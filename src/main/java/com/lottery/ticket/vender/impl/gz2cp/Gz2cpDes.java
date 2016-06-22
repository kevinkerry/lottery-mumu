package com.lottery.ticket.vender.impl.gz2cp;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.Key;


public class Gz2cpDes {
	
	private static Logger logger = LoggerFactory.getLogger(Gz2cpDes.class);
	
	private static byte[] keyiv = "12345678".getBytes();
	
  
    /**
     * CBC加密
     * @param key 密钥
     * @param keyiv IV
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static String des3EncodeCBC(String key, String data) throws Exception {
        try {
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(keyiv);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] bOut = cipher.doFinal(data.getBytes("UTF-8"));
			String res=Base64.encodeBase64String(bOut);
			return res;
		} catch (Exception e) {
			logger.error("加密出错,key:" + key + ",data:" + data, e);
			throw new Exception("加密出错,key:" + key + ",data:" + data, e);
		}
    }
    /**
     * CBC解密
     * @param key 密钥
     * @param keyiv IV
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeCBC(String key, String data) throws Exception {
        try {
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(keyiv);
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] bOut = cipher.doFinal(Base64.decodeBase64(data));
			return new String(bOut, "UTF-8");
		} catch (Exception e) {
			logger.error("解密出错, key:" + key + ",data:" + data, e);
			throw new Exception("解密出错, key:" + key + ",data:" + data, e);
		}
    }
}
