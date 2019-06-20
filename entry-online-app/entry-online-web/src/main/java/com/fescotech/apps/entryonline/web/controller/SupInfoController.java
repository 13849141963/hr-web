package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.vo.SupInfoReq;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cy on 2018/6/6.
 */

@Controller
@RequestMapping("/supinfo/")
public class SupInfoController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getBusiCustListByCustId")
    @ResponseBody
    //根据客户id查询业务客户id列表,参考 2.2.6 根据客户id查询业务客户id列表
    public Result getBusiCustListByCustId(long custId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getBusiCustListByCustId";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getCsCustConByDepCustId")
    @ResponseBody
    //根据业务客户id查询合同列表,参考 2.2.8 根据业务客户id查询合同列表
    public Result getCsCustConByDepCustId(long busiCustId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("busiCustId", busiCustId);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getCsCustConByDepCustId";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getCsConAcceByConId")
    @ResponseBody
    //根据合同id查询合同附件id列表,参考 2.2.9 根据合同id查询合同附件id列表
    public Result getCsConAcceByConId(long conId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("conId", conId);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getCsConAcceByConId";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getNatiCountSuppInfo")
    @ResponseBody
    //获取各地供应商信息,参考 2.4 获取各地供应商信息
    public Result getNatiCountSuppInfo(SupInfoReq supInfoReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(supInfoReq.getCustIds()!=null && supInfoReq.getCustIds().size()!=0) {
            map.put("custIds", StringUtils.join(supInfoReq.getCustIds(), ","));
        }
        if(supInfoReq.getBusiCustIds()!=null && supInfoReq.getBusiCustIds().size()!=0) {
            map.put("busiCustIds", StringUtils.join(supInfoReq.getBusiCustIds(), ","));
        }
        if(supInfoReq.getCustConIds()!=null && supInfoReq.getCustConIds().size()!=0) {
            map.put("custConIds", StringUtils.join(supInfoReq.getCustConIds(), ","));
        }
        if(supInfoReq.getConAcceIds()!=null && supInfoReq.getConAcceIds().size()!=0) {
            map.put("conAcceIds", StringUtils.join(supInfoReq.getConAcceIds(), ","));
        }
        map.put("pageIndex", supInfoReq.getPageIndex());
        map.put("pageSize", supInfoReq.getPageSize());
        map.put("custIds", "244761");

        String jsonStr = JSON.toJSONString(map);

        String method = "oldhr.getNatiCountSuppInfo";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }
}
