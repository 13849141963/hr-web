package com.fescotech.apps.entryonline.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.common.util.ImgUtilOpenPlateform;
import com.fescotech.apps.entryonline.dto.*;
import com.fescotech.apps.entryonline.entity.CommonImgInfo;
import com.fescotech.apps.entryonline.entity.EntryInfo;
import com.fescotech.apps.entryonline.util.HttpUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.controller.FilesDownloadController;
import com.fescotech.common.config.AppConfig;
import com.fescotech.common.util.ApiUtilOpenPlateform;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.io.FileDescriptor.out;

/**
 * Created by cy on 2018/2/28.
 */
public class DownloadFileUtils {

    private static String PDF_DOWNLOAD_INNER_URL;

    static{
        PDF_DOWNLOAD_INNER_URL = AppConfig.me().getPropValue("PDF_DOWNLOAD_INNER_URL");
    }


	//身份证下载
    public static void downloadIDCardFiles(HttpServletRequest request, HttpServletResponse response, List<IDCardResp> idCardResps) {

        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");

        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = "身份证集.zip";
        String agent = request.getHeader("USER-AGENT");
        try {
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + downloadName);

        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos = null;
        try {
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法
        } catch (Exception e) {
            e.printStackTrace();
        }

        //循环将文件写入压缩流
        DataOutputStream os = null;
        for (int i = 0; i < idCardResps.size(); i++) {

            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                zipos.putNextEntry(new ZipEntry(i + idCardResps.get(i).getEmpName() + ".jpg"));
                os = new DataOutputStream(zipos);
                //  InputStream is = ImgUtilOpenPlateform.downloadFile(idCardResps.get(i).getIdCardComImgId());
                InputStream is = download(idCardResps.get(i).getIdCardComImgId());
                byte[] b = new byte[100];
                int length = 0;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                zipos.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //关闭流
        try {
            os.flush();
            os.close();
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //下载图片
    public static void batchloadImgFiles(HttpServletRequest request, HttpServletResponse response, List<CommonImgInfo> imgInfo, String fileName) {
        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");

        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = fileName;
        String agent = request.getHeader("USER-AGENT");
        try {
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos = null;
        try {
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法
        } catch (Exception e) {
            e.printStackTrace();
        }

        //循环将文件写入压缩流
        DataOutputStream os = null;
        for (int i = 0; i < imgInfo.size(); i++) {

            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                zipos.putNextEntry(new ZipEntry(imgInfo.get(i).getFileName() + ".jpg"));
                os = new DataOutputStream(zipos);
                //InputStream is = ImgUtilOpenPlateform.downloadFileInner(imgInfo.get(i).getFileId());
                InputStream is = download(imgInfo.get(i).getFileId());
                byte[] b = new byte[100];
                int length = 0;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                zipos.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //关闭流
        try {
            os.flush();
            os.close();
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从网上获取图片流做测试
    public static InputStream download(String urlString) throws Exception {
        // 构造URL
        String urlbase = "http://api.inside.com:18081/fileserver/getErRecordDataById.do?fileId=";
        URL url = new URL(urlbase+urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();
        return is;
    }


    public static void downloadIDCardPdf(HttpServletRequest request, HttpServletResponse response, List<PdfFileParamDtoReq> list) throws Exception {
        OutputStream out = null;
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        InputStream in= null;
        //响应头的设置
        response.setCharacterEncoding("utf-8");
        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = "身份证集.pdf";
        String agent = request.getHeader("USER-AGENT");
        try {
        	
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
            }
            
            response.setHeader("Content-Disposition", "attachment;fileName=" + downloadName);
            out = response.getOutputStream();
            //in = download("http://img.zcool.cn/community/01638059302785a8012193a36096b8.jpg@2o.jpg");
            in = getPdfInputStream(list);
            
            byte[] b = new byte[1024];
            int len;
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void downloadLoadHouseholdPdfFiles(HttpServletRequest request, HttpServletResponse response, List<HouseholdPdfParamDtoReq> list) {
        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");

        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = "户口本集.zip";
        String agent = request.getHeader("USER-AGENT");
        try {
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos = null;
        try {
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法
        } catch (Exception e) {
            e.printStackTrace();
        }

        //循环将文件写入压缩流
        DataOutputStream os = null;
        for (int i = 0; i < list.size(); i++) {
        List<PdfFileParamDtoReq> listPdf =  new ArrayList<PdfFileParamDtoReq>();
        listPdf.add(list.get(i).getFirstHuKouPicId());
        listPdf.add(list.get(i).getSelfHuKouPicId());
        listPdf.add(list.get(i).getHuKouChangePicIds());

            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                zipos.putNextEntry(new ZipEntry("" + list.get(i).getEmpName()+list.get(i).getIdCode() + ".pdf"));
                os = new DataOutputStream(zipos);
                // InputStream is = ImgUtilOpenPlateform.downloadFile(loadHouseholdResp.get(i).getSelfHuKouPicId());
                //InputStream is = ApiUtilOpenPlateform.callOpenPlateformApiGetInoutStream("publicmgr.mergePdfFileByFileId",JSON.toJSONString(listPdf));
                InputStream is = getPdfInputStream(listPdf);
                byte[] b = new byte[100];
                int length = 0;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                zipos.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //关闭流
        try {
            os.flush();
            os.close();
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }


    //传入fileId 返回输入流
    private static InputStream getPdfInputStream(List<PdfFileParamDtoReq> listPdf) throws Exception{
        //String result = ApiUtilOpenPlateform.callOpenPlateformApiHr("publicmgr.mergePdfFileByFileId",JSON.toJSONString(listPdf));
    	//内测
    	String url =PDF_DOWNLOAD_INNER_URL;
	    String result = HttpUtils.jsonPost(url, JSON.toJSONString(listPdf));
       /* System.err.println("--------");
	    System.err.println(JSON.toJSONString(listPdf));
        System.err.println("--------");*/
        Result parseObject = JSONObject.parseObject(result, Result.class);
        Object successResult = parseObject.getSuccessResult();
        JSONObject filestrRes = JSONObject.parseObject(successResult.toString());
        String filestr = (String)filestrRes.get("filestr");
        
        byte[] bytes = new BASE64Decoder().decodeBuffer(filestr);   //将字符串转换为byte数组
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        
        //InputStream is =new ByteArrayInputStream(filestr.getBytes("UTF-8"));
    	return in;
    }

}
