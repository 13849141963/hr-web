package com.fescotech.apps.entryonline.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fesco.pafa.util.StringUtil;
import com.fescotech.common.config.AppConfig;

public class HRPlateformApiUtil{
	private static Logger logger = Logger.getLogger(HRPlateformApiUtil.class);
   
    final static private String FORMAT = "json";
 

    public static String HR_INNER_NETWORK_URL;
    
    static{
        HR_INNER_NETWORK_URL= AppConfig.me().getPropValue("HR_INNER_NETWORK_URL");
    }

    public static String callNewInterfaceByInnerNetWork( String method, String jsonList) throws Exception {
		long startInvokeTime = System.currentTimeMillis();
		String methodName = method.replace(".", "/");
		String url = HRPlateformApiUtil.HR_INNER_NETWORK_URL + methodName;
        PostMethod filePost = new PostMethod(url);
        filePost.setRequestEntity(new StringRequestEntity(jsonList,"application/json","utf-8"));
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        int status = client.executeMethod(filePost);
        String result = filePost.getResponseBodyAsString();
		long endInvokeTime = System.currentTimeMillis();
        String log4jMessage ="  耗费时间（毫秒） :: " + (endInvokeTime-startInvokeTime);
        logger.info("HRInterface --> " + method + "  params --> " +  jsonList + "  rslt --> "+ result  + log4jMessage);
        if(StringUtil.isEmpty(result)){
        	logger.error("调用hr系统为空");
        	throw new ResException("HR系统接口调用失败");
        }
        
        Result rslt= JSON.parseObject(result, Result.class);
		if(StringUtil.isEmpty(rslt.getCode()) || !"0".equals(rslt.getCode())){
        	logger.error(rslt.getErrorMsg());
			throw new ResException("HR系统接口调用失败");
		}
        return rslt.getSuccessResult().toString();
    }
}
