/*
import com.alibaba.fastjson.JSON;
import com.fesco.pafa.exceptions.MessageException;
import com.fesco.pafa.util.HttpClientUtil;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.apps.entryonline.util.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HttpApiUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpApiUtil.class);
	*/
/**
	 * 同步调用并返回封装对象
	 * @param url  远程url
	 * @param request 请求Bean对象
	 * @return
	 *//*

	public static Result callSyncService(String url, Object request) {
        Map<String, String> param = createJsonDataRequest(request);
        return callMicroService(url, param);
    }

	*/
/**
	 * 同步调用并返回数据对象
	 * @param url 远程服务地址
	 * @param request 请求bean对象
	 * @return
	 *//*

	public static Object callHttpResult(String url, Object request) {
        Map<String, String> param = createJsonDataRequest(request);
        Result rsp= callMicroService(url, param);
        if (rsp == null) {
            logger.error("url=" + url + ",data=" + request);
            throw new MessageException("远程调用返回对象为空");
          }
        if (!"0".equals(rsp.getCode())) {
            logger.error("url=" + url + ",data=" + request);
            throw new MessageException(rsp.getErrorMsg());
        }
        return rsp.getSuccessResult();
    }

    private static Result callMicroService(String url, Map<String, String> param) {
        long start = System.currentTimeMillis();
        String rslt = HttpClientUtil.httpPostProxy(url, param, null, 0);
        long end = System.currentTimeMillis();
        logger.info("call-sycn-service-url=" + url + "\t cost-time=" + (end - start));
        logger.info("数据："+rslt);
        return JsonBuilder.parseObject(rslt, Result.class);
    }

    private static Map<String, String> createJsonDataRequest(Object request) {
    	String json = null;
    	if(request instanceof String)
    		json = (String) request;
    	else
    		json = JSON.toJSONString(request);
        Map<String, String> param = new HashMap<String, String>();
        param.put("jsonParam",json);
        return param;
    }

}
*/
