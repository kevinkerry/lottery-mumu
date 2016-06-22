package com.lottery.ticket.sender.worker.vender;

import com.lottery.common.Constants;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.common.util.DateUtil;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.SeqEnum;
import com.lottery.core.domain.UserInfo;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.IVenderConverter;
import com.lottery.ticket.vender.impl.huai.HuAiLotteryDef;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HuAiVenderTicketSendWorker extends AbstractVenderTicketSendWorker {
	private String betCode = "101";

	@Override
	protected List<TicketSendResult> executePerSend(TicketBatch ticketBatch, List<Ticket> ticketList, LotteryType lotteryType,IVenderConfig venderConfig,IVenderConverter venderConverter) throws Exception {
		  List<TicketSendResult> ticketSendResultList = new ArrayList<TicketSendResult>();
		    for (Ticket ticket : ticketList) {
				TicketSendResult ticketSendResult = createInitializedTicketSendResult(ticket);
				ticketSendResultList.add(ticketSendResult);
				String messageStr = "";
				String returnStr = "";
				try {
					IVenderConverter haConverter = getVenderConverter();
					messageStr = getElement(ticket, venderConfig, lotteryType, haConverter);
					ticketSendResult.setSendMessage(messageStr);
					ticketSendResult.setSendTime(new Date());
					ticketSendResult.setId(ticket.getId());
					returnStr = HTTPUtil.sendPostMsg(venderConfig.getRequestUrl(), messageStr);
//                    logger.error("请求 数据为{},返回数据为{}",messageStr,returnStr);
					dealResult(messageStr, returnStr, ticketBatch,ticketSendResult, lotteryType);
				} catch (Exception e) {
					List<Ticket> allnewList=new ArrayList<Ticket>();
					allnewList.add(ticket);
					processError(ticketSendResultList, ticketBatch, allnewList, convertResultStatusFromException(e), null, messageStr, returnStr, e.getMessage());
					logger.error("互爱送票处理错误", e);

				}
			}
		
		return ticketSendResultList;
	}

	/**
	 * 送票结果处理
	 * 
	 * @param desContent
	 * @return
	 */
	
	protected void dealResult(String requestStr, String desContent, TicketBatch ticketBatch, TicketSendResult ticketSendResult, LotteryType lotteryType) throws Exception {

		Document doc = DocumentHelper.parseText(desContent);
		Element rootElt = doc.getRootElement();
		String returnCode = rootElt.elementText("reCode");	
		String returnMsg = rootElt.elementText("reMessage");
		ticketSendResult.setTerminalId(ticketBatch.getTerminalId());
		ticketSendResult.setResponseMessage(desContent);
		ticketSendResult.setSendMessage(requestStr);
		ticketSendResult.setSendTime(new Date());
		ticketSendResult.setMessage(returnMsg);
		ticketSendResult.setTerminalType(getTerminalType());
		if("0".equals(returnCode)) {
			ticketSendResult.setStatus(TicketSendResultStatus.success);
			ticketSendResult.setSendTime(new Date());
			ticketSendResult.setLotteryType(lotteryType);
			ticketSendResult.setStatusCode(returnCode);
		} else if ("306".equals(returnCode)) {// 票号重复
			ticketSendResult.setStatus(TicketSendResultStatus.duplicate);
			ticketSendResult.setMessage("网站平台交易流水号重复");
		} else if (Constants.huaiSendError.containsKey(returnCode)) {
			ticketSendResult.setMessage(Constants.huaiSendError.get(returnCode));
			ticketSendResult.setStatus(TicketSendResultStatus.unkown);
		} else {
			ticketSendResult.setStatus(TicketSendResultStatus.unkown);
		}
	}
	

	/**
	 * 送票前拼串
	 * @return
	 * @throws Exception
	 */

	public String getElement(Ticket ticket,IVenderConfig haConfig, LotteryType lotteryType, IVenderConverter haConverter) throws UnsupportedEncodingException {
		try {
			StringBuffer stringBuffer=new StringBuffer();
			String messageId=idGeneratorService.getMessageId(SeqEnum.vender_gzcp_messageid);
			stringBuffer.append("exAgent=").append(haConfig.getAgentCode())
			.append("&exAction=").append(betCode).append("&exMsgID="+messageId);
			
			StringBuilder  exParam=new StringBuilder();
			String userNo = "123456";
			UserInfo userInfo = userInfoService.get(userNo);
		
			Double	amount = ticket.getAmount().doubleValue() / 100;
			String playType=HuAiLotteryDef.getPlayType(ticket);
			exParam.append("OrderID=").append(ticket.getId()).append("_")
		           .append("LotID=").append(HuAiLotteryDef.getJcLotteryNo(ticket.getPlayType(),ticket.getLotteryType())).append("_")
		           .append("LotIssue=").append(getPhase(ticket)).append("_")
		           .append("LotMoney=").append(amount).append("_")
			       .append("LotCode=").append("1|")
			                          .append(playType).append("|")
			                          .append(HuAiLotteryDef.playTypeMap.get(ticket.getPlayType())).append("|")
			                          .append(haConverter.convertContent(ticket)).append("|")
			                          .append(ticket.getMultiple()).append("|")
			                          .append(amount).append("_")
			       .append("Name=").append(userInfo.getRealName()).append("_")
			       .append("CardType=1_")
			       .append("CardNO=").append(userInfo.getIdcard()).append("_")
			       .append("Phone=").append( userInfo.getPhoneno());	
			
			String signMsg=MD5Util.toMd5(haConfig.getAgentCode()+betCode+messageId+exParam.toString()+haConfig.getKey());
			stringBuffer.append("&exParam=").append(exParam.toString()).append("&exSign=").append(signMsg);
			return stringBuffer.toString();
		} catch (Exception e) {
			logger.error("送票拼串异常", e);
		}
		return null;
	}

	@Override
	protected TerminalType getTerminalType() {
		return TerminalType.T_HUAI;
	}
	
	/**
	 * 期次转换
	 * @param ticket
	 * @return
	 */
	private String getPhase(Ticket ticket){
		LotteryType lotteryType=LotteryType.get(ticket.getLotteryType());
		if(LotteryType.getJclq().contains(lotteryType)||LotteryType.getJczq().contains(lotteryType)){
			String content=ticket.getContent().split("\\-")[1].replace("^","").split("\\|")[0];
			StringBuffer stringBuffer=new StringBuffer();
			String con=content.substring(0, 8);
			stringBuffer.append(con+DateUtil.getWeekOfDate(DateUtil.parse("yyyyMMdd",con))+content.substring(8,11));
			return  stringBuffer.toString();
//		}else if(lotteryType==LotteryType.JC_GUANJUN||lotteryType==LotteryType.JC_GUANYAJUN){
//			IVenderConverter haConverter = getVenderConverter();
//			return haConverter.convertPhase(ticket.getLotteryType(), ticket.getPhase());
		}else{
			IVenderConverter haConverter = getVenderConverter();
			return haConverter.convertPhase(ticket.getLotteryType(), ticket.getPhase());
		}
	}

	
	
	
}
