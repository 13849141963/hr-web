package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cy on 2018/6/1.
 */
@Controller
@RequestMapping("/customer/")
public class CustomerController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getCustInfo")
    @ResponseBody
    //入职手续
    public Result getCustInfo() throws Exception {
        String[] removeDuplicateCustIds = CommonDictUtils.getRemoveDuplicateCustIds();

        Map<String, Object> map = new HashMap<>();
        //String username = (String) ShiroUtils.getSessionAttribute("username");
        String join = StringUtils.join(removeDuplicateCustIds, ",");
        map.put("custIds", join);
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getCustInfoById";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }
}
