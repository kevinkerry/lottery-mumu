package com.lottery.ticket.sender.worker.vender;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.lottery.common.Constants;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.common.util.DateUtil;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.domain.UserInfo;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.IVenderConverter;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.huancai.HuancaiConfig;
import com.lottery.ticket.vender.impl.huancai.HuancaiLotteryDef;

@Service
public class HuanCaiVenderTicketSendWorker extends AbstractVenderTicketSendWorker {
	private String betCode = "1002";

	@Override
	protected List<TicketSendResult> executePerSend(TicketBatch ticketBatch, List<Ticket> ticketList, LotteryType lotteryType,IVenderConfig venderConfig,IVenderConverter venderConverter) throws Exception {
		List<TicketSendResult> ticketSendResultList = new ArrayList<TicketSendResult>();

		for (Ticket ticket : ticketList) {
			TicketSendResult ticketSendResult = createInitializedTicketSendResult(ticket);
			ticketSendResultList.add(ticketSendResult);
			String messageStr = "";
			String returnStr = "";
			try {
				IVenderConverter huancaiConverter = getVenderConverter();
				String lotno = huancaiConverter.convertLotteryType(lotteryType);
				String phase = huancaiConverter.convertPhase(lotteryType, ticketBatch.getPhase());
				messageStr = getElement(ticket, lotno, phase, venderConfig, lotteryType, huancaiConverter);
				ticketSendResult.setSendMessage(messageStr);
				ticketSendResult.setSendTime(new Date());
				returnStr = HTTPUtil.sendPostMsg(venderConfig.getRequestUrl(), messageStr);
				ticketSendResult.setResponseMessage(returnStr);
				Map<String, String> map = XmlParse.getElementAttribute("/", "response", returnStr);
				String code = map.get("code");
				String msg = map.get("msg");
				ticketSendResult.setStatusCode(code);
				ticketSendResult.setId(ticket.getId());
				ticketSendResult.setMessage(msg);
				if ("0000".equals(code)) {
					ticketSendResult.setStatus(TicketSendResultStatus.success);
					ticketSendResult.setSendTime(new Date());
				} else if ("0006".equals(code)) {// 票号重复
					ticketSendResult.setStatus(TicketSendResultStatus.duplicate);
				} else if (Constants.huancaiErrorFailed.containsKey(code)) {
					ticketSendResult.setStatus(TicketSendResultStatus.failed);
				}else {
					ticketSendResult.setStatus(TicketSendResultStatus.unkown);
				}

			} catch (Exception e) {
				ticketSendResult.setStatus(TicketSendResultStatus.unkown);
				ticketSendResult.setStatus(convertResultStatusFromException(e));
				ticketSendResult.setMessage(e.getMessage());
				ticketSendResult.setSendMessage(messageStr);
				ticketSendResult.setResponseMessage(returnStr);
				logger.error("环彩送票处理错误", e);
			}

		}
		return ticketSendResultList;
	}

	

	/**
	 * 送票前拼串
	 * 
	 * @param ticketBatchList
	 *            票集合
	 * @param lotteryNo
	 *            彩种
	 * @return
	 * @throws Exception
	 */

	public String getElement(Ticket ticket, String lotteryNo, String phase, IVenderConfig huancaiConfig, LotteryType lotteryType, IVenderConverter huancaiConverter) {
		String md = "";
		XmlParse xmlParse = null;
		try {
			String messageId=DateUtil.format("yyyyMMddHHmmss", new Date());;
			xmlParse = HuancaiConfig.addGxHead(betCode,huancaiConfig.getAgentCode(),messageId);
			HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
			bodyAttr.put("lottid", lotteryNo);
			bodyAttr.put("issue", phase);
			HashMap<String, Object> bodyAttr2 = new HashMap<String, Object>();
			Double amount = ticket.getAmount().doubleValue();
			String userNo = "123456";
			UserInfo userInfo = userInfoService.get(userNo);
			bodyAttr2.put("ticketid", ticket.getId());
			String selectType =HuancaiLotteryDef.getPlayType(ticket);
			bodyAttr2.put("playcode", selectType);
			bodyAttr2.put("amount", amount.intValue());
			bodyAttr2.put("multiple", ticket.getMultiple() + "");
			bodyAttr2.put("name", userInfo.getUsername());
			bodyAttr2.put("idcard", userInfo.getIdcard());
			bodyAttr2.put("phone", userInfo.getPhoneno());
			Element attributeMap2 = xmlParse.addBodyElementAndAttribute("order", "", bodyAttr);
			xmlParse.addBodyElementAndAttribute("ticket","<betCode>"+huancaiConverter.convertContent(ticket)+"</betCode>", bodyAttr2, attributeMap2);
			
			try {
				md = MD5Util.toMd5(huancaiConfig.getAgentCode()+messageId + huancaiConfig.getKey() + xmlParse.getBodyElement().asXML().replace("&lt;","<").replace("&gt;",">"));
			} catch (Exception e) {
				logger.error("加密异常" + e.getMessage());
			}
			xmlParse.addHeaderElement("digest", md);
			return  "cmd="+betCode+"&msg="+xmlParse.asXML().replace("&lt;","<").replace("&gt;",">");
		} catch (Exception e) {
			logger.error("送票拼串异常", e);
		}
		return null;
	}

	
	
	@Override
	protected TerminalType getTerminalType() {

		return TerminalType.T_HUANCAI;
	}

}
