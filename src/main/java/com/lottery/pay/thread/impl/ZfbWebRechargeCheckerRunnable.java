package com.lottery.pay.thread.impl;

import com.lottery.common.Constants;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.common.contains.pay.PayStatus;
import com.lottery.core.domain.UserTransaction;
import com.lottery.pay.IPayConfig;
import com.lottery.pay.progress.zfb.ZfbPay;
import com.lottery.pay.thread.AbstractRechargeCheckerRunnable;
import com.lottery.ticket.sender.worker.XmlParse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ZfbWebRechargeCheckerRunnable extends AbstractRechargeCheckerRunnable {

	@Override
	protected PayChannel getPayChannel() {
		return PayChannel.zfbwebpay;
	}

	@Override
	protected void process(IPayConfig payConfig,
			List<UserTransaction> userTransactionList) {
		// 查票并处理结果
		for (UserTransaction userTransaction : userTransactionList) {
			String is_success = "";
			Element rootElt = null;
			String orderStr = "";
			try {
				orderStr = ZfbPay.queryOrderInfo(userTransaction.getId(),payConfig);
			} catch (Exception e) {
				logger.error("充值web查询接口异常" , e);
				continue;
			}
			//解析
            if(StringUtils.isNotBlank(orderStr)){
			try {
				rootElt=XmlParse.getRootElement(orderStr);
				is_success = rootElt.elementTextTrim("is_success");
			} catch (DocumentException e) {
				logger.error("支付宝web充值通知解析格式异常", e);
				continue;
			}
			//结果处理
			try {
				if ("F".equals(is_success)) {
					String error = rootElt.elementTextTrim("error");
					if (Constants.zfbError.containsKey(error)) {
						processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,error);
					}else if(error.equals("TRADE_NOT_EXIST")){
						long timeout=(new Date()).getTime()-userTransaction.getCreateTime().getTime();
						if (timeout>30*60*1000){//如果超过30分钟为失败
							processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,error);
						}
					}else {
						logger.error("支付宝查询订单:{},充值返回错误:{}", userTransaction.getId(),orderStr);
					}
				} else if ("T".equals(is_success)) {
					UserTransaction userTrans = this.getChargeResult(userTransaction, orderStr);
					if (userTrans != null) {
						if(PayStatus.PAY_FAILED.getValue()==userTrans.getStatus()){
							processResult(userTrans.getId(),userTrans.getTradeNo(),userTrans.getAmount()+"",false,userTrans.getDescription());
						}else if(PayStatus.PAY_SUCCESS.getValue()==userTrans.getStatus()){
							processResult(userTrans.getId(),userTrans.getTradeNo(),userTrans.getAmount()+"",true,"");
						}
					}
				}
			} catch (Exception e) {
				logger.error("支付宝充值订单{}处理异常",userTransaction.getId(),e);
			}
          }
		}
	}

		/**
		 * 成功查询解析串并封装
		 * @param userTransaction
		 * @param orderStr
		 * @return
		 * @throws DocumentException 
		 */
	public UserTransaction getChargeResult(UserTransaction userTransaction,String orderStr) throws DocumentException {
	            Map<String,String>	map=XmlParse.getElementText("response/","trade",orderStr);
				String price=map.get("price");
				String trade_no = map.get("trade_no");
				String trade_status =map.get("trade_status");
				String out_trade_no =map.get("out_trade_no");
				if (Constants.TRADE_SUCCESS.equals(trade_status)||Constants.TRADE_FINISHED.equals(trade_status)) {
					userTransaction.setStatus(PayStatus.PAY_SUCCESS.getValue());
				}else if ("TRADE_CLOSED".equals(trade_status)) {
					userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
					userTransaction.setDescription("订单未支付失败");
				}else if ("WAIT_BUYER_PAY".equals(trade_status)) {
					long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
					if(leftTime>30){//超过30分未支付置失败
						userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
						userTransaction.setDescription("订单长时间未支付失败");
					}else{
						return null;
					}
				}
				userTransaction.setFinishTime(new Date());
				userTransaction.setTradeNo(trade_no);
				userTransaction.setId(out_trade_no);
				userTransaction.setAmount(new BigDecimal(price));
				return userTransaction;
	}

}
