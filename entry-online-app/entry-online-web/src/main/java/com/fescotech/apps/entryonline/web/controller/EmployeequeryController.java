package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.vo.EmpinexitReq;
import com.fescotech.apps.entryonline.entity.vo.EmployeequeryReq;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cy on 2018/6/8.
 */
@Controller
@RequestMapping("/employeequery/")
public class EmployeequeryController {
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "getEmpInfos")
    @ResponseBody
    //员工查询,参考 2.4.9 根据查询条件获取雇员列表(hr接口)
    public Result getEmpInfos(EmployeequeryReq employeequeryReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("custIds", StringUtils.join(employeequeryReq.getCustIds(),","));
        map.put("busiCustIds", StringUtils.join(employeequeryReq.getBusiCustIds(),","));
        map.put("uniqNo", employeequeryReq.getUniqNo());
        map.put("empName", employeequeryReq.getEmpName());
        map.put("idCardNo",employeequeryReq.getIdCardNo());
        map.put("pageIndex", employeequeryReq.getPageIndex());
        map.put("pageSize", employeequeryReq.getPageSize());
        String jsonStr = JSON.toJSONString(map);
        String method = "oldhr.getEmpInfos";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        return result;
    }

    //员工详细信息;调用EmpinexitController中员工信息明细接口
}
