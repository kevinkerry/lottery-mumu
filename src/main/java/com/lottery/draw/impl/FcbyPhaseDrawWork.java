package com.lottery.draw.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.lottery.LotteryType;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.util.DES3;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.IdGeneratorService;
import com.lottery.core.domain.LotteryPhase;
import com.lottery.draw.AbstractVenderPhaseDrawWorker;
import com.lottery.draw.LotteryDraw;
import com.lottery.ticket.IPrizeNumConverter;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.fcby.FcbyConverter;
@Service
public class FcbyPhaseDrawWork extends AbstractVenderPhaseDrawWorker {
	protected final static Logger logger = LoggerFactory.getLogger(FcbyPhaseDrawWork.class);
	protected static Map<Integer, IPrizeNumConverter> prizeNumConverterMap = new HashMap<Integer, IPrizeNumConverter>();
	@Autowired
	IdGeneratorService igenGeneratorService;
	protected String openPrizeNum="102";
	@Autowired
	private FcbyConverter fcbyConverter;	
	@Override
	protected LotteryDraw get(IVenderConfig config,
			Integer lotteryType, String phase) {
		if(config==null){
			return null;
		}
		String lotno = fcbyConverter.convertLotteryType(LotteryType.getLotteryType(lotteryType));
		String message=getElement(lotno,phase,config);
		String returnStr="";
		try {
			returnStr = HTTPUtil.post(config.getCheckUrl(),message ,CharsetConstant.CHARSET_UTF8);
		} catch (Exception e) {
			logger.error("开奖结果接口返回异常" + e);
		}
		XmlParse xmlParse=null;
		try {
			xmlParse = new XmlParse(returnStr, "ctrl", "body", "");
			String result = xmlParse.getElementAttributeText("result", 1);
            if(ErrorCode.Success.getValue().equals(result)){
               String bodyContent =  xmlParse.getBodyText("body");
         	   //解密
         	   String desContent = DES3.des3DecodeCBC(config.getPasswd(),bodyContent);
         	   //处理结果
         	   LotteryDraw lotteryDraw= dealResult(desContent,lotteryType);
         	   return lotteryDraw;
            }
		} catch (Exception e) {
			logger.error("处理开奖结果异常"+e);
		}
		return null;
	}

	@Override
	protected TerminalType getTerminalType() {
		return TerminalType.T_FCBY;
	}

	/**
	 * 查奖结果处理
	 * @param desContent
	 * @param count
	 * @return
	 */
	private LotteryDraw dealResult(String desContent,int lotteryType){
		LotteryDraw lotteryDraw=new LotteryDraw();
		XmlParse bodyParse=null;
		try {
			bodyParse = new XmlParse(desContent,"response","", "");
			if(bodyParse!=null){
				Element el = bodyParse.getHeaderElement();
				Iterator<?> e3=el.elementIterator("result");
				if(e3.hasNext()) {
					Element obj = (Element) e3.next();
				    String issue=obj.attributeValue("issue");
				    String prizecode=obj.attributeValue("prizecode");
				    if(StringUtils.isNotBlank(prizecode)){
				    	 IPrizeNumConverter prConverter=prizeNumConverterMap.get(lotteryType);
				    	 lotteryDraw.setLotteryType(lotteryType);
				    	 lotteryDraw.setPhase(issue);
						 lotteryDraw.setWindCode(prConverter.convert(prizecode));
						 return lotteryDraw;
				    }
				    return null;
			   }
			}
		} catch (DocumentException e) {
			logger.error("处理开奖结果异常");
		}
		return null;
	}
	private String getElement(String lotteryNo,String phase, IVenderConfig fcbyConfig) {
		String md = "";
		XmlParse xmlParse = null;
		try {
			xmlParse=XmlParse.addFcbyHead(fcbyConfig.getAgentCode(),openPrizeNum,igenGeneratorService.getMessageId());
			XmlParse bodyParse = new XmlParse("body");
			HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
			bodyAttr.put("lotno", lotteryNo);
			bodyAttr.put("issue", phase);
			bodyParse.addBodyElementAndAttribute("querystring", "",bodyAttr);
			String desContent = "";
			try {
				desContent = DES3.des3EncodeCBC(fcbyConfig.getPasswd(),bodyParse.asXML());
				md = MD5Util.toMd5(fcbyConfig.getPasswd() + desContent);
			} catch (Exception e) {
				logger.error("加密异常" + e.getMessage());
			}
			xmlParse.addHeaderElement("md", md);
			xmlParse.addBodyElement("body", desContent);
			return "msg=" + xmlParse.asXML();
		} catch (Exception e) {
			logger.error("开奖号码查询异常" + e.getMessage());
		}
		return null;
	}

	static{
		//开奖号码转换
		////双色球七乐彩 大乐彩
		IPrizeNumConverter qlcConverter=new IPrizeNumConverter() {
			@Override
			public String convert(String content) {
				return content;
			}
		}; 
		
		prizeNumConverterMap.put(LotteryType.CJDLT.getValue(),qlcConverter);
		prizeNumConverterMap.put(LotteryType.PL3.getValue(), qlcConverter);
		prizeNumConverterMap.put(LotteryType.PL5.getValue(),qlcConverter);
		prizeNumConverterMap.put(LotteryType.QXC.getValue(),qlcConverter);
	}

	@Override
	protected List<LotteryPhase> getOnSalePhaseList(int lotteryType, IVenderConfig venderConfig) {
	
		return null;
	}


}
