package com.lottery.pay.progress.xfwap;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.pay.IPayConfig;
import com.lottery.pay.progress.xfwap.util.XfWapUtil;
import com.lottery.pay.progress.yeebaowap.util.PaymobileUtils;

public class XfWapPay {


	protected static Logger logger = LoggerFactory.getLogger(XfWapPay.class);
	
	 /**
	    * 订单查询
	    * @param orderId
	    * @return
	    * @throws Exception 
	    */
		public static JSONObject queryOrderInfo(String orderId,IPayConfig  config) throws Exception{
			//把请求参数打包成数组
			TreeMap<String, Object> treeMap	= new TreeMap<String, Object>();
			treeMap.put("orderid", 	orderId);
			//第一步 生成AESkey及encryptkey
			String AESKey		= PaymobileUtils.buildAESKey();
			String encryptkey	= PaymobileUtils.buildEncyptkey(AESKey,config.getPublicKey());
			//第二步 生成data
			String data			= PaymobileUtils.buildData(treeMap, AESKey,config.getSeller(),config.getPrivateKey());
			//第三步 http请求，订单查询接口的请求方式为GET
			Map<String, String> responseMap	= PaymobileUtils.httpGet(config.getSearchUrl(), config.getSeller(), data, encryptkey);
			System.out.println("请求串：" + config.getSearchUrl() + "?merchantaccount=" + config.getSeller() 
	   				+ "&data=" + URLEncoder.encode(data, "utf-8") 
	 				+ "&encryptkey=" + URLEncoder.encode(encryptkey, "utf-8"));
			
			//第五步 请求成功，则获取data、encryptkey，并将其解密
			String data_response					= responseMap.get("data");
			String encryptkey_response				= responseMap.get("encryptkey");
			JSONObject responseDataMap	= PaymobileUtils.decrypt(data_response, encryptkey_response,config.getPrivateKey());

			logger.error("请求返回的明文参数：" + responseDataMap);
	        return  responseDataMap;
		}
	
		
		public static String getSign(Map<String, Object> map, String key) throws Exception {
			// 获取签名
			Map<String, Object> signMap = new HashMap<String, Object>();
			signMap.putAll(map);
			String sign = XfWapUtil.getSignature(signMap, key);
			return sign;
		}

		
}
