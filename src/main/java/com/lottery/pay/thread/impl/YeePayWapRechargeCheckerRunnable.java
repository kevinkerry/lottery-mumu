package com.lottery.pay.thread.impl;
import com.lottery.common.Constants;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.common.contains.pay.PayStatus;
import com.lottery.core.domain.UserTransaction;
import com.lottery.pay.IPayConfig;
import com.lottery.pay.progress.yeebaowap.YeeBaoWapPay;
import com.lottery.pay.thread.AbstractRechargeCheckerRunnable;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

public class YeePayWapRechargeCheckerRunnable extends AbstractRechargeCheckerRunnable {

	@Override
	protected PayChannel getPayChannel() {
		return PayChannel.yeebaowap;
	}

	@Override
	protected void process(IPayConfig payConfig,
			List<UserTransaction> userTransactionList) {
		// 查票并处理结果
		for (UserTransaction userTransaction : userTransactionList) {
			
			JSONObject jsObject =null;
			try {
				jsObject =YeeBaoWapPay.queryOrderInfo(userTransaction.getId(),payConfig);
			} catch (Exception e) {
				logger.error("充值查询接口异常", e);
				continue;
			}
			
			try {
				if(jsObject.containsKey("status")){
					if(jsObject.getInt("status")==1){//成功
						 processResult(userTransaction.getId(),jsObject.getString("yborderid"),jsObject.get("amount")+"",true,"成功");
					}else if(jsObject.getInt("status")==0){//等待支付
						 long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
						 if(leftTime>30){
							userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
							processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"等待支付超时");
						}	
				   }else if(jsObject.getInt("status")==2){//已撤销
						 processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"已取消,充值失败");
				   }else if(jsObject.getInt("status")==3){
						 processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"交易阻断,充值失败");
				   }else if(jsObject.getInt("status")==4){
						 processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"充值失败");
				   }   
				}else{
					logger.error("易宝wap充值异常返回{}",jsObject.toString());
					if(Constants.yeepayWapError.containsKey(jsObject.get("rb_PayStatus"))){
				    	 processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,Constants.yeepayWapError.get("error"));	 
					} else if ("200002".equals(jsObject.get("error_code"))) {//订单不存在
						long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
						if(leftTime>60){
							processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"未完成交易,订单失败");
	                    }			
				    }
				}
			    
			} catch (Exception e) {
				logger.error("易宝wap充值订单"+userTransaction.getId()+"处理结果异常" + e);

			}
		}
	}

}
