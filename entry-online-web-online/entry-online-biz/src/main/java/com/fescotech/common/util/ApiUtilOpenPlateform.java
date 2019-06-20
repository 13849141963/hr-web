package com.fescotech.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fesco.fws.ws.WsUtils;
import com.fesco.pafa.exceptions.MessageException;
import com.fesco.pafa.util.HttpClientUtil;
import com.fescotech.common.config.AppConfig;

/**
 * @author cao.guo.dong
 * @create 2017-09-18 17:45
 * @desc 开放平台调用工具类
 **/

public class ApiUtilOpenPlateform {
    private static Logger logger = Logger.getLogger(ApiUtilOpenPlateform.class);
    private static String APP_KEY;// = "WeiXinSystem";//
    private static String APP_SECRET;// = "c46beef207eab2bafa1cf2b03cdf48c7";//
    final static private String FORMAT = "json";
    //内测环境
    private static String OUTER_NETWORK_URL;//"https://apics.fesco.com.cn/fescoOpenTest";//
    private static String ALIYUN_OUTER_NETWORK_URL;
    //正式环境（勿用，勿动）
    static private String OFFICIAL_NETWORK_URL; //"https://openapi.fesco.com.cn/fescoOpen";
    
    public static String MIDDLE_INNER_NETWORK_URL;
    public static String OLD_INTERFACE_EMPLOYEE_INNER_NETWORK_URL;
    public static String HR_INNERR_NETWORK_URL;
    public static String OLD_INTERFACE_EMPLOYEE_PATH;
    public static String OLD_INTERFACE_EMPLOYEE_NAMESPACE;
    
    static{
        APP_KEY = AppConfig.me().getPropValue("APP_KEY");
        APP_SECRET = AppConfig.me().getPropValue("APP_SECRET");
        OUTER_NETWORK_URL = AppConfig.me().getPropValue("OUTER_NETWORK_URL");
        ALIYUN_OUTER_NETWORK_URL = AppConfig.me().getPropValue("ALIYUN_OUTER_NETWORK_URL");
        
        MIDDLE_INNER_NETWORK_URL= AppConfig.me().getPropValue("MIDDLE_INNER_NETWORK_URL");
        HR_INNERR_NETWORK_URL= AppConfig.me().getPropValue("HR_INNERR_NETWORK_URL");
        OLD_INTERFACE_EMPLOYEE_INNER_NETWORK_URL= AppConfig.me().getPropValue("OLD_INTERFACE_EMPLOYEE_INNER_NETWORK_URL");
        OLD_INTERFACE_EMPLOYEE_PATH = AppConfig.me().getPropValue("OLD_INTERFACE_EMPLOYEE_PATH");
        OLD_INTERFACE_EMPLOYEE_NAMESPACE=AppConfig.me().getPropValue("OLD_INTERFACE_EMPLOYEE_NAMESPACE");
    }


