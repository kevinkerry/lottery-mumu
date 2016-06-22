package com.lottery.draw.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lottery.common.contains.lottery.PhaseStatus;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.IdGeneratorService;
import com.lottery.core.SeqEnum;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.draw.AbstractVenderPhaseDrawWorker;
import com.lottery.draw.LotteryDraw;
import com.lottery.ticket.IPrizeNumConverter;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.huai.HuAiConverter;
@Service
public class HuAiPhaseDrawWork extends AbstractVenderPhaseDrawWorker {
	protected final static Logger logger = LoggerFactory.getLogger(HuAiPhaseDrawWork.class);
	protected static Map<Integer, IPrizeNumConverter> prizeNumConverterMap = new HashMap<Integer, IPrizeNumConverter>();
	@Autowired
	IdGeneratorService igenGeneratorService;
	protected String openPrizeNum="103";
	@Autowired
	private HuAiConverter huAiConverter;	
	@Override
	protected LotteryDraw get(IVenderConfig config,
			Integer lotteryType, String phase) {
		if(config==null){
			return null;
		}
		phase="20"+phase;
		String lotno = huAiConverter.convertLotteryType(LotteryType.getLotteryType(lotteryType));
		String message=getElement(lotno,phase,config);
		String returnStr="";
		try {
			returnStr = HTTPUtil.sendPostMsg(config.getRequestUrl(), message);
		} catch (Exception e) {
			logger.error("互爱开奖结果接口返回异常",e);
		}
		try {
         	 //处理结果
         	 LotteryDraw lotteryDraw= dealResult(returnStr,lotteryType);
         	 return lotteryDraw;
		} catch (Exception e) {
			logger.error("处理开奖结果异常"+e);
		}
		return null;
	}

	@Override
	protected TerminalType getTerminalType() {
		return TerminalType.T_HUAI;
	}

	/**
	 * 查奖结果处理
	 * @param desContent
	 * @param
	 * @return
	 */
	private LotteryDraw dealResult(String desContent,int lotteryType){
		LotteryDraw lotteryDraw=new LotteryDraw();
		lotteryDraw.setResponsMessage(desContent);
		try {
			desContent="<response>"+desContent+"</response>";
			HashMap<String, String> mapResult = XmlParse.getElementText("/", "ActionResult", desContent);
			String code = mapResult.get("reCode");
			String msg = mapResult.get("reMessage");
			if ("0".equals(code)) {
				HashMap<String, String> reResult = XmlParse.getElementAttribute("/ActionResult/reValue/", "Issue", desContent);
				    String issue=reResult.get("LotIssue");
				    String prizecode=reResult.get("BonusCode");
				    if(StringUtils.isNotBlank(prizecode)){
				    	 IPrizeNumConverter prConverter=prizeNumConverterMap.get(lotteryType);
				    	 lotteryDraw.setLotteryType(lotteryType);
				    	 lotteryDraw.setPhase(issue);
						 lotteryDraw.setStatus(PhaseStatus.prize_open.value);
						 lotteryDraw.setWindCode(prConverter.convert(prizecode));
						 return lotteryDraw;
				    }
				    return null;
			 }else{
				 logger.error("code:{},msg:{}",code,msg);
			 }
		} catch (DocumentException e) {
			logger.error("处理开奖结果异常",e);
		}
		return null;
	}
	private String getElement(String lotteryNo,String phase, IVenderConfig huaiConfig) {
		StringBuffer stringBuffer=new StringBuffer();
		StringBuilder  exParam=new StringBuilder();
		exParam.append("LotID=").append(lotteryNo).append("_LotIssue=").append(phase);
		String messageId=igenGeneratorService.getMessageId(SeqEnum.vender_gzcp_messageid);
		stringBuffer.append("exAgent=").append(huaiConfig.getAgentCode())
		.append("&exAction=").append(openPrizeNum).append("&exMsgID="+messageId);
		String signMsg=MD5Util.toMd5(huaiConfig.getAgentCode()+openPrizeNum+messageId+exParam.toString()+huaiConfig.getKey());
		stringBuffer.append("&exParam=").append(exParam.toString()).append("&exSign=").append(signMsg);
		return stringBuffer.toString();
	}

	static{
		//开奖号码转换
		//11x5
		IPrizeNumConverter syx5Converter=new IPrizeNumConverter() {
			@Override
			public String convert(String content) {
				return content;
			}
		}; 
		
		prizeNumConverterMap.put(LotteryType.SD_11X5.getValue(),syx5Converter);
		prizeNumConverterMap.put(LotteryType.GD_11X5.getValue(),syx5Converter);
	}

	@Override
	protected List<LotteryPhase> getOnSalePhaseList(int lotteryType, IVenderConfig venderConfig) {
	
		return null;
	}


	
}
