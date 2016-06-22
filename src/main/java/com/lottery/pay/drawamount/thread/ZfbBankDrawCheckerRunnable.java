package com.lottery.pay.drawamount.thread;
import com.lottery.common.contains.DrawOperateType;
import com.lottery.common.contains.DrawType;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.core.handler.LotteryDrawAmountHandler;
import com.lottery.pay.BasePayConfig;
import com.lottery.pay.drawamount.AbstractZfbBankDraw;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * 支付宝银行提现线程
 * 
 * @author lxy
 * 
 */
public class ZfbBankDrawCheckerRunnable extends AbstractZfbBankDraw {
	@Autowired
	private LotteryDrawAmountHandler drawAmountHandler;
	@Override
	protected void execute() throws Exception {
		 List<String> lotteryDrawAmounts=null;
		BasePayConfig basePayConfig=null;
	    try {
	    	 lotteryDrawAmounts=getDataList(DrawType.bank_draw.value, DrawOperateType.zfb_bankdraw_auto.value);
	    	 if(lotteryDrawAmounts==null||lotteryDrawAmounts.size()==0)
	    	 {
	    		 return;
	    	 }
		     basePayConfig=getConfig(lotteryDrawAmounts,PayChannel.zfbbankdraw);
	     } catch (Exception e) {
			logger.error("支付宝银行卡提现查询明细线程异常",e);

	    }
	    if(basePayConfig==null){
	    	logger.error(DrawType.bank_draw.value+"配置为空");
	    	return;
	    }
		if (lotteryDrawAmounts==null){
			return;
		}
		
	    for(String batchId:lotteryDrawAmounts){
	        try{
				drawAmountHandler.zfbLotteryDraw(basePayConfig,batchId);
	        } catch (Exception e) {
					logger.error("支付宝银行卡提现查询线程异常",e);
			}
		}	
		
		
	}
	
}
