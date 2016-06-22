package com.lottery.pay.thread.impl;
import com.lottery.common.Constants;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.common.contains.pay.PayStatus;
import com.lottery.common.util.DateUtil;
import com.lottery.core.domain.UserTransaction;
import com.lottery.pay.IPayConfig;
import com.lottery.pay.progress.elinkpc.ElinkPcPay;
import com.lottery.pay.progress.elinkpc.util.HttpClient;
import com.lottery.pay.progress.elinkpc.util.SDKUtil;
import com.lottery.pay.thread.AbstractRechargeCheckerRunnable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ElinkpcwyRechargeCheckerRunnable extends AbstractRechargeCheckerRunnable {
	
	@Override
	protected PayChannel getPayChannel() {
		return PayChannel.elinkpcwypay;
	}

	@Override
	protected void process(IPayConfig payConfig,
			List<UserTransaction> userTransactionList) {


		// 查票并处理结果
		for (UserTransaction userTransaction : userTransactionList) {

			try {
				String singcerpath=getPath()+payConfig.getPrivateCerPath();
				Map<String, String>	orderStr =ElinkPcPay.queryOrderInfo(userTransaction.getId(),DateUtil.format("yyyyMMddHHmmss",userTransaction.getCreateTime()),payConfig.getPartner(),singcerpath,payConfig.getPasswd());
				String resultString = HttpClient.send(orderStr, payConfig.getSearchUrl());
				if(StringUtils.isNotBlank(resultString)){
					Map<String, String> map = SDKUtil.convertResultStringToMap(resultString);
					String origRespCode=map.get("origRespCode");
					String respcode=map.get("respCode");
					String respMsg=map.get("respMsg");
					String queryId=map.get("queryId");
					if("00".equals(respcode)&&"00".equals(origRespCode)){//成功或已成功
						String tradeAmt=map.get("settleAmt");
						processResult(userTransaction.getId(),queryId,tradeAmt,true,respMsg);
					}else{
						logger.error("银联网银充值订单{}交易异常,查询返回{}",userTransaction.getId(),resultString);
						if(Constants.elinPckFailedError.containsKey(respcode)){
							long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
							if(leftTime>60){
								processResult(userTransaction.getId(),queryId,String.valueOf(userTransaction.getAmount()),false,respMsg);
							}else{
								logger.error("充值订单:{},不存在{}",userTransaction.getId(),respMsg);
							}
						}else if("05".equals(origRespCode)){
							long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
							if(leftTime>60){
								userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
								processResult(userTransaction.getId(),queryId,String.valueOf(userTransaction.getAmount()),false,"订单长时间未支付失败");
							}
						}else if("77".equals(origRespCode)){//银行卡未开通认证
							userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
							processResult(userTransaction.getId(),queryId,String.valueOf(userTransaction.getAmount()),false,"订单银行卡未开通认证支付失败");
						}else if("64".equals(origRespCode)){//支付余额不足
						   userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
						   processResult(userTransaction.getId(),queryId,String.valueOf(userTransaction.getAmount()),false,"支付余额不足");
					    }else{
							userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
							processResult(userTransaction.getId(),queryId,String.valueOf(userTransaction.getAmount()),false,"充值异常");
						}
					}
				}
			} catch (Exception e) {
				logger.error("银联网银充值查询出错id={}",userTransaction.getId(),e);
			}

			
		}
	}




	
}
