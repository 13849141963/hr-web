package com.fescotech.apps.entryonline.common.util;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


 

/**
 * 签名工具类
 * @author 邱健
 *
 */
public class SignUtil {
	private static Logger logger = Logger.getLogger(SignUtil.class);
	
	@SuppressWarnings("unchecked")
	public static String mySign(String appkey, String appSecret,
			String jsonParam, String format) throws Exception{
		String mysign="";
		try {
			jsonParam = URLDecoder.decode(jsonParam, "UTF-8");
			Map<String, String> paranMap = JsonUtils.parseObject(jsonParam, Map.class);
			mysign = SignUtil.getSign(appkey, appSecret, paranMap, format);
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}
		return mysign;
	}
	/**
	 * 
	 * @param appkey
	 * @param appSecret
	 * @param paranMap:该map包含所有参数除了appSecret
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String getSign(String appkey ,String appSecret,Map<String,String> paranMap ,String format) throws Exception{
		String sign = null ;
		try {
			
			//添加签名参数
			if(paranMap == null )
				paranMap = new HashMap<String,String>();
			paranMap.put(BaseSecuritySignConstants.APPKEY, appkey);
			if(!"".equals(format) && format != null && !"null".equals(format))
				paranMap.put(BaseSecuritySignConstants.FORMAT, format);
			paranMap.remove("thisIsServiceJobSign");
			//对map签名
			sign = SignUtil.signingObjectByMd5(paranMap , appSecret);
			
		} catch (Exception e) {
			logger.info(" getSign signingObjectByMd5 : 对象签名出现异常,签名失败 :"+e.getMessage());
			throw new Exception(e.getMessage(),e);
		}  
		logger.info(" getSign : 对象签名成功");
		return sign;
	}

	/**
	 * @desc 传递map和args值,根据md5对对象进行签名
	 * @param paramMap
	 * @param args
	 * @return
	 * @throws Exception
	 * @author liangliuqi
	 */
	public static String signingObjectByMd5(Map<String,String> paramMap , String args ) throws Exception{
		// 对MAP参数名进行字典排序 
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		
		// 拼接有序的参数名-值串  
		StringBuffer stringBuffer = new StringBuffer();  
		stringBuffer.append(args);  
		for (String key : keyArray)  
		{  
			if(paramMap.get(key) !=null && !"".equals(paramMap.get(key))){
				stringBuffer.append(key).append(String.valueOf(paramMap.get(key)));
			}
		}  
		String codes = stringBuffer.toString();  
		logger.info("md5之前的串:"+codes);
		//使用MD5签名
		String sign ="";
		try {
			sign = SignUtil.encrypt(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return sign;
	}
	
	/**
	 *  将传入字符串做md5，返回md5后的串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes("utf-8"));
		return byte2hex(md5.digest());

	}
	/**
	 *  二行制转字符串
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + "";
		}
		return hs;
	}
	
	
}
