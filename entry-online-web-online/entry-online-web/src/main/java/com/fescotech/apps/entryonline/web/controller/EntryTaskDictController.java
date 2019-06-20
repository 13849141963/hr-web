package com.fescotech.apps.entryonline.web.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.util.HttpUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@Controller
public class EntryTaskDictController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/entryProcedures")
    @ResponseBody
    //入职手续
    public Result taskCreate() throws Exception {
        String method = "olentry.listEntryProcedures";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, "{}");
        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }


    //入职指南
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/entryGuides")
    @ResponseBody
    public Result listEntryGuides() throws Exception {
        String method = "olentry.listEntryGuides";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, "{}");
        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    //根据HR帐号获得所管辖客户列表
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/customerCompany")
    @ResponseBody
    public Result customerCompany() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String username = (String) ShiroUtils.getSessionAttribute("username");
        map.put("hrAccount", username);
        String jsonStr = JSON.toJSONString(map);
        //根据HR帐号获得所管辖客户列表，包含客户到合同附件层级数据
        String method = "hrmgr.findCusInfoList";
        String companyNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);
        Result res = JSON.parseObject(companyNameResultStr, Result.class);
        Object customerResult = res.getSuccessResult();
        List<CustomerVo> customerListResult = JSON.parseArray(JSON.toJSONString(customerResult), CustomerVo.class);
        ArrayList<CustomerVo> removeDuplicateCustomer = removeDuplicateCustomer(customerListResult);
        res.setSuccessResult(removeDuplicateCustomer);
        return res;
    }

    private static ArrayList<CustomerVo> removeDuplicateCustomer(List<CustomerVo> customers) {
        Set<CustomerVo> set = new TreeSet<CustomerVo>(new Comparator<CustomerVo>() {
            @Override
            public int compare(CustomerVo o1, CustomerVo o2) {
                return o1.getBusiCustId().compareTo(o2.getBusiCustId());
            }
        });
        set.addAll(customers);
        return new ArrayList<CustomerVo>(set);
    }
}
