package com.fescotech.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.util.ResException;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.config.AppConfig;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cao.guo.dong
 * @create 2017-10-18 16:20
 * @desc 开放平台文件服务
 **/

public class ImgUtilOpenPlateform {
    final static private Logger logger = Logger
            .getLogger(ImgUtilOpenPlateform.class);
    private static String APP_KEY;// = "WeiXinSystem";
    private static String APP_SECRET;// = "c46beef207eab2bafa1cf2b03cdf48c7";
    private static String FORMAT = "json";
    private static String UPLOAD_FILE_URL;// = "https://apics.fesco.com.cn/uploadFileTest";
    private static String DOWNLOAD_FILE_URL;// = "https://apics.fesco.com.cn/downloadFileTest";
    private static String UPLOAD_FILE_URL_INNER;//http://10.0.75.151:18081/fileserver/uploadByUniqNo.do
    private static String DOWNLOAD_FILE_URL_INNER;//http://10.0.75.151:18081/fileserver/getErRecordDataById.do

    static {
        APP_KEY = AppConfig.me().getPropValue("APP_KEY");
        APP_SECRET = AppConfig.me().getPropValue("APP_SECRET");
        UPLOAD_FILE_URL = AppConfig.me().getPropValue("UPLOAD_FILE_URL");
        DOWNLOAD_FILE_URL = AppConfig.me().getPropValue("DOWNLOAD_FILE_URL");
        UPLOAD_FILE_URL_INNER = AppConfig.me().getPropValue("UPLOAD_FILE_URL_INNER");
        DOWNLOAD_FILE_URL_INNER = AppConfig.me().getPropValue("DOWNLOAD_FILE_URL_INNER");
    }

