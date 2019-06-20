package com.fescotech.common.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.fesco.pafa.util.HttpClientUtil;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.apps.entryonline.util.ResException;
import com.fescotech.apps.entryonline.util.Result;

public class ApiUtil {
	private static final Logger logger = LoggerFactory.getLogger(ApiUtil.class);

	/**
	 * 同步调用并返回封装对象
	 * 
	 * @param url
	 *            远程url
	 * @param request
	 *            请求Bean对象
	 * @return
	 */
	public static Result callHttpApi(String url, Object request) {
		Map<String, String> param = createJsonDataRequest(request);
		return callMicroService(url, param);
	}

	/**
	 * 同步调用并返回数据对象
	 * 
	 * @param url
	 *            远程服务地址
	 * @param request
	 *            请求bean对象
	 * @return
	 */
	public static Object callHttpResult(String url, Object request) {
		Map<String, String> param = createJsonDataRequest(request);
		Result rsp = callMicroService(url, param);
		if (rsp == null) {
			logger.error("url=" + url + ",request=" + request);
			throw new ResException("远程调用返回对象为空");
		}
		return rsp.getSuccessResult();
	}

	private static Result callMicroService(String url, Map<String, String> param) {
		logger.info("url=" + url + "\t param=" + JSON.toJSONString(param));
		long start = System.currentTimeMillis();
		String rslt = HttpClientUtil.httpPost(url, param);
		
		long end = System.currentTimeMillis();
		logger.info("call-sycn-service-url=" + url + "\t cost-time="
				+ (end - start) + "\t rslt = " + rslt);

		Result resp = new Result();
		resp = JSONObject.parseObject(rslt, Result.class);
		
		logger.info("resp  [" + JSON.toJSONString(resp)+ "]");

		if ("".equals(resp.getCode())) {
			logger.error("url=" + url + ",rslt=" + rslt);
			throw new ResException("转换异常");
		}

		if (!"0".equals(resp.getCode())) {
			logger.error("url=" + url + ",data=" + resp);
			throw new ResException(resp.getErrorMsg());
		}

		return resp;
	}

	private static ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object obj, String s, Object v) {
			if (v == null)
				return "";
			return v;
		}
	};

	private static Map<String, String> createJsonDataRequest(Object request) {
		String json = null;
		if (request instanceof String)
			json = (String) request;
		else
			json = JSON.toJSONString(request);
		Map<String, String> param = new HashMap<String, String>();
		param.put("jsondata", json);
		return param;
	}
}
