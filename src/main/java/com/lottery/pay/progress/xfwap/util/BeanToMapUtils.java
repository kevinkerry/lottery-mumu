package com.lottery.pay.progress.xfwap.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;



public class BeanToMapUtils {

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Object convertMap(Class type, Map<String, Object>  map) throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Map convertBean(Object bean) throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class type = bean.getClass();
		Map<String, Object>  returnMap = new HashMap<String, Object> ();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} 
			}
		}
		return returnMap;
	}
	
	
	// 获取加密参数
	public static String getData(Map<String, Object>  dataMap, String sign,String key) {
		// 获取加密数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(dataMap);
		map.put("sign", sign);
		String data = mapToKVString(map);
		data = AES.aesEncrypt(data,key);
		return data;
	}
	/*
	 * map 转换为key=value&key=value形式
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToKVString(Map<String, Object> map) {
		StringBuffer result = new StringBuffer();
		if (map.isEmpty()) {
			return result.toString();
		}
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		int index = 0;
		while (it.hasNext()) {
			if (index++ != 0) {
				result.append("&");
			}
			String key = it.next();
			Object value = map.get(key);
			result.append(key).append("=").append(value);
		}
		return result.toString();
	}

	public static Map<String, String> kvStringToMap(String str) {
		if (str == null || str.trim().equals("")) {
			return null;
		}
		String[] kvs = str.split("&");
		if (kvs == null || kvs.length == 0) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < kvs.length; i++) {
			String[] kv = kvs[i].split("=");
			if (kv != null && kv.length == 2) {
				map.put(kv[0], kv[1]);
			}
		}
		return map;
	}
	
	
	
	

}