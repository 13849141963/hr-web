package com.fescotech.apps.entryonline.web.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.fescotech.apps.entryonline.dto.EntrySituationDtoReq;
import com.fescotech.apps.entryonline.dto.EntrySituationDtoResp;
import com.fescotech.apps.entryonline.entity.EntryInfo;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.EntrySituationVoReq;
import com.fescotech.apps.entryonline.entity.ModelData.SocialSecurityDataVoResp;
import com.fescotech.apps.entryonline.util.PageUtils;
import com.fescotech.apps.entryonline.util.Result;

import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fescotech.common.util.ApiUtilOpenPlateform;

/**
 * Created by cy on 2018/3/1.
 */
@Controller
public class QueryEntrySituationController {

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryEntrySituation")
    @ResponseBody
    public Map getList(EntrySituationVoReq params) throws Exception {
        EntrySituationDtoReq entrySituationDto = new EntrySituationDtoReq();
        //entrySituationDto.setBusiCustNos(params.getBusiCustNos());
        //业务客户编号
        //entrySituationDto.setBusiCustNos(new String[]{"cp001","cp002","cp003"});
        //todo;实际获取busiCustNos方法  搜索条件没有公司 查询出全部 buscustnos  有公司就按 公司去查 
       String[] busiCustNos = params.getBusiCustNos();
        if (busiCustNos != null && busiCustNos.length != 0) {
            entrySituationDto.setBusiCustNos(busiCustNos);
        } else {
            //todo;没有所管辖用户会报空指针
            String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
            entrySituationDto.setBusiCustNos(busiCustIds);
        }

        entrySituationDto.setIntoChangeFlag(params.getIntoChangeFlag());
        entrySituationDto.setEmpStatus(params.getEmpStatus());
        entrySituationDto.setJobType(params.getJobType());
        entrySituationDto.setPost(params.getEmpType());
        entrySituationDto.setInsAddType(params.getSocialSecurityType());
        //entrySituationDto.setHandleMonth(params.getHandleMonth());
        entrySituationDto.setTaskStatus(params.getTaskStatus());
        entrySituationDto.setPageNo(params.getPage());
        entrySituationDto.setPageSize(params.getRows());
        entrySituationDto.setEntryStartTime(params.getBeginDate());
        entrySituationDto.setEntryEndTime(params.getEndDate());
        entrySituationDto.setSyncMisFlag(params.getSyncMisFlag());

        if (params.getUserInfoType() != null && params.getUserInfoType().equals(1)) {
            entrySituationDto.setEmpName(params.getUserInfoContent());
        } else if (params.getUserInfoType() != null && params.getUserInfoType().equals(2)) {
            entrySituationDto.setMobile(params.getUserInfoContent());
        } else if (params.getUserInfoType() != null && params.getUserInfoType().equals(3)) {
            entrySituationDto.setIdCode(params.getUserInfoContent());
        }
        String s = JSON.toJSONString(entrySituationDto);

        String method = "olentry.queryEmpEntryList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
        Result result = JSON.parseObject(resultStr, Result.class);
        Object successResult = result.getSuccessResult();
        String s1 = JSON.toJSONString(successResult);
        Map map = JSON.parseObject(s1, Map.class);
        Object pageRecords = map.get("pageRecords");
        Integer totalCount = Integer.parseInt(JSON.toJSONString(map.get("totalCount")));
        String s2 = JSON.toJSONString(pageRecords);
        List<EntrySituationDtoResp> entrySituationDtoResps = JSON.parseArray(s2, EntrySituationDtoResp.class);

        List<EntryInfo> list = new ArrayList<>();
        for (EntrySituationDtoResp entrySituation : entrySituationDtoResps) {
            EntryInfo entryInfo = new EntryInfo();
            entryInfo.setTaskId(entrySituation.getEmpTaskId());
            entryInfo.setEmpName(entrySituation.getEmpName());
            entryInfo.setCompany(entrySituation.getBusiCustNo());
            entryInfo.setIdCode(entrySituation.getIdCode());
            entryInfo.setMobile(entrySituation.getMobile());
            entryInfo.setEmployeeState(entrySituation.getEmpStatus());
            entryInfo.setRemark(entrySituation.getRemark());
            entryInfo.setOrderCompany(entrySituation.getOrderCompany());
            entryInfo.setEntryTime(entrySituation.getEntryTime());
            entryInfo.setSyncMisFlag(entrySituation.getSyncMisFlag());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = entrySituation.getStartTime();
            if (StringUtils.isNotEmpty(startTime)) {
                Date orderTime = sdf.parse(startTime);
                // 订单起始时间
                entryInfo.setOrderTime(orderTime);
            }

            // 社保手续
            entryInfo.setShebaoProcedures(entrySituation.getInsStatus());
            //入职登记
            entryInfo.setEntryRegister(entrySituation.getEnrollStatus());
            //档案手续
            entryInfo.setDanganProcedures(entrySituation.getPersonnelStatus());
            //入职体检
            entryInfo.setEntryTest(entrySituation.getHealthExamStatus());
            //劳动合同
            entryInfo.setLaodongHetong(entrySituation.getContractStatus());
            //备注
            entryInfo.setRemark(entrySituation.getRemark());
            //入职确认状态
            entryInfo.setConfirmStatusName(entrySituation.getConfirmStatusName());
            list.add(entryInfo);
        }
        PageUtils pageUtil = new PageUtils(list, totalCount, params.getRows(), params.getPage());
        //PageUtils pageUtil = new PageUtils(list, totalCount, 1, 2);
        return R.ok().put("page", pageUtil);
    }
}
