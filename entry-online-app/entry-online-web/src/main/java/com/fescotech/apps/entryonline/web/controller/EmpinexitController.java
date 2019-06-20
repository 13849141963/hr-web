package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.vo.EmpinexitReq;
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
@RequestMapping("/empinexit/")
public class EmpinexitController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getEntryResignationInfoList")
    @ResponseBody
    //获取员工入离职统计列表,参考 2.4.2 获取员工入离职统计列表
    //ss
    public Result getEntryResignationInfoList(EmpinexitReq empinexitReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custIds", StringUtils.join(empinexitReq.getCustIds(),","));
        map.put("busiCustIds", StringUtils.join(empinexitReq.getBusiCustIds(),","));
        map.put("custConIds", StringUtils.join(empinexitReq.getCustConIds(),","));
        map.put("conAcceIds", StringUtils.join(empinexitReq.getConAcceIds(),","));
        map.put("yearMonth",empinexitReq.getYearMonth().replace(":",""));
        map.put("pageIndex", empinexitReq.getPageIndex());
        map.put("pageSize", empinexitReq.getPageSize());
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getEntryResignationInfoList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getOrderListByStatisInfo")
    @ResponseBody
    //根据统计信息获取雇员集合对应的订单信息列表 ,参考 2.4.3 ----2级列表:1为本月新增预派办理，2为本月新增委派办理，3为本月撤离办理，4为本月在派
    public Result getOrderListByStatisInfo(Long acceId,String ym,Integer type,Integer pageIndex,Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("acceId", acceId);
        map.put("ym", ym.replace(":",""));
        map.put("type", type);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getOrderListByStatisInfo";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getSendEmpProdDetList")
    @ResponseBody
    //根据雇员订单id获取员工派出信息列表 ,参考 2.4.4 ----3级列表:派出产品明细
    public Result getSendEmpProdDetList(Long orderId,Integer pageIndex,Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getSendEmpProdDetList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getEmpBasicInfo")
    @ResponseBody
    //获取雇员个人基本信息 ,参考 2.4.10 ----3级列表:员工信息
    public Result getEmpBasicInfo(Long uniqNo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("uniqNo", uniqNo);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getEmpBasicInfo";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getCsOiConfirmInfoByOrderId")
    @ResponseBody
    //根据雇员订单id获取全国交互信息列表 ,参考 2.4.5 ----3级列表:全国信息交互
    public Result getCsOiConfirmInfoByOrderId(Long orderId,Integer pageIndex,Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getCsOiConfirmInfoByOrderId";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }
}
