package com.lottery.pay.thread.impl;
import com.lottery.common.Constants;
import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.common.contains.pay.PayStatus;
import com.lottery.common.util.HTTPUtil;
import com.lottery.core.domain.UserTransaction;
import com.lottery.pay.IPayConfig;
import com.lottery.pay.progress.elink.ElinkPay;
import com.lottery.pay.thread.AbstractRechargeCheckerRunnable;

import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElinkRechargeCheckerRunnable extends AbstractRechargeCheckerRunnable {

	@Override
	protected PayChannel getPayChannel() {
		return PayChannel.elinkpay;
	}

	@Override
	protected void process(IPayConfig payConfig,
			List<UserTransaction> userTransactionList) {
		// 查票并处理结果
		for (UserTransaction userTransaction : userTransactionList) {
			String orderStr =null;
			try {
				orderStr =ElinkPay.queryOrderInfo(userTransaction,payConfig);
			} catch (Exception e) {
				logger.error("充值查询接口异常",e);
				continue;
			}
			String respString=null;
			try {
				respString = HTTPUtil.post(payConfig.getSearchUrl(), orderStr, CharsetConstant.CHARSET_UTF8);
			} catch (Exception e) {
				logger.error("银联充值异常",e);
			}

			if(StringUtils.isBlank(respString)){
				return;
			}
			try{
				String []names=respString.split("\\&");
				Map<String,String>map=new HashMap<String, String>();
				for(String name:names){
					String key=name.split("\\=")[0];
					String value=name.split("\\=")[1];
					map.put(key, value);
				}
				String respcode=map.get("respCode");
				String transStatus=map.get("transStatus");
				if("00".equals(respcode)){
					if("00".equals(transStatus)){
					    processResult(userTransaction.getId(),userTransaction.getTradeNo(),String.valueOf(userTransaction.getAmount()),true,"");
				    }if("03".equals(transStatus)){
				    	processResult(userTransaction.getId(),userTransaction.getTradeNo(),String.valueOf(userTransaction.getAmount()),false,"充值失败");
				    }else{
						logger.error("充值订单{}交易异常,错误为{}",userTransaction.getId(),Constants.elinkError.get("respcode"));
					}
				}else{
					logger.error("充值订单{}交易异常,原始信息为:{}",userTransaction.getId(),respString);
					if(Constants.elinkFailedError.containsKey(map.get("respcode"))){

						processResult(userTransaction.getId(),userTransaction.getTradeNo(),String.valueOf(userTransaction.getAmount()),false,Constants.elinkFailedError.get("respcode"));
					}else if("11".equals(respcode)){
						long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
						if(leftTime>30){
							userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
							processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"订单长时间未支付失败");
						}
					}
				}
			}catch (Exception e){
				logger.error("银联充值查询出错",e);
			}

		}
	}

}
