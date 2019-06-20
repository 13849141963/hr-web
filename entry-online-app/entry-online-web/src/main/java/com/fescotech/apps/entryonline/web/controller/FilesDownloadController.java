package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.dto.*;
import com.fescotech.apps.entryonline.entity.CommonImgInfo;
import com.fescotech.apps.entryonline.entity.EntryTask;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.util.PageUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.apps.entryonline.web.util.DownloadFileUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by cy on 2018/2/28.
 */
@Controller
public class FilesDownloadController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/downloadIdPics")
    @ResponseBody
    public String downloadIdPics(HttpServletRequest request, HttpServletResponse response , @RequestParam(value = "empTaskIds",required=false)String[] empTaskIds) throws Exception {
        IDCardReq idCardReq = new IDCardReq();
        //idCardReq.setBusiCustNos(new String[]{"22"});
        String[] removeDuplicateCustomerIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        idCardReq.setBusiCustNos(removeDuplicateCustomerIds);

        idCardReq.setEmpTaskIds(empTaskIds);

        String method = "olentry.batchGetIdPics";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, JSON.toJSONString(idCardReq));
        Result result = JSON.parseObject(resultStr,Result.class);
        Object s = result.getSuccessResult();
        String sStr = JSON.toJSONString(s);
        Map sMap =  JSON.parseObject(sStr, Map.class);
        Object pageRecords = sMap.get("pageRecords");
        List<IDCardResp> idCardResps = JSON.parseArray(JSON.toJSONString(pageRecords), IDCardResp.class);

        //批量下载身份证并打成zip包返回给客户端
        //DownloadFileUtils.downloadIDCardFiles(request,response,idCardResps);
        List<PdfFileParamDtoReq> listFileParam = new ArrayList<PdfFileParamDtoReq>();
        for (IDCardResp idCardResp: idCardResps) {
            PdfFileParamDtoReq pdfFileParamDtoReq = new PdfFileParamDtoReq(1,1,1,idCardResp.getIdCardComImgId());
            //todo;
            //PdfFileParamDtoReq pdfFileParamDtoReq = new PdfFileParamDtoReq(1,1,1,"66F30274CE56130FE053DF4B000A1B8C");
            listFileParam.add(pdfFileParamDtoReq);
        }

        if(listFileParam.size()>40){
            DownloadFileUtils.downloadIDCardPdfZip(request,response,listFileParam);
        }else {
            DownloadFileUtils.downloadIDCardPdf(request, response, listFileParam);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/downloadLoadHouseholds")
    @ResponseBody
    public String downloadLoadHouseholds(HttpServletRequest request, HttpServletResponse response , @RequestParam(value = "empTaskIds",required=false)String[] empTaskIds) throws Exception {
        LoadHouseholdReq loadHouseholdReq = new LoadHouseholdReq();
        //获取busiusIds
        //loadHouseholdReq.setBusiCustNos(new String[]{"22"});
        String[] removeDuplicateCustomerIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        loadHouseholdReq.setBusiCustNos(removeDuplicateCustomerIds);

        loadHouseholdReq.setEmpTaskIds(empTaskIds);

        String method = "olentry.batchLoadHousehold";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, JSON.toJSONString(loadHouseholdReq));
        Result result = JSON.parseObject(resultStr,Result.class);
        Object s = result.getSuccessResult();
        String sStr = JSON.toJSONString(s);
        Map sMap =  JSON.parseObject(sStr, Map.class);
        Object pageRecords = sMap.get("pageRecords");
        List<LoadHouseholdResp> loadHouseholdResps = JSON.parseArray(JSON.toJSONString(pageRecords), LoadHouseholdResp.class);

        //批量下载身份证并打成zip包返回给客户端
        List<HouseholdPdfParamDtoReq> list = new ArrayList<>();
        for (LoadHouseholdResp loadHouseholdResp:loadHouseholdResps) {
            HouseholdPdfParamDtoReq householdPdf = new HouseholdPdfParamDtoReq();
            householdPdf.setFirstHuKouPicId(new PdfFileParamDtoReq(2,1,1,loadHouseholdResp.getFirstHuKouPicId()));
            householdPdf.setSelfHuKouPicId(new PdfFileParamDtoReq(3,1,1,loadHouseholdResp.getSelfHuKouPicId()));
            householdPdf.setHuKouChangePicIds(new PdfFileParamDtoReq(4,1,1,loadHouseholdResp.getHuKouChangePicId()));
            //todo;
            //householdPdf.setFirstHuKouPicId(new PdfFileParamDtoReq(2,1,1,"66F30274CE56130FE053DF4B000A1B8C"));
            //householdPdf.setSelfHuKouPicId(new PdfFileParamDtoReq(3,1,1,"66F30274CE56130FE053DF4B000A1B8C"));
            //householdPdf.setHuKouChangePicIds(new PdfFileParamDtoReq(4,1,1,"66F30274CE56130FE053DF4B000A1B8C"));

            householdPdf.setEmpName(loadHouseholdResp.getEmpName());
            householdPdf.setIdCode(loadHouseholdResp.getIdCode());
            list.add(householdPdf);
        }
        DownloadFileUtils.downloadLoadHouseholdPdfFiles(request,response,list);

        return null;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/downloadLoadCunDangZhengMings")
    @ResponseBody
    public String downloadLoadCunDangZhengMings(HttpServletRequest request, HttpServletResponse response , @RequestParam(value = "empTaskIds",required=false)Long[] empTaskIds) throws Exception {
        String method = "olentry.batchLoadCunDangZhengMing";
        String fileName = "存档证明集.zip";
        commonImgDownLoad(request,response,empTaskIds,method,fileName);
        return null;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/downloadLoadZhuanDangXieYis")
    @ResponseBody
    public String downloadLoadZhuanDangXieYis(HttpServletRequest request, HttpServletResponse response , @RequestParam(value = "empTaskIds",required=false)Long[] empTaskIds) throws Exception {
        String method = "olentry.batchLoadZhuanDangXieYi";
        String fileName = "转档协议集.zip";
        commonImgDownLoad(request,response,empTaskIds,method,fileName);
        return null;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/downloadLoadNewInsPics")
    @ResponseBody
    public String downloadLoadNewInsPics(HttpServletRequest request, HttpServletResponse response , @RequestParam(value = "empTaskIds",required=false)Long[] empTaskIds) throws Exception {
        String method = "olentry.batchLoadNewInsPic";
        String fileName = "社保照片集.zip";
        commonImgDownLoad(request,response,empTaskIds,method,fileName);
        return null;
    }

    private String commonImgDownLoad(HttpServletRequest request, HttpServletResponse response ,Long[] empTaskIds,String method,String fileName) throws Exception {
        ImgBatchLoadCommonDtoReq imgBatchLoadCommonDtoReq = new ImgBatchLoadCommonDtoReq();
        String[] removeDuplicateCustomerIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        imgBatchLoadCommonDtoReq.setBusiCustNos(removeDuplicateCustomerIds);
        imgBatchLoadCommonDtoReq.setEmpTaskIds(empTaskIds);

        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, JSON.toJSONString(imgBatchLoadCommonDtoReq));
        Result result = JSON.parseObject(resultStr,Result.class);
        Object s = result.getSuccessResult();
        String sStr = JSON.toJSONString(s);
        Map sMap =  JSON.parseObject(sStr, Map.class);
        Object pageRecords = sMap.get("pageRecords");
        List<ImgBatchLoadCommonDtoResp> imgInfos = JSON.parseArray(JSON.toJSONString(pageRecords), ImgBatchLoadCommonDtoResp.class);
        List<CommonImgInfo> commonImgInfos = new ArrayList<>();

        for (ImgBatchLoadCommonDtoResp imgInfo:imgInfos) {
            CommonImgInfo commonImgInfo = new CommonImgInfo();
            commonImgInfo.setEmpName(imgInfo.getEmpName());
            commonImgInfo.setEmpTaskId(imgInfo.getEmpTaskId());
            commonImgInfo.setIdCode(imgInfo.getIdCode());
            commonImgInfo.setMobile(imgInfo.getMobile());
            if(imgInfo.getMaterials().size()>0) {
                commonImgInfo.setMeterialType(imgInfo.getMaterials().get(0).getMeterialType());
                commonImgInfo.setFileId(imgInfo.getMaterials().get(0).getFileId());
            }
            commonImgInfo.setFileName(imgInfo.getEmpName()+imgInfo.getIdCode());
            commonImgInfos.add(commonImgInfo);
        }

        DownloadFileUtils.batchloadImgFiles(request,response,commonImgInfos,fileName);

        return null;
    }

    }
