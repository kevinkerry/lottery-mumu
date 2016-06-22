package com.lottery.draw.impl;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.util.DES3;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.IdGeneratorService;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.sender.worker.XmlParse;
@Service
public class FcbyAccountDrawWork {
	protected final Logger logger = LoggerFactory.getLogger(FcbyAccountDrawWork.class);
	@Autowired
	IdGeneratorService igenGeneratorService;
	protected String queryAccount="105";
	
	protected Map<String,Object> getAccount(IVenderConfig config) {
		if(config==null){
			return null;
		}
		String message=getElement(config);
		String returnStr="";
		try {
			returnStr = HTTPUtil.post(config.getCheckUrl(),message ,CharsetConstant.CHARSET_UTF8);
		} catch (Exception e) {
			logger.error("查询账户接口返回异常" + e);
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
         	  return dealResult(desContent);
            }
		} catch (Exception e) {
			logger.error("查询账户解析异常"+e);
		}
		return null;
	}

	protected TerminalType getTerminalType() {
		return TerminalType.T_FCBY;
	}

	
	/**
	 * 查询账户结果处理
	 * @param desContent
	 * @param count
	 * @return
	 */
	private Map<String,Object> dealResult(String desContent){
		Map<String,Object> lotteryMap=new HashMap<String, Object>();
		try {
			Element el=XmlParse.getElement("body/response/","result",desContent);
		    String leftMoney=el.attributeValue("balance");
		    String freebalance=el.attributeValue("freebalance");
			lotteryMap.put("leftMoney", leftMoney);
			lotteryMap.put("freebalance", freebalance);
			return lotteryMap;
		} catch (DocumentException e) {
			logger.error("查询账户结果异常");
		}
		return null;
	}
	/**
	 * 拼串
	 * @param fcbyConfig
	 * @return
	 */
	private String getElement(IVenderConfig fcbyConfig) {
		String md = "";
		XmlParse xmlParse = null;
		try {
			xmlParse=XmlParse.addFcbyHead(fcbyConfig.getAgentCode(),queryAccount,igenGeneratorService.getMessageId());
			XmlParse bodyParse = new XmlParse("body");
			HashMap<String, Object> bodyAttr = new HashMap<String, Object>();
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
			logger.error("账户查询异常" + e.getMessage());
		}
		return null;
	}

	
}
