package com.fescotech.apps.entryonline.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json处理工具类
 * @author sunwill
 */
public class JsonUtils {

	/**
	 * 对象序列化
	 * @param object
	 * @return
	 */
	public static final String toJSONString(Object object){
		
		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * 对象JSONP
	 * @param object
	 * @return
	 */
	public static final String toJSONPString(Object object){
		return "jsonpCallback("+JSON.toJSONString(object,SerializerFeature.WriteMapNullValue)+")";
	}
	
	/**
	 * 反序列化
	 * @param jsonText
	 * @return
	 */
	public static final Object parseObject(String jsonText){
		return JSON.parse(jsonText);
	}
	
	/**
	 * 反序列化指定对象
	 * @param jsonText
	 * @param classz
	 * @return
	 */
	public static final <T> T parseObject(String jsonText,Class<T> classz){
		return JSON.parseObject(jsonText, classz,Feature.OrderedField);
	}
}
