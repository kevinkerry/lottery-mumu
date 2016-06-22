package com.lottery.pay.impl.xfwap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lottery.common.contains.pay.PayChannel;
import com.lottery.common.contains.pay.PayStatus;
import com.lottery.common.contains.pay.RechargeRequestData;
import com.lottery.common.contains.pay.RechargeResponseData;
import com.lottery.common.util.JsonUtil;
import com.lottery.core.domain.UserTransaction;
import com.lottery.pay.IPayConfig;
import com.lottery.pay.impl.AbstractRechargeProcess;
import com.lottery.pay.progress.xfwap.XfWapPay;
import com.lottery.pay.progress.xfwap.util.AES;
import com.lottery.pay.progress.xfwap.util.BeanToMapUtils;
import com.lottery.pay.progress.xfwap.util.HttpUtils;
import com.lottery.pay.progress.xfwap.util.MD5Helper;
import com.lottery.pay.progress.yeebao.util.DigestUtil;
import com.lottery.pay.progress.zfb.util.RSASignature;
@Service
public class XfWapRechargeProcess extends AbstractRechargeProcess {
	@Override
	public PayChannel getPayChannel() {
		return PayChannel.xfwap;
	}



    /**
     * 验证
     */
	@Override
	public boolean verifySign(String notifyStr) {
		Map<String,String> map=getMap(notifyStr);
	    String sValue;
		try {
			 List<String> keys = new ArrayList<String>(map.keySet());
			Map<String,String>mapStr=new HashMap<String, String>();
			 for (int i = 0; i < keys.size(); i++) {
		            String key = keys.get(i);
		            String value = map.get(key);
		            if("hmac".equals(key)||"type".equals(key)){
		            	continue;
		            }
		            mapStr.put(key, value);   
			 }
			sValue = RSASignature.createValueLinkString(mapStr);
			String sNewString=DigestUtil.hmacSign(sValue.toString(), getIPayConfig().getKey());
			if (map.get("hmac").equals(sNewString)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("前面验证失败",e);
		}
		return false;
	}


	@Override
	protected RechargeResponseData sign(IPayConfig payConfig, RechargeResponseData responseData, RechargeRequestData requestData) {
		String payUrl= "";
		try {
			// 获取签名
			Map<String,Object>map=getMap(payConfig,requestData,responseData);
			String sign = XfWapPay.getSign(map, payConfig.getKey());
			// 获取密文数据
			String data = BeanToMapUtils.getData(map, sign, payConfig.getKey());
			logger.error("data===="+data);
			data = java.net.URLEncoder.encode(data, "UTF-8");
			// 创建订单URL
			payUrl= payConfig.getRequestUrl()+ "?merchantId=" + payConfig.getPartner() + "&data=" + data;
		} catch (Exception e) {
			logger.error("拼装签名数据异常",e);
		}
		responseData.setResult(payUrl);
		return responseData;
	}

	public Map<String,Object>getMap(IPayConfig payConfig,RechargeRequestData requestData,RechargeResponseData responseData ){
		Map<String,Object>mapStr=new HashMap<String, Object>();
		mapStr.put("token",getToken(payConfig,requestData.getUserno()));
		mapStr.put("outOrderId",responseData.getOrderNo());
		mapStr.put("merchantId",payConfig.getPartner());
		mapStr.put("userId",requestData.getUserno());
		mapStr.put("amount" ,requestData.getAmount());
		mapStr.put("curType","CNY");
		mapStr.put("goodsName","充值");
		mapStr.put("merchantName" ,"充值");
		mapStr.put("returnURL",payConfig.getRequestUrl());
		mapStr.put("notifyURL",payConfig.getNoticeUrl());
		return mapStr;
	}
	
	public String  getToken(IPayConfig payConfig,String userId){
		Map<String,Object>mapStr=new HashMap<String, Object>();
		mapStr.put("merchantId",payConfig.getPartner());
		mapStr.put("userId",userId);
		String sign = MD5Helper.getSignature(mapStr,payConfig.getKey());
		String data = BeanToMapUtils.getData(mapStr, sign,payConfig.getKey());
		// getToken参数
		Map<String, String> getTokenMap = new HashMap<String, String>();
		getTokenMap.put("data", data);
		getTokenMap.put("merchantId", payConfig.getPartner());
		logger.error("showUrl{}",payConfig.getShowUrl());
		String result = HttpUtils.sendGetRequest(payConfig.getShowUrl(), getTokenMap, "UTF-8");
		logger.error("返回{}",result);
		String resultStr=(String) JsonUtil.transferJson2Map(result).get("data");
		String returnKV = AES.aesDecrypt(resultStr,payConfig.getKey());
		Map<String, String> returnMap = BeanToMapUtils.kvStringToMap(returnKV);
		logger.error("获取token返回{}",returnKV);
		if ("00".equals(returnMap.get("respCode"))) {
			String token = returnMap.get("token");
			return token;
		}
		return null;
	}
	public String notify(String notifyData) {
		logger.error("先锋通知返回{}",notifyData);
		    Map<String, String> map=getMap(notifyData);
			String out_trade_no = map.get("r6_Order");//订单号
			String trade_no =  map.get("r2_TrxId");//易宝交易号
			String trade_status = map.get("r1_Code");//支付结果
			String total_fee =  map.get("r3_Amt");//交易金额
			String noticeType=map.get("r9_BType");//通知类别
			try {
				// 成功
				if ("1".equals(trade_status)) {
					if ("1".equals(noticeType)) {//同步通知
						UserTransaction userTransaction = userTransactionService.get(out_trade_no);
						if(userTransaction!=null&&userTransaction.getStatus()==PayStatus.NOT_PAY.getValue()){
							userTransactionService.updateStatus(userTransaction.getId(),PayStatus.ALREADY_PAY.value);
						}
						return "0";
					} else if ("2".equals(noticeType)) {//异步通知
						this.rechargeResult(out_trade_no, trade_no, new BigDecimal(total_fee), true, "");
						return "2";
					}
				}
			} catch (Exception e) {
				logger.error("充值订单{}通知异常",out_trade_no,e);
				return "1";
			}
			return "1";
	}

	public static void main(String[] args) throws Exception {
			Map<String,Object>mapStr=new HashMap<String, Object>();
			mapStr.put("merchantId","MT10000000");
			mapStr.put("userId","123456");
			String sign = MD5Helper.getSignature(mapStr,"hmYB5Ue6OPoHsW2YX5VlaQ");
			String data = BeanToMapUtils.getData(mapStr, sign,"hmYB5Ue6OPoHsW2YX5VlaQ");

			// getToken参数
			Map<String, String> getTokenMap = new HashMap<String, String>();
			getTokenMap.put("data", data);
			getTokenMap.put("merchantId", "MT10000000");
			String result = HttpUtils.sendGetRequest("http://m.ucfpay.com/mobilepay-oneclick/oneClickOperate/getToken", getTokenMap, "UTF-8");
			System.out.println("获取token返回密文结果：" + result);
			String resultStr=(String) JsonUtil.transferJson2Map(result).get("data");
			
			String returnKV = AES.aesDecrypt(resultStr,"hmYB5Ue6OPoHsW2YX5VlaQ");
			System.out.println("获取token返回明文结果：" + returnKV);
			Map<String, String> returnMap = BeanToMapUtils.kvStringToMap(returnKV);

			if ("00".equals(returnMap.get("respCode"))) {
				String token = returnMap.get("token");
				System.out.println(token);
				mapStr=new HashMap<String, Object>();
				mapStr.put("token",token);
				mapStr.put("outOrderId","TY160516000008879589");
				mapStr.put("merchantId","MT10000000");
				mapStr.put("userId","123456");
				mapStr.put("amount" ,"100");
				mapStr.put("curType","CNY");
				mapStr.put("goodsName","充值");
				mapStr.put("merchantName" ,"充值");
				mapStr.put("returnURL","http://118.26.65.147/adapter/callback.jsp");
				mapStr.put("notifyURL","http://118.26.65.147/adapter/zfbWapNotify/sendNotify");
				
				sign = XfWapPay.getSign(mapStr, "hmYB5Ue6OPoHsW2YX5VlaQ");
				// 获取密文数据
				data = BeanToMapUtils.getData(mapStr, sign, "hmYB5Ue6OPoHsW2YX5VlaQ");
				data = java.net.URLEncoder.encode(data, "UTF-8");
				// 创建订单URL
				String payUrl= "http://m.ucfpay.com/mobilepay-oneclick/oneClickOperate/createOrder"+ "?merchantId=MT10000000&data=" + data;
			
			    System.out.println(payUrl);
			}
		}

}