    //中台接口
    public static String callOpenPlateformApi(String method, String jsonList) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("methodName", method);
        params.put("format", FORMAT);
        params.put("jsonList", jsonList);
        params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        params.put("appkey", APP_KEY);
        String jsonParam = JsonUtils.toJSONString(params);//该map包含所有参数除了appSecret
        String mysign = SignUtil.mySign(APP_KEY, APP_SECRET, jsonParam, FORMAT);
        params.put("sign", mysign);
        logger.info("入参：" + JsonUtils.toJSONString(params));
        long startInvokeTime = System.currentTimeMillis();
        String result = HttpClientUtil.httpPost(ALIYUN_OUTER_NETWORK_URL, params, "UTF-8");
        long endInvokeTime = System.currentTimeMillis();
        logger.info("出参：" + result);
        String log4jMessage ="  耗费时间（毫秒） :: " + (endInvokeTime-startInvokeTime);
        if (StringUtils.isNullOrEmpty(result)) {
            String msg = String.format("方法名:%s,错误信息:%s", method, "返回信息为空");
            logger.info(msg);
            throw new MessageException(msg);
        }
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        if("".equals(code)){
            throw new MessageException("转换异常");
        }
        if (!"0".equals(code)) {
            String errorMsg = jsonObject.getString("errorMsg");
            String msg = String.format("方法名:%s,错误信息:%s", method, errorMsg);
            logger.error(msg);
            throw new MessageException(errorMsg);
        }
        return result;
    }

    //调用hr相关接口
    public static String callOpenPlateformApiHr(String method, String jsonList) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("methodName", method);
        params.put("format", FORMAT);
        params.put("jsonList", jsonList);
        params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        params.put("appkey", APP_KEY);
        String jsonParam = JsonUtils.toJSONString(params);//该map包含所有参数除了appSecret
        String mysign = SignUtil.mySign(APP_KEY, APP_SECRET, jsonParam, FORMAT);
        params.put("sign", mysign);
        logger.info("入参：" + JsonUtils.toJSONString(params));
        long startInvokeTime = System.currentTimeMillis();
        String result = HttpClientUtil.httpPost(OUTER_NETWORK_URL, params, "UTF-8");
        long endInvokeTime = System.currentTimeMillis();
        logger.info("出参：" + result);
        String log4jMessage ="  耗费时间（毫秒） :: " + (endInvokeTime-startInvokeTime);
        if (StringUtils.isNullOrEmpty(result)) {
            String msg = String.format("方法名:%s,错误信息:%s", method, "返回信息为空");
            logger.info(msg);
            throw new MessageException(msg);
        }
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        if("".equals(code)){
            throw new MessageException("转换异常");
        }
        if (!"0".equals(code)) {
            String errorMsg = jsonObject.getString("errorMsg");
            String msg = String.format("方法名:%s,错误信息:%s", method, errorMsg);
            logger.error(msg);
            throw new MessageException(errorMsg);
        }
        return result;
    }
    
    
    public static String callNewInterfaceByInnerNetWork(String netWorkUrl , String method, String jsonList) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("methodName", method);
        params.put("format", FORMAT);
        params.put("jsonList", jsonList);
        params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        params.put("appkey", APP_KEY);
        String jsonParam = JsonUtils.toJSONString(params);//该map包含所有参数除了appSecret
        String mysign = SignUtil.mySign(APP_KEY, APP_SECRET, jsonParam, FORMAT);
        params.put("sign", mysign);
        String methodName = method.replace(".", "/");
        logger.info("innerNewInterfaceUrl  ===> "+netWorkUrl+methodName + "   innerNewInterfaceUrl入参：" + JsonUtils.toJSONString(params));
        long startInvokeTime = System.currentTimeMillis();
        String result = HttpClientUtil.httpPost(netWorkUrl+methodName, params, "UTF-8");
        long endInvokeTime = System.currentTimeMillis();
        logger.info("innerNewInterfaceUrl出参：" + result);
        String log4jMessage ="  耗费时间（毫秒） :: " + (endInvokeTime-startInvokeTime);
        if (StringUtils.isNullOrEmpty(result)) {
            String msg = String.format("方法名:%s,错误信息:%s", method, "返回信息为空");
            logger.info(msg);
            throw new MessageException(msg);
        }
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        if("".equals(code)){
            throw new MessageException("转换异常");
        }
        if (!"0".equals(code)) {
            String errorMsg = jsonObject.getString("errorMsg");
            String msg = String.format("方法名:%s,错误信息:%s", method, errorMsg);
            logger.error(msg);
            throw new MessageException(errorMsg);
        }
        return result;
    }
    
    public static String callOldInterfaceByInnerNetWork(String netWorkUrl,String path, String nameSpace, String method, String jsonList) throws Exception{
    	Map<String, String> params = new HashMap<>();
        //该map包含所有参数除了appSecret
        String mysign = SignUtil.mySign(APP_KEY, APP_SECRET, jsonList, FORMAT);
        String[] value = {APP_KEY,mysign,jsonList,FORMAT};
        logger.info("innertoldInterfaceurl  ===> "+netWorkUrl+path+nameSpace +"  入参：" + jsonList);
        long startInvokeTime = System.currentTimeMillis();
        String wsdlUrl = netWorkUrl+path;
        Object[] objs = WsUtils.invoke2(wsdlUrl, nameSpace, method, value, new Class[] { String.class });
        		//HttpClientUtil.httpPost(INNER_NETWORK_URL+method, params, "UTF-8");
        String result = (objs==null || objs[0] == null )? "" : objs[0].toString(); 
        long endInvokeTime = System.currentTimeMillis();
        logger.info("innertoldInterfaceurl出参：" + result);
        String log4jMessage ="  耗费时间（毫秒） :: " + (endInvokeTime-startInvokeTime);
        
        if (StringUtils.isNullOrEmpty(result)) {
            String msg = String.format("方法名:%s,错误信息:%s", method, "返回信息为空");
            logger.info(msg);
            throw new MessageException(msg);
        }
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        if("".equals(code)){
            throw new MessageException("转换异常");
        }
        if (!"0".equals(code)) {
            String errorMsg = jsonObject.getString("errorMsg");
            String msg = String.format("方法名:%s,错误信息:%s", method, errorMsg);
            logger.error(msg);
            throw new MessageException(errorMsg);
        }
        return result;
    }

    public static InputStream callOpenPlateformApiGetInoutStream(String method, String jsonList) throws Exception {
        try {
        Map<String, String> params = new HashMap<>();
        params.put("methodName", method);
        params.put("format", FORMAT);
        params.put("jsonList", jsonList);
        params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        params.put("appkey", APP_KEY);
        String jsonParam = JsonUtils.toJSONString(params);//该map包含所有参数除了appSecret
        String mysign = SignUtil.mySign(APP_KEY, APP_SECRET, jsonParam, FORMAT);
        params.put("sign", mysign);
        logger.info("入参：" + JsonUtils.toJSONString(params));
        long startInvokeTime = System.currentTimeMillis();

        PostMethod filePost = new PostMethod(OUTER_NETWORK_URL);
        HttpClient client = new HttpClient();
        filePost.setRequestBody(new NameValuePair[]{
                new NameValuePair("appkey", params.get("appkey")),
                new NameValuePair("format", params.get("format")),
                new NameValuePair("methodName", params.get("methodName")),
                new NameValuePair("timeStamp", params.get("timeStamp")),
                new NameValuePair("jsonList", params.get("jsonList")),
                new NameValuePair("sign", mysign)});
        int status = client.executeMethod(filePost);
        if (logger.isInfoEnabled()) {
            logger.info("download response size -- " + filePost.getResponseContentLength());
        }
        if (status == 200) {
            return filePost.getResponseBodyAsStream();
        } else {
            logger.error("pdf下载失败,");
        }
    } catch (Exception e) {
        logger.error("pdf下载失败,详细信息：" + e.getMessage());
    }
        return null;
    }

    
    public static void main(String[] args) throws Exception {
      
    }
}
