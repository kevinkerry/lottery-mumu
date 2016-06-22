package com.lottery.pay.drawamount.thread;

import com.lottery.common.contains.DrawAmountStatus;
import com.lottery.common.contains.DrawOperateType;
import com.lottery.common.contains.DrawType;
import com.lottery.common.contains.pay.PayChannel;
import com.lottery.core.service.LotteryDrawAmountService;
import com.lottery.pay.BasePayConfig;
import com.lottery.pay.drawamount.AbstractZfbBankDraw;
import com.lottery.pay.drawamount.ZfbDrawAmount;
import com.lottery.ticket.sender.worker.XmlParse;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * 支付宝提现
 * 
 * @author lxy
 * 
 */
public class ZfbDrawCheckerRunnable extends AbstractZfbBankDraw {
	@Autowired
	private LotteryDrawAmountService lotteryDrawAmountService;

	@Override
	protected void execute() throws Exception {
		List<String> lotteryDrawAmounts=null;
		BasePayConfig basePayConfig=null;
	    try {
	    	 lotteryDrawAmounts=getDataList(DrawType.zfb_draw.value, DrawOperateType.zfb_draw.value);
		     basePayConfig=getConfig(lotteryDrawAmounts,PayChannel.zfbdraw);
	     } catch (Exception e) {
			logger.error("支付宝提现订单查询出错",e);

	    }

		if (lotteryDrawAmounts==null||basePayConfig==null){
			logger.info("支付宝查询或配置为空");
			return;
		}

		try {
			for(String batchId:lotteryDrawAmounts){
				String str=ZfbDrawAmount.getMapStr(basePayConfig,batchId);

				String is_success = "";
				try{
					Element rootElt= XmlParse.getRootElement(str);
					is_success = rootElt.elementTextTrim("is_success");
					if("T".equals(is_success)){
						    HashMap<String,String> resultMap=XmlParse.getElementText("response/","order",str);
							String res_data=resultMap.get("res_data");
							String []results=res_data.split("\\|");
							for(int i=0;i<results.length;i++){
								String []contents=results[i].split("\\^");
								if("S".equals(contents[4])){
									lotteryDrawAmountService.doResult(contents[0],contents[1],contents[3],true,contents[5],new Date());
								}else if("F".equals(contents[4])){
									lotteryDrawAmountService.doResult(contents[0],contents[1],contents[3],false,contents[5],new Date());
								}else if("ÛSER_NOT_EXIST".equals(contents[4])){//余额不足
									lotteryDrawAmountService.updateStatusById(contents[1], DrawAmountStatus.haschecked.getValue());
								}
						}
					}else if ("F".equals(is_success)) {
						logger.error("提现批量batchid:{},查询返回异常:{}",batchId,str);
					} 
					
				} catch (Exception e) {
					logger.error("支付宝提现查询处理异常,str={}",str,e);
				}
			}
		} catch (Exception e) {
			logger.error("支付宝提现订单查询出错",e);

		}
		
	}
	
}
