package com.lottery.ticket.sender.worker.vender;

import com.lottery.common.Constants;
import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.contains.ticket.TicketSendResult;
import com.lottery.common.contains.ticket.TicketSendResultStatus;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.common.util.StringUtil;
import com.lottery.core.cache.RedisCache;
import com.lottery.core.domain.UserInfo;
import com.lottery.core.domain.ticket.Ticket;
import com.lottery.core.domain.ticket.TicketBatch;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.IVenderConverter;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.anrui.AnRuiConfig;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AnRuiVenderTicketSendWorker extends AbstractVenderTicketSendWorker {
	private String betCode = "101";
    @Autowired
	protected RedisCache redisCache;
	private String anruijczq__key="anruijczq_";
	private String anruijclq__key="anruijclq_";


	@Override
	protected List<TicketSendResult> executePerSend(TicketBatch ticketBatch, List<Ticket> ticketList,LotteryType lotteryType, IVenderConfig venderConfig,IVenderConverter venderConverter) throws Exception {
		List<TicketSendResult> ticketSendResultList = new ArrayList<TicketSendResult>();

		for (Ticket ticket : ticketList) {
			TicketSendResult ticketSendResult = createInitializedTicketSendResult(ticket);
			ticketSendResultList.add(ticketSendResult);
			String messageStr = "";
			String returnStr = "";
			try {

				
				String lotno = venderConverter.convertLotteryType(lotteryType);
				String phase = venderConverter.convertPhase(lotteryType, ticketBatch.getPhase());
				
			
				messageStr = getElement(ticket, lotno, phase, lotteryType,venderConfig,venderConverter);
				ticketSendResult.setSendMessage(messageStr);

				ticketSendResult.setSendTime(new Date());
				returnStr = HTTPUtil.sendPostMsg(venderConfig.getRequestUrl(), messageStr);
				ticketSendResult.setResponseMessage(returnStr);
				ticketSendResult.setStatusCode(returnStr);
				logger.error("返回结果："+returnStr);
				if ("0".equals(returnStr)) {
					ticketSendResult.setStatus(TicketSendResultStatus.success);
					ticketSendResult.setSendTime(new Date());
				} else if ("-20".equals(returnStr)) {// 票号重复
					ticketSendResult.setStatus(TicketSendResultStatus.duplicate);
					ticketSendResult.setMessage("网站平台交易流水号重复");
				} else if (Constants.anruiSendErrorFailed.containsKey(returnStr)) {
					ticketSendResult.setStatus(TicketSendResultStatus.failed);
					ticketSendResult.setMessage(Constants.anruiSendErrorFailed.get(returnStr));
				} else if (Constants.anruiSendError.containsKey(returnStr)) {
					ticketSendResult.setMessage(Constants.anruiSendError.get(returnStr));
					ticketSendResult.setStatus(TicketSendResultStatus.failed);
					ticketSendResult.setMessage(Constants.anruiSendError.get(returnStr));
				} else {
					ticketSendResult.setStatus(TicketSendResultStatus.unkown);
				}

			} catch (Exception e) {
				ticketSendResult.setStatus(TicketSendResultStatus.unkown);
				ticketSendResult.setStatus(convertResultStatusFromException(e));
				ticketSendResult.setMessage(e.getMessage());
				ticketSendResult.setSendMessage(messageStr);
				ticketSendResult.setResponseMessage(returnStr);
				logger.error("安瑞送票处理错误", e);
			}

		}
		return ticketSendResultList;
	}

	/**
	 * 送票前拼串
	 * 
	 * @param ticket
	 *            票集合
	 * @param lotteryNo
	 *            彩种
	 * @return
	 * @throws Exception
	 */

	public String getElement(Ticket ticket, String lotteryNo, String phase,LotteryType lotteryType, IVenderConfig anruiConfig,  IVenderConverter anruiConverter) {
		
		StringBuffer stringBuffer=new StringBuffer();
		Double amount = ticket.getAmount().doubleValue() / 100;
		String userNo = "123456";
		UserInfo userInfo = userInfoService.get(userNo);
		int zhushu=0;
		try {
			stringBuffer.append("transcode=").append(betCode)
			.append("&ChannelID=").append(anruiConfig.getAgentCode())
			.append("&LotteryID=").append(lotteryNo).append("&WareID=0")
			.append("&WareIssue=");
			if (LotteryType.getJclq().contains(lotteryType)||LotteryType.getJczq().contains(lotteryType)){
				stringBuffer.append("20110501");
			}else{
				stringBuffer.append(phase);
			}
			stringBuffer.append("&BatchID=").append(ticket.getId().substring(2))
			.append("&AddFlag=");
			if (ticket.getAddition() == 1) {// 大乐透追加
				stringBuffer.append("1");
				zhushu=amount.intValue() / ticket.getMultiple() / 3;
			}else{
				stringBuffer.append("0");
				zhushu=amount.intValue() / ticket.getMultiple() / 2;
			}
			zhushu=(zhushu*ticket.getMultiple());
			String content=anruiConverter.convertContent(ticket);
			if (LotteryType.getJclq().contains(lotteryType) || LotteryType.getJczq().contains(lotteryType)) {
				content=getContent(content,lotteryType,anruiConfig);
			}
			content=content+"~"+ticket.getMultiple()+"~"+zhushu+"@"+ticket.getId().substring(2);
			String wareid="0";
			String md = "";
			try {
				md = MD5Util.toMd5(anruiConfig.getAgentCode() +lotteryNo + wareid+zhushu+content+anruiConfig.getKey());
			} catch (Exception e) {
				logger.error("加密异常" + e.getMessage());
			}
			
			stringBuffer.append("&BetAmount=").append(zhushu)
			.append("&BetContent=").append(content)
			.append("&RealName=").append(userInfo.getRealName())
			.append("&IDCard=").append(userInfo.getIdcard())
			.append("&Phone=").append(userInfo.getPhoneno().trim())
			.append("&Sign=").append(md.toUpperCase());
			
			logger.error("送票内容："+stringBuffer.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			logger.error("送票拼串异常", e);
		}
		return null;
	}

	@Override
	protected TerminalType getTerminalType() {
		return TerminalType.T_ARUI;
	}

	
	/**
	 * 内容转换
	 * @param content
	 * @param lotteryType
	 * @return
	 */
	private String getContent(String content,LotteryType lotteryType,IVenderConfig anruiConfig){
		StringBuffer stringBuffer=new StringBuffer();
		String []matchs=content.split("\\/");
		int i=0;
		for(String match:matchs){
			String []mt=match.split("\\~");
			String matchId=getAnRuiMatchId(lotteryType,match.split("\\~")[0],(AnRuiConfig)anruiConfig);
			if(!StringUtils.isEmpty(matchId)){
				stringBuffer.append(matchId).append("~").append(mt[1]).append("~").append(mt[2]);
			}
			if(i!=matchs.length-1){
				stringBuffer.append("/");
			}
			i++;
		}
		logger.error("解析后内容",stringBuffer.toString());
		return stringBuffer.toString();
		
	}


	/**
	 * 由系统场次查询安瑞场次
	 * */
	protected  String getAnRuiMatchId(LotteryType lotteryType,String matchNum,AnRuiConfig config) {
			String footUrl=null;
			String key=null;
			if (LotteryType.getJclq().contains(lotteryType)){
				footUrl=config.getJclqMatch();
				key=anruijclq__key + matchNum;
			}else{
				footUrl=config.getJczqMatch();
				key=anruijczq__key + matchNum;
			}
			String matchId = redisCache.getString(key);
			if (StringUtil.isEmpty(matchId)) {
				String footStr=null;
				try {
					footStr = HTTPUtil.getResult(footUrl,CharsetConstant.CHARSET_UTF8);
				} catch (Exception e) {
					logger.error("安瑞获取赛事异常",e);
					return null;
				}
				List<HashMap<String, String>> mapList =null;
				try {
					mapList = XmlParse.getElementAttributeList("/", "Match", footStr);
				} catch (DocumentException e) {
					logger.error("解析赛事异常",e);
				}
				
				if(mapList!=null&&mapList.size()>0){
					for(HashMap<String,String>map:mapList){
					   if((matchNum).equals(map.get("GameNo"))){
						   String value=map.get("MatchID");
						   redisCache.saveString(key,12*60*60,value);
						   return value;
					   }
					}
				}
			}
			return  matchId;
			
	}
	    	
}
