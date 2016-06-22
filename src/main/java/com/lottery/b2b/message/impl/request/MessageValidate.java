package com.lottery.b2b.message.impl.request;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.b2b.message.IExternalMessage;
import com.lottery.b2b.message.IExternalResponseMessage;
import com.lottery.b2b.message.impl.response.ExternalResponseMessage;
import com.lottery.common.contains.CharsetConstant;
import com.lottery.common.contains.ErrorCode;
import com.lottery.common.contains.YesNoStatus;
import com.lottery.common.util.CoreStringUtils;
import com.lottery.common.util.DESCoder;
import com.lottery.core.domain.merchant.Merchant;
import com.lottery.core.service.merchant.MerchantService;
@Service
public class MessageValidate {
private final Logger logger=LoggerFactory.getLogger(getClass().getName());
	@Autowired
	protected MerchantService merchantService;
	
	public IExternalResponseMessage validate(IExternalMessage message,String ip){
		ExternalResponseMessage responseMessage=new ExternalResponseMessage();
		String userno=message.getMerchant();
		String messageId=message.getMessageId();
		String command=message.getCommand();
		String timestamp=message.getTimestamp();
		String sign=message.getSignature();
		Merchant merchant=merchantService.get(userno);
	
		if(merchant==null){
			responseMessage.setErrorCode(ErrorCode.no_user);
			logger.error("{}账号不存在,messageId={}",new Object[]{userno,messageId});
			return responseMessage;
		}
		if(merchant.getStatus()==YesNoStatus.no.value){
			responseMessage.setErrorCode(ErrorCode.merchant_stop);
			logger.error("{}账号停用,messageId={}",new Object[]{userno,messageId});
			return responseMessage;
		}
		String ipString=merchant.getIp();
		if((StringUtils.isBlank(ipString)||!ipString.contains(ip))){
			responseMessage.setErrorCode(ErrorCode.ip_error);
			logger.error("用户:{},ip被拒绝,信任ip为 :{},访问ip为:{},messageId={}",new Object[]{userno,ipString,ip,messageId});
			return responseMessage;
		}
		String messageBody=message.getMessageBody();
		String key=merchant.getSecretKey();
		String md5Str=command+timestamp+userno+key;
		String md5=CoreStringUtils.md5(md5Str, CharsetConstant.CHARSET_UTF8);
		if(!md5.equals(sign)){
			responseMessage.setErrorCode(ErrorCode.user_passwd_error);
			logger.error("用户:{},加密错误,传入的加密为:{},本平台加密为:{},原始串为:{},key={},messageId={}",new Object[]{userno,sign,md5,md5Str,key,messageId});
			return responseMessage;
		}
		String body=null;
		try{
			body=DESCoder.desDecrypt(messageBody, key);
		}catch(Exception e){
			logger.error("解密出错",e);
		}
		if(StringUtils.isBlank(body)){
			responseMessage.setErrorCode(ErrorCode.des_error);
			logger.error("用户:{}解密错,原始串为:{},key={},messageId={}",new Object[]{userno,messageBody,key,messageId});
			return responseMessage;
		}
		logger.info("messageid={},解密后的字符串是：{}",messageId,body);
		responseMessage.setMerchant(userno);
		responseMessage.setCommand(command);
		responseMessage.setMessageId(messageId);
		responseMessage.setErrorCode(ErrorCode.Success);
		responseMessage.setMessageBody(body);
		responseMessage.setKey(key);
		responseMessage.setCreditBalance(merchant.getCreditBalance());
		return responseMessage;
	}
}
