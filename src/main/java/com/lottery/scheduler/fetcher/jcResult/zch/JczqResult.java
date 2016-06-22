package com.lottery.scheduler.fetcher.jcResult.zch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lottery.common.contains.lottery.RaceStatus;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.common.util.StringUtil;
import com.lottery.scheduler.fetcher.IGetDataFrom;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.zch.ZchConfig;

@Component
public class JczqResult implements IGetDataFrom  {
	protected final Logger logger=LoggerFactory.getLogger(getClass());
	@Override
	public JSONArray perfrom(Map<String, String> reqMap, IVenderConfig config) {
		String phaseNo = reqMap.get("phaseNo");
		return queryGGList(phaseNo, config);
	}
	
	/**
	 * 获取过关赛果
	 * @param day 格式 yyyyMMdd（day和endTime必须选一个填写）
	 */
	public JSONArray queryGGList(String day, IVenderConfig config) {
		try {
			XmlParse xmlParse=ZchConfig.addZchHead(config.getAgentCode(), "019");
			HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
			bodyAttr.put("day", day);
			bodyAttr.put("playId", "01");
			bodyAttr.put("pollId", "02");//poll 01,单关；02,过关
			xmlParse.addBodyElementAndAttribute("query", "", bodyAttr);
			String	md = MD5Util.toMd5(config.getAgentCode() + config.getKey() + xmlParse.getBodyElement().asXML());
			xmlParse.addHeaderElement("digest", md);
			String resultStr = HTTPUtil.sendPostMsg(config.getRequestUrl(),"msg="+xmlParse.asXML());
			Element response = XmlParse.getElement("body/", "response", resultStr);
			String code = response.attributeValue("code");
			if(!"0000".equals(code)){
				return null;
			}
			Element matchList = response.element("matchList");
			Iterator<Element> matchs = matchList.elementIterator("match");
			JSONArray ja  = new JSONArray();
 			while (matchs.hasNext()) {
				Element match = matchs.next();
				String sn = match.attributeValue("sn");
				//状态 1销售中,2已截止,3已开奖,4已返奖,5取消,6异常,7已算奖
				String status = match.attributeValue("status");
				//只有开奖的才有赛果
				if(StringUtil.isEmpty(status) || Integer.parseInt(status) < 3){
					continue;
				}else if("5".equals(status)){
					JSONObject rejo = new JSONObject();
					rejo.put("matchNum", day+sn);
					rejo.put("resultFrom", "中彩汇");
					rejo.put("state", RaceStatus.CANCEL.value);
					ja.add(rejo);
				}else{
					Element matchResult = match.element("matchResult");
					String mainTeamHalfScore = matchResult.attributeValue("mainTeamHalfScore");//主队半场比分
					String guestTeamHalfScore = matchResult.attributeValue("guestTeamHalfScore");//客队半场比分
					String mainTeamScore = matchResult.attributeValue("mainTeamScore");//主队全场比分
					String guestTeamScore = matchResult.attributeValue("guestTeamScore");//客队全场比分
					String firstHalf = "";
					if(!StringUtil.isEmpty(mainTeamHalfScore) && !StringUtil.isEmpty(guestTeamHalfScore)){
						firstHalf = mainTeamHalfScore+":"+guestTeamHalfScore;
					}
					String finalScore = "";
					if(!StringUtil.isEmpty(mainTeamScore) && !StringUtil.isEmpty(guestTeamScore)){
						finalScore =  mainTeamScore+":"+guestTeamScore;
					}
					String secondHalf = "";
					if(!StringUtil.isEmpty(mainTeamScore) && !StringUtil.isEmpty(mainTeamHalfScore)
							&& !StringUtil.isEmpty(guestTeamScore) && !StringUtil.isEmpty(guestTeamHalfScore)){
						secondHalf = (Integer.parseInt(mainTeamScore)-Integer.parseInt(mainTeamHalfScore))+
						":"+(Integer.parseInt(guestTeamScore)-Integer.parseInt(guestTeamHalfScore));
					}
					
					JSONObject rejo = new JSONObject();
					rejo.put("matchNum", day+sn);
					rejo.put("firstHalf", firstHalf);
					rejo.put("secondHalf", secondHalf);
					rejo.put("finalScore",finalScore);
					rejo.put("resultFrom", "中彩汇");
					rejo.put("state", RaceStatus.CLOSE.value);
					ja.add(rejo);
				}
			}
 			return ja;
		} catch (Exception e) {
			logger.error("获取竞彩足球赛果出错", e);
		}
		return null;
	}
	
	
	/**
	 * 过关查询
	 * @param day 格式 yyyyMMdd
	 * @param sn 赛事场次编号
	 */
	private JSONArray queryGG(String day, String sn, IVenderConfig config){
		JSONArray jr = null;
		try {
		    XmlParse xmlParse=ZchConfig.addZchHead(config.getAgentCode(),"016");
			HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
			bodyAttr.put("day", day);
			bodyAttr.put("lotteryId", "200");
			bodyAttr.put("sn", sn);
			bodyAttr.put("playId", "00");//过关02 传什么都行
			bodyAttr.put("pollId", "02");//poll 01,单关；02,过关
			xmlParse.addBodyElementAndAttribute("query", "", bodyAttr);
			String	md = MD5Util.toMd5(config.getAgentCode() + config.getKey() + xmlParse.getBodyElement().asXML());
			xmlParse.addHeaderElement("digest", md);
			String resultStr = HTTPUtil.sendPostMsg(config.getRequestUrl(), "msg="+xmlParse.asXML());
			if(StringUtil.isEmpty(resultStr)){
				logger.error("访问返回为空url:{}.msg{}", config.getRequestUrl(), xmlParse.asXML());
				return null;
			}
			Element response = XmlParse.getElement("body/", "response", resultStr);
			String code = response.attributeValue("code");
			if(!"0000".equals(code)){
				return null;
			}
			Element match = response.element("match");
			//状态 1销售中,2已截止,3已开奖,4已返奖,5取消,6异常,7已算奖
			String status = match.attributeValue("status");
			//只有开奖的才有赛果
			if(StringUtil.isEmpty(status) || Integer.parseInt(status) < 3){
				return null;
			}
			Element matchResult = match.element("matchResult");
			//让分胜平负赔率
			String winOrNegaSp = match.elementText("winOrNegaSp");
			if(StringUtil.isEmpty(winOrNegaSp)){
				logger.error("竞彩足球 未获取到让分胜平负赔率 期号:{},场次:{}",new Object[]{day, sn});
				return null;
			}
			
			//进球赔率
			String totalGoalSp = match.elementText("totalGoalSp");
			if(StringUtil.isEmpty(totalGoalSp)){
				logger.error("竞彩足球 未获取到进球赔率 期号:{},场次:{}",new Object[]{day, sn});
				return null;
			}
			
			//半场胜平负赔率
			String halfCourtSp = match.elementText("halfCourtSp");
			if(StringUtil.isEmpty(halfCourtSp)){
				logger.error("竞彩足球 未获取到半场胜平负赔率 期号:{},场次:{}",new Object[]{day, sn});
				return null;
			}
			
			//胜平负赔率
			String spfWinOrNegaSp = match.elementText("spfWinOrNegaSp");
			if(StringUtil.isEmpty(spfWinOrNegaSp)){
				logger.error("竞彩足球 未获取到胜平负赔率 期号:{},场次:{}",new Object[]{day, sn});
				return null;
			}
			
			//主队半场比分
			String mainTeamHalfScore = matchResult.attributeValue("mainTeamHalfScore");
			//客队半场比分
			String guestTeamHalfScore = matchResult.attributeValue("guestTeamHalfScore");
			//主队全场比分
			String mainTeamScore = matchResult.attributeValue("mainTeamScore");
			//客队全场比分
			String guestTeamScore = matchResult.attributeValue("guestTeamScore");
			
			String firstHalf = "";
			if(!StringUtil.isEmpty(mainTeamHalfScore) && !StringUtil.isEmpty(guestTeamHalfScore)){
				firstHalf = mainTeamHalfScore+":"+guestTeamHalfScore;
			}
			
			String finalScore = "";
			if(!StringUtil.isEmpty(mainTeamScore) && !StringUtil.isEmpty(guestTeamScore)){
				finalScore =  mainTeamScore+":"+guestTeamScore;
			}
			
			String secondHalf = "";
			if(!StringUtil.isEmpty(mainTeamScore) && !StringUtil.isEmpty(mainTeamHalfScore)
					&& !StringUtil.isEmpty(guestTeamScore) && !StringUtil.isEmpty(guestTeamHalfScore)){
				secondHalf = (Integer.parseInt(mainTeamScore)-Integer.parseInt(mainTeamHalfScore))+
				":"+(Integer.parseInt(guestTeamScore)-Integer.parseInt(guestTeamHalfScore));
			}
			
			JSONObject rejo = new JSONObject();
			rejo.put("prizeSpfWrq", spfWinOrNegaSp);
			rejo.put("prizeBqc", halfCourtSp);
			rejo.put("prizeJqs", totalGoalSp);
			rejo.put("prizeSpf", winOrNegaSp);
			rejo.put("secondHalf", secondHalf);
			rejo.put("finalScore",finalScore);
			rejo.put("firstHalf", firstHalf);
			jr = new JSONArray();
			jr.add(rejo);
		} catch (Exception e) {
			logger.error("获取竞彩足球赛果出错",e);
		}
		return jr;
	}
	
}
