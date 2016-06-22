package com.lottery.pay.thread.impl;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.common.contains.pay.PayStatus;
import com.lottery.core.domain.UserTransaction;
import com.lottery.pay.IPayConfig;
import com.lottery.pay.progress.yeebao.YeeBaoPay;
import com.lottery.pay.thread.AbstractRechargeCheckerRunnable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YeePayRechargeCheckerRunnable extends AbstractRechargeCheckerRunnable {

	@Override
	protected PayChannel getPayChannel() {
		return PayChannel.yeebao;
	}

	@Override
	protected void process(IPayConfig payConfig,
			List<UserTransaction> userTransactionList) {
		// 查票并处理结果
		for (UserTransaction userTransaction : userTransactionList) {
			List<String>strList =null;
			try {
				strList =YeeBaoPay.queryOrderInfo(userTransaction.getId(),payConfig);
			} catch (Exception e) {
				logger.error("充值查询接口异常", e);
				continue;
			}
			Map<String, String>mapStr=new HashMap<String, String>();
			//解析
			if(strList!=null&&strList.size()>0){
				for(String str:strList){
				 String []strs=	str.split("=");
				 if(strs.length>1){
					 mapStr.put(strs[0],strs[1]+"");
				 }else{
					 mapStr.put(strs[0],"");
				 }
				}
			}
			
			try {
				if ("1".equals(mapStr.get("r1_Code"))) {
					 if("CANCELED".equals(mapStr.get("rb_PayStatus"))){
						 processResult(userTransaction.getId(),"",String.valueOf(new BigDecimal(mapStr.get("r3_Amt")).multiply(new BigDecimal("100"))),false,"已取消,充值失败");
					 }else if("SUCCESS".equals(mapStr.get("rb_PayStatus"))){//成功
						 processResult(userTransaction.getId(),mapStr.get("r2_TrxId"),String.valueOf(new BigDecimal(mapStr.get("r3_Amt")).multiply(new BigDecimal("100"))),true,"");
					 }else if("WAIT_BUYER_PAY".equals(mapStr.get("rb_PayStatus"))||"INIT".equals(mapStr.get("rb_PayStatus"))){//等待支付
						 long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
							if(leftTime>30){
								userTransaction.setStatus(PayStatus.PAY_FAILED.getValue());
								processResult(userTransaction.getId(),"",String.valueOf(new BigDecimal(mapStr.get("r3_Amt")).multiply(new BigDecimal("100"))),false,"订单长时间未支付失败");
							}
					 }
				} else if ("50".equals(mapStr.get("r1_Code"))) {//订单不存在
					long leftTime=(new Date().getTime()-userTransaction.getCreateTime().getTime())/60000;
					if(leftTime>60){
						processResult(userTransaction.getId(),"",String.valueOf(userTransaction.getAmount()),false,"未完成交易,订单失败");
                    }			
			    }
			} catch (Exception e) {
				logger.error("易宝充值订单"+userTransaction.getId()+"处理结果异常" + e);

			}
		}
	}

}