    @SuppressWarnings("rawtypes")
    public static String uploadFile(Map<String, String> params, String fileType,
                                    File file) throws Exception {
        params.put("appkey", APP_KEY);
        params.put("format", FORMAT);
        params.put("filetype", fileType);
        /*
         * params.put("type", "227"); params.put("elecMatSource", "8");
		 * params.put("methodName", method); params.put("fileNo", "1");
		 * params.put("uniqNo", "995933"); params.put("verify", "2");
		 */
        String mysign = SignUtil.getSign(APP_KEY, APP_SECRET, params, FORMAT);
        params.put("sign", mysign);
        Part[] parts = {new StringPart("appkey", params.get("appkey")),
                new StringPart("elecMatSource", params.get("elecMatSource")),
                new StringPart("fileNo", params.get("fileNo")),
                new StringPart("filetype", params.get("filetype")),
                new StringPart("format", params.get("format")),
                new StringPart("methodName", params.get("methodName")),
                new StringPart("timeStamp", params.get("timeStamp")),
                new StringPart("type", params.get("type")),
                new StringPart("uniqNo", params.get("uniqNo")),
                new StringPart("verify", params.get("verify")),
                new StringPart("sign", params.get("sign")),
                new FilePart("file", file)};
        PostMethod filePost = new PostMethod(UPLOAD_FILE_URL);
        filePost.addRequestHeader("application/x-www-form-urlencoded",
                "multipart/form-data");
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost
                .getParams()));
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(5000);
        int status = client.executeMethod(filePost);
        String rsp = filePost.getResponseBodyAsString();
        logger.info(rsp);
        if (status != HttpStatus.SC_OK) {
            logger.error("上传失败");
            throw new ResException("上传失败");
        }
        filePost.releaseConnection();
        Result rslt = JSON.parseObject(rsp, Result.class);

        if (!rslt.getCode().equals("0")) {
            logger.error("调用接口错误" + rslt);
            throw new ResException(rslt.getErrorMsg());
        }

        JSONObject jsonRslt = JSONObject.parseObject(JSON.toJSONString(rslt.getSuccessResult()));
        Object fileIdObj = jsonRslt.get("fileId");
        if (fileIdObj == null) {
            logger.error("应用级错误   :: " + jsonRslt);
            throw new ResException("上传失败");
        }
        String fileId = jsonRslt.getString("fileId");

        return fileId;
    }

    /**
     * @param fileId :文件服务器返回的唯一标识
     * @return 返回输入流
     * @author cao.guo.dong
     * @create 2017-10-19 17:01
     * @desc 图片下载
     **/
    public static InputStream downloadFile(String fileId) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("appkey", APP_KEY);
            params.put("format", FORMAT);
            params.put("methodName", "fileservice.getErRecordMatById");
            params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
            params.put("fileId", fileId);
            String mysign = SignUtil.getSign(APP_KEY, APP_SECRET, params,
                    FORMAT);
            params.put("sign", mysign);
            PostMethod filePost = new PostMethod(DOWNLOAD_FILE_URL);
            HttpClient client = new HttpClient();
            filePost.setRequestBody(new NameValuePair[]{
                    new NameValuePair("appkey", params.get("appkey")),
                    new NameValuePair("format", params.get("format")),
                    new NameValuePair("methodName", params.get("methodName")),
                    new NameValuePair("timeStamp", params.get("timeStamp")),
                    new NameValuePair("fileId", params.get("fileId")),
                    new NameValuePair("sign", mysign)});
            int status = client.executeMethod(filePost);
            if (logger.isInfoEnabled()) {
                logger.info("download response size -- " + filePost.getResponseContentLength());
            }
            if (status == 200) {
                return filePost.getResponseBodyAsStream();
            } else {
                logger.error("图片下载失败,fileId:" + fileId);
            }
        } catch (Exception e) {
            logger.error("图片下载失败，fileId:" + fileId + ",详细信息：" + e.getMessage());
        }
        return null;
    }


    public static String uploadFileInner(Map<String, String> params,
                                    File file) throws Exception {
        Part[] parts = {
                new StringPart("elecMatSource", params.get("elecMatSource")),
                new StringPart("fileNo", params.get("fileNo")),
                new StringPart("filetype", params.get("filetype")),
                new StringPart("type", params.get("type")),
                new StringPart("uniqNo", params.get("uniqNo")),
                new StringPart("verify", params.get("verify")),
                new FilePart("file", file)};
        PostMethod filePost = new PostMethod(UPLOAD_FILE_URL_INNER);
        filePost.addRequestHeader("application/x-www-form-urlencoded","multipart/form-data");
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        int status = client.executeMethod(filePost);
        String rsp = filePost.getResponseBodyAsString();
        logger.info(rsp);
        if (status != HttpStatus.SC_OK) {
            logger.error("上传失败");
            throw new ResException("上传失败");
        }
        filePost.releaseConnection();
        Result rslt = JSON.parseObject(rsp, Result.class);

        if (!rslt.getCode().equals("0")) {
            logger.error("调用接口错误" + rslt);
            throw new ResException(rslt.getErrorMsg());
        }
        JSONObject jsonRslt = JSONObject.parseObject(JSON.toJSONString(rslt.getSuccessResult()));
        Object fileIdObj = jsonRslt.get("fileId");
        if (fileIdObj == null) {
            logger.error("应用级错误   :: " + jsonRslt);
            throw new ResException("上传失败");
        }
        String fileId = jsonRslt.getString("fileId");
        return fileId;
    }

    public static InputStream downloadFileInner(String fileId) {
        try {
            PostMethod filePost = new PostMethod(DOWNLOAD_FILE_URL_INNER);
            HttpClient client = new HttpClient();
            filePost.setRequestBody(new NameValuePair[]{new NameValuePair("fileId", fileId)});
            int status = client.executeMethod(filePost);
            logger.info("download response size -- " + filePost.getResponseContentLength());
            if (status == 200) {
                return filePost.getResponseBodyAsStream();
            } else {
                logger.error("图片下载失败,fileId:" + fileId);
            }
        } catch (Exception e) {
            logger.error("图片下载失败，fileId:" + fileId + ",详细信息：" + e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) throws Exception{
        String fileId = "640B29B11FD32B29E053DF4B000ADC62";
        InputStream inputStream = downloadFileInner(fileId);
        OutputStream outputStream = new FileOutputStream("C:\\Users\\cao.guo.dong.CAOGUODONG\\Desktop\\2.jpeg");
        StreamUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();

        /*Map<String, String> params = new HashMap<String, String>();
        params.put("type", "227");
        params.put("elecMatSource", "8");
        params.put("fileNo", "");
        params.put("uniqNo", "61213144L");
        params.put("verify", "2");
        params.put("filetype", "jpg");
        File file = new File("C:\\Users\\cao.guo.dong.CAOGUODONG\\Desktop\\java补医保接口流程.jpeg");
        String uploadFileId = uploadFileInner(params, file);
        System.out.println(uploadFileId);*/
    }
}
