package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.dto.*;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cy on 2018/3/22.
 */
@Controller
public class FilesDownloadValidateController {


    @RequestMapping(value = "/entryOnline/validateDownloadIdPics")
    @ResponseBody
    public R valiDateDownloadIdPics(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) String[] empTaskIds) throws Exception {

        LoadHouseholdReq loadHouseholdReq = new LoadHouseholdReq();
        //获取busiusIds
        //loadHouseholdReq.setBusiCustNos(new String[]{"22"});
        String[] removeDuplicateCustomerIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        loadHouseholdReq.setBusiCustNos(removeDuplicateCustomerIds);

        loadHouseholdReq.setEmpTaskIds(empTaskIds);

        String method = "olentry.batchGetIdPics";
        //String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, JSON.toJSONString(loadHouseholdReq));
        return getRForValidateFilesDate(method,loadHouseholdReq);
    }

    @RequestMapping(value = "/entryOnline/validateDownloadLoadHouseholds")
    @ResponseBody
    public R valiDateDownloadLoadHouseholds(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) String[] empTaskIds) throws Exception {

        IDCardReq idCardReq = new IDCardReq();
        String[] removeDuplicateCustomerIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        idCardReq.setBusiCustNos(removeDuplicateCustomerIds);

        idCardReq.setEmpTaskIds(empTaskIds);

        String method = "olentry.batchLoadHousehold";

        return getRForValidateFilesDate(method,idCardReq);
    }

    @RequestMapping(value = "/entryOnline/validateExportEntryList")
    @ResponseBody
    public R validateExportEntryList(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {

        String method = "olentry.queryExpEntryList";

        Map<String, Object> queryParam = new HashMap<>();
        //queryParam.put("busiCustNos", new String[] { "cp001", "cp002","cp003" });
        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        queryParam.put("busiCustNos", busiCustIds);
        queryParam.put("empTaskIds", empTaskIds);

        return getRForValidateFilesDate(method,queryParam);
    }

    @RequestMapping(value = "/entryOnline/validateExportEntryListHr")
    @ResponseBody
    public R validateExportEntryListHr(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {

        EntrySituationHRDtoReq entrySituationDto = new EntrySituationHRDtoReq();
        String method = "olentry.queryEntryProgressList";

        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        entrySituationDto.setBusiCustNos(busiCustIds);
        entrySituationDto.setEmpTaskIds(empTaskIds);


        return getRForValidateFilesDate(method,entrySituationDto);
    }

    @RequestMapping(value = "/entryOnline/validateExportEmpInfos")
    @ResponseBody
    public R validateExportEmpInfos(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {

        EmpInfoDtoReq empInfo = new EmpInfoDtoReq();
        String method = "olentry.batchLoadEmpInfo";

        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        empInfo.setBusiCustNos(busiCustIds);
        empInfo.setEmpTaskIds(empTaskIds);


        return getRForValidateFilesDate(method,empInfo);
    }

    @RequestMapping(value = "/entryOnline/validateDownloadLoadCunDangZhengMings")
    @ResponseBody
    public R validateDownloadLoadCunDangZhengMings(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {

        ImgBatchLoadCommonDtoReq imgReq = new ImgBatchLoadCommonDtoReq();
        String method = "olentry.batchLoadCunDangZhengMing";

        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        imgReq.setBusiCustNos(busiCustIds);
        imgReq.setEmpTaskIds(empTaskIds);


        return getRForValidateFilesDate(method,imgReq);
    }

    @RequestMapping(value = "/entryOnline/validateDownloadLoadZhuanDangXieYis")
    @ResponseBody
    public R validateDownloadLoadZhuanDangXieYis(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {

        ImgBatchLoadCommonDtoReq imgReq = new ImgBatchLoadCommonDtoReq();
        String method = "olentry.batchLoadZhuanDangXieYi";

        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        imgReq.setBusiCustNos(busiCustIds);
        imgReq.setEmpTaskIds(empTaskIds);


        return getRForValidateFilesDate(method,imgReq);
    }

    @RequestMapping(value = "/entryOnline/validateDownloadLoadNewInsPics")
    @ResponseBody
    public R validateDownloadLoadNewInsPics(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {

        ImgBatchLoadCommonDtoReq imgReq = new ImgBatchLoadCommonDtoReq();
        String method = "olentry.batchLoadNewInsPic";

        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        imgReq.setBusiCustNos(busiCustIds);
        imgReq.setEmpTaskIds(empTaskIds);


        return getRForValidateFilesDate(method,imgReq);
    }

    private R getRForValidateFilesDate(String method ,Object obj){
        String resultStr = null;
        try {
            resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, JSON.toJSONString(obj));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("errorMsg",e.getMessage());
        }
        Result result = JSON.parseObject(resultStr,Result.class);
        Object s = result.getSuccessResult();
        String sStr = JSON.toJSONString(s);
        Map sMap =  JSON.parseObject(sStr, Map.class);
        Object totalCount = sMap.get("totalCount");
        Integer count = Integer.parseInt(totalCount.toString());
        if (count.equals(0)){
            return R.error().put("errorMsg","无可下载数据");
        }
        return R.ok();
    }

}
