/**
 * Copyright: Copyright (c)2014
 * Company: UCFGROUP
 */
package com.lottery.pay.progress.xfwap.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MD5Helper {													

	
	public static String getSignature(Map<String,Object> params, String secret)
	{
	    // 先将参数以其参数名的字典序升序进行排序
	    Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
	    Set<Entry<String, Object>> entrys = sortedParams.entrySet();
	 
	    // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
	    StringBuilder basestring = new StringBuilder();
	    for (Entry<String, Object> param : entrys) {
	        basestring.append(param.getKey()).append("=").append(param.getValue()).append("&");
	    }
	    if(secret != null && secret != ""){
	    	basestring.append("key").append("=").append(secret);
	    }else{
	    	basestring = new StringBuilder(basestring.substring(0, basestring.length()-1));
	    }
	    // 使用MD5对待签名串求签
	    byte[] bytes = null;
	    try {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        try {
				bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    } catch (GeneralSecurityException ex) {
	        ex.printStackTrace();
	    }
	    String sign = HexUtils.encodeHexStr(bytes, false);
	    return sign;
	}

	
}
