package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fescotech.apps.entryonline.dto.EntrySituationDtoReq;
import com.fescotech.apps.entryonline.dto.EntrySituationDtoResp;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoReq;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoResp;
import com.fescotech.apps.entryonline.entity.EntryInfo;
import com.fescotech.apps.entryonline.entity.EntryInfoHr;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.entity.vo.EntrySituationHRVoReq;
import com.fescotech.apps.entryonline.util.HttpUtils;
import com.fescotech.apps.entryonline.util.PageUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cy on 2018/3/3.
 */
@Controller
public class QueryEntrySituationHRController {

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryEntrySituationHR")
    @ResponseBody
    public Map getList(EntrySituationHRVoReq params) throws Exception {
        EntrySituationHRDtoReq entrySituationDto = new EntrySituationHRDtoReq();
        //少员工状态
        //entrySituationDto.setBusiCustNos(params.getBusiCustNos());
        //entrySituationDto.setBusiCustNos(new String[]{"cp001","cp002","cp003"});
        //todo;实际获取busiCustNos方法
        String[] busiCustNos = params.getBusiCustNos();
        if(busiCustNos != null  &&  busiCustNos.length != 0){
        	entrySituationDto.setBusiCustNos(busiCustNos);
        }else{
            String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
    		entrySituationDto.setBusiCustNos(busiCustIds);
        }
        //入职办理状态
        entrySituationDto.setTaskStatus(params.getTaskStatus());
        //员工类型(全日制/非全日制)
        entrySituationDto.setJobType(params.getJobType());
        //员工职务
        entrySituationDto.setPost(params.getEmpType());
        
        entrySituationDto.setPageNo(params.getPage());
        entrySituationDto.setPageSize(params.getRows());

        if (params.getUserInfoType() != null && params.getUserInfoType().equals(1)) {
            entrySituationDto.setEmpName(params.getUserInfoContent());
        } else if (params.getUserInfoType() != null && params.getUserInfoType().equals(2)) {
            entrySituationDto.setMobile(params.getUserInfoContent());
        } else if (params.getUserInfoType() != null && params.getUserInfoType().equals(3)) {
            entrySituationDto.setIdCode(params.getUserInfoContent());
        }
        String s = JSON.toJSONString(entrySituationDto);
        String method = "olentry.queryEntryProgressList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
        Result result = JSON.parseObject(resultStr, Result.class);
        Object successResult = result.getSuccessResult();
        String s1 = JSON.toJSONString(successResult);
        Map map = JSON.parseObject(s1, Map.class);
        Object pageRecords = map.get("pageRecords");
        Integer totalCount = Integer.parseInt(JSON.toJSONString(map.get("totalCount")));
        String s2 = JSON.toJSONString(pageRecords);
        List<EntrySituationHRDtoResp> entrySituationHRDtoResps = JSON.parseArray(s2, EntrySituationHRDtoResp.class);

        List<EntryInfoHr> list = new ArrayList<>();
        for (EntrySituationHRDtoResp entrySituationHR:entrySituationHRDtoResps) {
            EntryInfoHr entryInfo = new EntryInfoHr();
            entryInfo.setTaskId(entrySituationHR.getEmpTaskId());
            //品牌
            entryInfo.setBrand(entrySituationHR.getBusiCustNo());
            //姓名
            entryInfo.setEmpName(entrySituationHR.getEmpName());
            //店号
            entryInfo.setDianming(entrySituationHR.getEmpDep());
            //职务
            entryInfo.setZhiwu(entrySituationHR.getPost());
            //用工类型
            entryInfo.setYonggongType(entrySituationHR.getJobType());
            //身份证号
            entryInfo.setIdCode(entrySituationHR.getIdCode());
            //手机号
            entryInfo.setMobile(entrySituationHR.getMobile());
            //社保手续
            entryInfo.setShebaoProcedures(entrySituationHR.getInsStatus());
            //入职登记
            entryInfo.setEntryRegister(entrySituationHR.getEnrollStatus());
            //人事档案
            entryInfo.setPersonalRecords(entrySituationHR.getPersonnelStatus());
            //店名
            entryInfo.setEmpDepName(entrySituationHR.getEmpDepName());
            //入职确认状态
            entryInfo.setConfirmStatusName(entrySituationHR.getConfirmStatusName());
            list.add(entryInfo);
        }
        PageUtils pageUtil = new PageUtils(list, totalCount, params.getRows(), params.getPage());
        //PageUtils pageUtil = new PageUtils(list, totalCount, 1, 2);
        return R.ok().put("page",pageUtil);
    }
    



    
}
