package com.lottery.draw.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lottery.common.contains.YesNoStatus;
import com.lottery.common.contains.lottery.TerminalType;
import com.lottery.common.util.HTTPUtil;
import com.lottery.common.util.MD5Util;
import com.lottery.core.domain.terminal.MemberAccount;
import com.lottery.core.domain.terminal.MemberAccountPK;
import com.lottery.draw.AbstractVenderBalanceWork;
import com.lottery.ticket.IVenderConfig;
import com.lottery.ticket.sender.worker.XmlParse;
import com.lottery.ticket.vender.impl.zch.ZchConfig;
@Component("ZhongCaiHui_account")
public class ZchAccountDrawWork extends AbstractVenderBalanceWork{
	private final Logger logger=LoggerFactory.getLogger(getClass().getName());
	private static String accountMoney="015";
    protected TerminalType getTerminalType() {
		return TerminalType.T_ZCH;
	}
    protected MemberAccount getAccount(IVenderConfig config) {
		if(config==null){
			return null;
		}
		String message=getElement(config);
		String returnStr="";
		try {
			returnStr = HTTPUtil.sendPostMsg(config.getRequestUrl(),message);
		} catch (Exception e) {
			logger.error("中彩汇查询账户余额接口返回异常" + e);
		}
	
		 //处理结果
		if(StringUtils.isNotBlank(returnStr)){
			return dealResult(returnStr,config);
        }
		return null;
	}


	/**
	 * 账户余额结果处理
	 * @param desContent
	 * @param count
	 * @return
	 */
	private MemberAccount dealResult(String desContent,IVenderConfig config){
		try {
			Map<String,String> map=XmlParse.getElementAttribute("body/", "response", desContent);
			if("0000".equals(map.get("code"))){
				Map<String,String> mapParam=XmlParse.getElementAttribute("body/response/", "account", desContent);
				if(mapParam!=null) {
					MemberAccount memberAccount=new MemberAccount();
					MemberAccountPK memberAccountPK=new MemberAccountPK();
					memberAccountPK.setTerminalType(TerminalType.T_ZCH.getValue());
				    memberAccountPK.setAgentCode(config.getAgentCode());
				    memberAccount.setId(memberAccountPK);
				    memberAccount.setSmsFlag(YesNoStatus.yes.value);
				    String account=mapParam.get("amount");//5,563,364.72
				    memberAccount.setBalance(new BigDecimal(account.replace(",", "")));
				    memberAccount.setTerminalName(TerminalType.T_ZCH.getName());
				    return memberAccount;
			   }
			}
		} catch (Exception e) {
			logger.error("处理账户余额异常{}",e);
		}
		return null;
	}
	private String getElement(IVenderConfig zchConfig) {
		XmlParse xmlParse = ZchConfig.addZchHead(zchConfig.getAgentCode(),accountMoney);
		Element bodyeElement=xmlParse.getBodyElement();
		bodyeElement.addText("");
		String md = MD5Util.toMd5(zchConfig.getAgentCode()+zchConfig.getKey() + xmlParse.getBodyElement().asXML());
		xmlParse.addHeaderElement("digest", md);
		return "msg=" + xmlParse.asXML();
	}

}
