package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cy on 2018/6/25.
 */
@Controller
@RequestMapping("/hfcalculator/")
public class HfcalculatorController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getNationwideBaseProdArea")
    @ResponseBody
    //获取城市列表,参考 2.3.1 获取城市列表
    public Result getNationwideBaseProdArea(Integer pageIndex,Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getNationwideBaseProdArea";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getNationwideProdLsPkgInfo")
    @ResponseBody
    //获取缴纳方案列表,参考 2.3.2 根据城市获取社保比例、基数
    public Result getNationwideProdLsPkgInfo(Integer cityId,Integer esAccountType,Integer type) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("cityId",cityId);
        map.put("esAccountType",esAccountType);
        map.put("type",type);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getNationwideProdLsPkgInfo";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getInsAccuCalcPriceDetail")
    @ResponseBody
    //查询公积金社保缴纳基数和比例,参考 2.3.3 查询公积金社保缴纳基数和比例
    public Result getInsAccuCalcPriceDetail(Integer cityId,Double baseApply,Integer type,Integer pkgId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("cityId",cityId);
        map.put("baseApply",baseApply);
        map.put("type",type);
        map.put("pkgId",pkgId);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getInsAccuCalcPriceDetail";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

}
