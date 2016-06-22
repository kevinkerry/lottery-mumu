package com.lottery.ticket.vender.notice.impl.ZhangYi;

import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.lottery.TicketVenderStatus;
import com.lottery.common.contains.ticket.TicketVender;
import com.lottery.common.util.CoreDateUtils;
import com.lottery.common.util.DateUtil;
import com.lottery.core.SeqEnum;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.vender.impl.zhangyi.ZYLotteryDef;
import com.lottery.ticket.vender.notice.AbstractVenderNoticeProcess;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Service
public class ZYNoticeProcess extends AbstractVenderNoticeProcess {

	
    @Override
    protected TerminalType getTerminalType() {
        return TerminalType.T_ZY;
    }


    @Override
    protected String getAgentId() {
        return "agenterid";
    }

    protected Map<String, String> convertRequestParameter(String requestString) {
    	Map<String,String>map=new HashMap<String, String>();
		try {
			 JSONObject mapObject =JSONObject.fromObject(requestString);
	    	 JSONObject headMap=(JSONObject) mapObject.get("header");
			 map.put("msg",mapObject.get("msg").toString());
			 map.put("agenterid",(String) headMap.get("agenterid"));
			return map;
		} catch (Exception e) {
			logger.error("解析xml异常",e);
		}
		
        return null;
    }

	@Override
	protected boolean validate(Map<String, String> requestMap, IVenderConfig venderConfig) {
		return true;
	}

	@Override
    protected String execute(Map<String, String> requestMap,IVenderConfig venderConfig) {
		
        //将具体信息解析
    	String exValue=requestMap.get("msg");
    	 try {
			 writeNoticeLog("解析后的数据是:"+exValue);
    		 JSONObject returnMsg=JSONObject.fromObject(exValue);
    		 Iterator<JSONObject> ticketList=returnMsg.getJSONArray("ticketlist").iterator();
				while(ticketList.hasNext()){
					JSONObject jObject=ticketList.next();
					String status = String.valueOf(jObject.get("status"));
					String externalId =String.valueOf(jObject.get("ticketid"));
					String ticketId =String.valueOf(jObject.get("id"));
					String failreason=jObject.getString("failreason");
					TicketVender ticketVender = createInit();//参照检票操作
					ticketVender.setId(ticketId);
			     	ticketVender.setSerialId("");
					ticketVender.setExternalId(externalId);
				    ticketVender.setMessage(failreason);
					if ("1".equals(status)||"4".equals(status)||"5".equals(status)) {//出票中
						ticketVender.setStatus(TicketVenderStatus.printing);
					} else if ("2".equals(status)) {// 成功
						String odds= jObject.get("spinfo")!=null?String.valueOf(jObject.get("spinfo")):"";
						ticketVender.setStatus(TicketVenderStatus.success);
						Ticket ticket = ticketService.getTicket(ticketId);
						if(StringUtils.isNotEmpty(odds)){
							odds=getPeilv(ticket,odds);
						}
						if (StringUtils.isNotBlank(odds)){
							ticketVender.setPeiLv(odds);
						}
						if (ticket==null){
							logger.error("根据票号{}未查到相关票信息",ticketId);
						}	
					} else {// 失败
					    ticketVender.setStatus(TicketVenderStatus.failed);
					}  
					ticketVender.setPrintTime(new Date());
					this.ticketVenderProcess(ticketVender);	
				}
 		
				return getReturn(venderConfig);  
		} catch (Exception e) {
			logger.error("掌奕处理通知异常",e);
			 return "failure";
		}
    }
    /**
     * 返回
     * @param zyConfig
     * @return
     */
	private String getReturn(IVenderConfig zyConfig){
		JSONObject message = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject msg = new JSONObject();
		header.put("messengerid",idGeneratorService.getMessageId(SeqEnum.vender_gzcp_messageid,CoreDateUtils.DATE_YYYYMMDDHHMMSS));
		header.put("timestamp",DateUtil.format("yyyyMMddHHmmss", new Date()));
		header.put("transactiontype","13006");
		header.put("des","0");
		header.put("agenterid",zyConfig.getAgentCode());
		message.put("header", header);	
		msg.put("errorcode", "0");
		msg.put("errormsg", "ok");		
		message.put("msg",msg);
		return message.toString();
	}
		
