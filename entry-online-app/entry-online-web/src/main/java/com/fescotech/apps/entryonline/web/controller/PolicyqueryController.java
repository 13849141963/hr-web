package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fescotech.apps.entryonline.dto.HouseholdPdfParamDtoReq;
import com.fescotech.apps.entryonline.dto.PdfFileParamDtoReq;
import com.fescotech.apps.entryonline.entity.NationwidFileItem;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.entity.vo.EmployeequeryReq;
import com.fescotech.apps.entryonline.entity.vo.PolicyqueryReq;
import com.fescotech.apps.entryonline.util.HRPlateformApiUtil;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by cy on 2018/6/8.
 */
@Controller
@RequestMapping("/policyquery/")
public class PolicyqueryController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getNatiPollcyMain")
    @ResponseBody
    //全国政策查询,参考 2.1 全国政策信息
    public Result getNatiPollcyMain(PolicyqueryReq policyqueryReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(policyqueryReq.getAreaName()!=null && policyqueryReq.getAreaName() !="") {
            map.put("areaName", policyqueryReq.getAreaName());
        }
        if(policyqueryReq.getCateId()!=null && policyqueryReq.getCateId()!="") {
            map.put("cateId", policyqueryReq.getCateId());
        }
        if(policyqueryReq.getDispTime()!=null && policyqueryReq.getDispTime()!="") {
            map.put("dispTime", policyqueryReq.getDispTime().replace(":", ""));
        }
        if(policyqueryReq.getStanEffectTime()!=null && policyqueryReq.getStanEffectTime()!="") {
            map.put("stanEffectTime", policyqueryReq.getStanEffectTime().replace(":", ""));
        }
        if(policyqueryReq.getTitle()!=null && policyqueryReq.getTitle()!="") {
            map.put("title", policyqueryReq.getTitle());
        }
        map.put("pageIndex", policyqueryReq.getPageIndex());
        map.put("pageSize", policyqueryReq.getPageSize());

        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getNatiPollcyMain";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getNationwidFileItem")
    @ResponseBody
    //根据政策id获取下载列表,参考 2.1.2 根据政策id获取下载列表
    public void getNationwidFileItem(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getNationwidFileItem";
        //String resultStr = HRPlateformApiUtil.callNewInterfaceByInnerNetWork(method, jsonStr);
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);
        Result result = JSON.parseObject(resultStr, Result.class);
        Object successResult = result.getSuccessResult();
        String fileStrs = JSON.toJSONString(successResult);
       /* Result<List<CustomerVo>> res = JSON.parseObject(jsonPost,
        new TypeReference<Result<List<CustomerVo>>>() {
        });*/
        List<NationwidFileItem> nationwidFileItems = JSON.parseArray(fileStrs, NationwidFileItem.class);
        downloadLoadFiles(request,response,nationwidFileItems);
    }

    private void downloadLoadFiles(HttpServletRequest request, HttpServletResponse response, List<NationwidFileItem> list) {
        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");

        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = "政策集.zip";
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
            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                zipos.putNextEntry(new ZipEntry("" + list.get(i).getFileName()));
                os = new DataOutputStream(zipos);
                //将字符串转换为byte数组
                byte[] bytes = new BASE64Decoder().decodeBuffer(list.get(i).getFileStr());
                InputStream is = new ByteArrayInputStream(bytes);
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
}
