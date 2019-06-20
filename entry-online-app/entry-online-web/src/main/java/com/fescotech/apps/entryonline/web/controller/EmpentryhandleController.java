package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.vo.EmpentryhandleReq;
import com.fescotech.apps.entryonline.entity.vo.SupInfoReq;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cy on 2018/6/8.
 */
@Controller
@RequestMapping("/empentryhandle/")
public class EmpentryhandleController {

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getCustEntryFormalitiesInfoList")
    @ResponseBody
    //员工入职手续办理,参考 2.4.6 获取入职手续办理情况统计列表
    public Result getCustEntryFormalitiesInfoList(EmpentryhandleReq empentryhandleReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custIds", StringUtils.join(empentryhandleReq.getCustIds(),","));
        map.put("busiCustIds", StringUtils.join(empentryhandleReq.getBusiCustIds(),","));
        map.put("custConIds", StringUtils.join(empentryhandleReq.getCustConIds(),","));
        map.put("conAcceIds", StringUtils.join(empentryhandleReq.getConAcceIds(),","));
        map.put("pageIndex", empentryhandleReq.getPageIndex());
        map.put("pageSize", empentryhandleReq.getPageSize());
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getCustEntryFormalitiesInfoList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getEmpEntryFormalitiesInfoList")
    @ResponseBody
    //根据统计信息获取雇员集合对应的订单信息列表 ,参考 2.4.7 ----2级列表:1手续代办，2手续办理中，3手续办理完成
    public Result getEmpEntryFormalitiesInfoList(Long acceId,Integer type,Integer pageIndex,Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("acceId", acceId);
        map.put("type", type);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getEmpEntryFormalitiesInfoList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getSendEmpProdDetList")
    @ResponseBody
    //员工入职手续办理情况明细信息列表 ,参考 2.4.8 ----3级列表:入职手续明细
    public Result getSendEmpProdDetList(Long uniqNo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("uniqNo", uniqNo);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getSendEmpProdDetList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    //3级列表:员工信息;调用EmpinexitController中员工信息明细接口

}