	private static String getPeilv(Ticket tickt,String venderSp){
		Integer lotteryType=tickt.getLotteryType();
		StringBuilder strBuilder=new StringBuilder();
		String []oddss=venderSp.split("\\;");
		int i=0;
		for(String odd:oddss){
			if (LotteryType.getJczq().contains(LotteryType.get(lotteryType))&&lotteryType!=LotteryType.JCZQ_HHGG.getValue()){
				strBuilder.append("20").append(odd.replace("-",""));
			}else if(lotteryType==LotteryType.JCZQ_HHGG.getValue()){
				strBuilder.append("20").append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
				.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(").append(odd.split("\\(")[1]);
			}else if(LotteryType.getJclq().contains(LotteryType.get(lotteryType))&&lotteryType!=LotteryType.JCLQ_HHGG.getValue()){
				if(lotteryType==LotteryType.JCLQ_SF.getValue()){
					strBuilder.append(odd.replace("-",""));
				}else if(lotteryType==LotteryType.JCLQ_SFC.getValue()){ 
					strBuilder.append(odd.replace("-","").split("\\(")[0]).append("(").append(odd.replace("-","").split("\\(")[1].split("\\)")[0].replace("6", "06")
					.replace("5", "05").replace("4", "04").replace("3", "03").replace("2", "02").replace("1","01").replace("7", "11").replace("8", "12")
					.replace("9","13").replace("10","14").replace("11","15").replace("12", "16")).append(")");
				}else{
					String []cons=odd.replace("-","").replace(")", "").split("\\(")[1].split("\\,");
					strBuilder.append(odd.replace("-","").split("\\(")[0]).append("(");
					int j=0;
					strBuilder.append(cons[0].split("\\_")[1]).append(":");
					for(String con:cons){
						strBuilder.append(con.split("\\_")[0]).append("_").append(con.split("\\_")[2]);
						if(j!=cons.length-1){
							strBuilder.append(",");
						}
						j++;
					}
					strBuilder.append(")");
				}
			}else if(lotteryType==LotteryType.JCLQ_HHGG.getValue()){
				int  lottery=ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0]);
				if(lottery==LotteryType.JCLQ_DXF.getValue()||lottery==LotteryType.JCLQ_RFSF.getValue()){
					strBuilder.append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
					.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(");
					String []cons=odd.replace(")", "").split("\\(")[1].split("\\,");
					int j=0;
					for(String con:cons){
						strBuilder.append(con.split("\\_")[0]).append("_").append(con.split("\\_")[2]);
						if(j!=cons.length-1){
							strBuilder.append(",");
						}
						j++;
					}
					strBuilder.append(")");
				}else if(lottery==LotteryType.JCLQ_SFC.getValue()){
					strBuilder.append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
					.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(").append(odd.split("\\(")[1].split("\\_")[0].replace("6", "06")
					.replace("5", "05").replace("4", "04").replace("3", "03").replace("2", "02").replace("1","01").replace("7", "11").replace("8", "12")
					.replace("9","13").replace("10","14").replace("11","15").replace("12", "16"))
					.append("_").append(odd.split("\\(")[1].split("\\_")[1]);
				}else{
					strBuilder.append(odd.replace("-","").split("\\^")[1].split("\\(")[0]).append("*")
					.append(ZYLotteryDef.toLotteryMap.get(odd.split("\\^")[0])).append("(").append(odd.split("\\(")[1]);
				}
				
			}
			
			if(i!=oddss.length-1){
				strBuilder.append("|");
			}
			i++;
		}
		return strBuilder.toString();
}
	
	
}
