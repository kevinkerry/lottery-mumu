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
public class JclqResult implements IGetDataFrom  {
	private static Logger logger=LoggerFactory.getLogger(JclqResult.class);
	@Override
	public JSONArray perfrom(Map<String, String> reqMap, IVenderConfig config) {
		return queryGGList(reqMap.get("phaseNo"), config);
	}
	
	/**
	 * @param day 格式 yyyyMMdd（day和endTime必须选一个填写）
	 * @param playId
	 */
	public JSONArray queryGGList(String day, IVenderConfig config) {
		try {
			//0：胜负 02：让分胜负 03:胜分差  04：大小分 
			XmlParse xmlParse=ZchConfig.addZchHead(config.getAgentCode(), "020");
			HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
			bodyAttr.put("day", day);
			bodyAttr.put("pollId", "02");
			bodyAttr.put("playId", "00");
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
					rejo.put("matchNum", day + sn);
					rejo.put("resultFrom", "中彩汇");
					rejo.put("state", RaceStatus.CANCEL.value);
					ja.add(rejo);
				}else{
					Element matchResult = match.element("matchResult");//当天赛事场次编号
					//String mainTeamHalfScore = matchResult.attributeValue("mainTeamHalfScore");//主队半场比分
					//String guestTeamHalfScore = matchResult.attributeValue("guestTeamHalfScore");//客队半场比分
					String mainTeamScore = matchResult.attributeValue("mainTeamScore");//主队全场比分
					String guestTeamScore = matchResult.attributeValue("guestTeamScore");//客队全场比分
					String finalScore = mainTeamScore+":"+guestTeamScore;
					
					JSONObject rejo = new JSONObject();
					rejo.put("matchNum", day + sn);
					rejo.put("finalScore", finalScore);
					rejo.put("resultFrom", "中彩汇");
					rejo.put("state", RaceStatus.CLOSE.value);
					ja.add(rejo);
				}
			}
 			return ja;
		} catch (Exception e) {
			logger.error("获取竞彩篮球赛果出错",e);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param day 格式 yyyyMMdd
	 * @param sn 赛事场次编号
	 */
	public JSONArray queryGG(String day, String sn, IVenderConfig config){
		JSONArray rejr = null;
		try {
			//0：胜负 02：让分胜负 03:胜分差  04：大小分 
			String[] playIds = new String[]{"01", "02", "04"};
			JSONObject rejo = null;
			for (String playId : playIds) {
			    XmlParse xmlParse=ZchConfig.addZchHead(config.getAgentCode(),"017");
				HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
				bodyAttr.put("day", day);
				bodyAttr.put("lotteryId", "201");
				bodyAttr.put("sn", sn);
				bodyAttr.put("playId", playId);
				//01,单关；02,过关
				bodyAttr.put("pollId", "02");
				xmlParse.addBodyElementAndAttribute("query", "", bodyAttr);
				String	md = MD5Util.toMd5(config.getAgentCode() + config.getKey() + xmlParse.getBodyElement().asXML());
				xmlParse.addHeaderElement("digest", md);
				String resultStr = HTTPUtil.sendPostMsg(config.getRequestUrl(),"msg="+xmlParse.asXML());
				Element response = XmlParse.getElement("body/", "response", resultStr);
				String code = response.attributeValue("code");
				if(!"0000".equals(code)){
					continue;
				}
				
				Element match = response.element("match");
				//当天赛事场次编号
				//状态 1销售中,2已截止,3已开奖,4已返奖,5取消,6异常,7已算奖
				String status = match.attributeValue("status");
				//只有开奖的才有赛果
				if(StringUtil.isEmpty(status) || Integer.parseInt(status) < 3){
					return null;
				}
				
				//胜分差赔率
				String winNegaDiffSp = match.elementText("winNegaDiffSp");
				if(rejo == null){
					rejo = new JSONObject();
					rejo.put("finalScore", "");
					rejo.put("prizeSf", "");//胜负奖金值,浮动奖金投注的开奖奖金
					rejo.put("prizeDxf", "");//大小分奖金值,浮动奖金投注的开奖奖金
					rejo.put("prizeRfsf", "");//让分胜负奖金值,浮动奖金投注的开奖奖金
					rejo.put("preCast", "");//浮动奖金投注预设总分
					rejo.put("letBall", "");//浮动奖金投注让分
				}

				Element matchResult = match.element("matchResult");
				if("01".equals(playId)){
					String winOrNegaSp = match.elementText("winOrNegaSp");
					rejo.put("prizeSf", winOrNegaSp);//胜负奖金值,浮动奖金投注的开奖奖金
				}else if("02".equals(playId)){
					String letWinOrNegaSp = match.elementText("letWinOrNegaSp");
					rejo.put("prizeRfsf", letWinOrNegaSp);//让分胜负奖金值,浮动奖金投注的开奖奖金
					//让球
					String letBall = matchResult.attributeValue("letBall");
					rejo.put("letBall", letBall);//浮动奖金投注让分
				}else if("04".equals(playId)){
					String bigOrLittleSp = match.elementText("bigOrLittleSp");
					rejo.put("prizeDxf", bigOrLittleSp);//大小分奖金值,浮动奖金投注的开奖奖金
					//preCast
					String preCast = matchResult.attributeValue("preCast");
					rejo.put("preCast", preCast);//浮动奖金投注预设总分
				}
				
				if(!StringUtil.isEmpty(rejo.getString("finalScore"))){
					continue;
				}
				//主队半场比分
				String mainTeamHalfScore = matchResult.attributeValue("mainTeamHalfScore");
				//客队半场比分
				String guestTeamHalfScore = matchResult.attributeValue("guestTeamHalfScore");
				//主队全场比分
				String mainTeamScore = matchResult.attributeValue("mainTeamScore");
				//客队全场比分
				String guestTeamScore = matchResult.attributeValue("guestTeamScore");
				String finalScore = "";
				if(!StringUtil.isEmpty(mainTeamScore) || !StringUtil.isEmpty(guestTeamScore)){
					finalScore = mainTeamScore+":"+guestTeamScore;
				}
				rejo.put("finalScore", finalScore); //最终比分
			}
			rejr= new JSONArray();
			rejr.add(rejo);
		} catch (Exception e) {
			logger.error("获取竞彩篮球赛果出错",e);
		}
		return rejr;
	}
}
