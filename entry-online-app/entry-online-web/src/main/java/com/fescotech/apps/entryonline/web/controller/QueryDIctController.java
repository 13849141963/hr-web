package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.util.HttpUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by cy on 2018/3/2.
 */
@Controller
public class QueryDIctController {

    //按公司名称检索
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryCompanyNamesDict")
    @ResponseBody
    public String getCompanyNames() throws Exception {
    	Map<String, Object> map = new HashMap<>();
        String username = (String)ShiroUtils.getSessionAttribute("username");
        map.put("hrAccount", username);
        String jsonStr = JSON.toJSONString(map);
        
        String method = "hrmgr.findCusInfoList";
		String companyNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method,jsonStr);
        //String companyNameResultStr = HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/findCusInfoList", jsonStr);
		Result res = JSON.parseObject(companyNameResultStr,Result.class);
		Object customerResult = res.getSuccessResult();
		List<CustomerVo> customerListResult = JSON.parseArray(JSON.toJSONString(customerResult), CustomerVo.class);
		ArrayList<CustomerVo> removeDuplicateCustomer = removeDuplicateCustomer(customerListResult);
        return JSON.toJSONString(removeDuplicateCustomer);
    }

    //获取门店list
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryStoreNamesDict")
    @ResponseBody
    public String getEmpNames() throws Exception {
    	Map<String, Object> map = new HashMap<>();
        String username = (String)ShiroUtils.getSessionAttribute("username");
        map.put("useraccount", username);
        String jsonStr = JSON.toJSONString(map);
        String method = "hrmgr.getOrgInfoByUserAccount";
	    String storeNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result companyNameResult = JSON.parseObject(storeNameResultStr, Result.class);
        Object successResult = companyNameResult.getSuccessResult();
        String bsStoreNames = JSON.toJSONString(successResult);
        return bsStoreNames;
    }

    //按手续状态检索
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryTaskStatusDict")
    @ResponseBody
    public String getTaskStatus() throws Exception {
        String bsTaskStatus = toOpenPlateformGetDictResult(35);
        return bsTaskStatus;
    }

    //是否需要转入变更
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryIntoChangeFlagDict")
    @ResponseBody
    public String getIntoChangeFlag() throws Exception {
        String bsIntoChangeFlag = toOpenPlateformGetDictResult(32);
        return bsIntoChangeFlag;
    }

    //按员工状态检索
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryEmpStatusDict")
    @ResponseBody
    public String getEmpStatus() throws Exception {
        String bsEmpStatus = toOpenPlateformGetDictResult(1);
        return bsEmpStatus;
    }

    //按员工类型检索
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryJobTypesDict")
    @ResponseBody
    public String getJobTypes() throws Exception {
        String bsJobType = toOpenPlateformGetDictResult(28);
        return bsJobType;
    }

    //按员工职务检索
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryEmpTypesDict")
    @ResponseBody
    public String getEmpTypes() throws Exception {
        String bsEmpTypes = toOpenPlateformGetDictResult(29);
        return bsEmpTypes;
    }

    //按社保手续检索
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/querySocialSecurityTypesDict")
    @ResponseBody
    public String getSocialSecurityTypes() throws Exception {
        String bsSocialSecurityTypes = toOpenPlateformGetDictResult(41);
        return bsSocialSecurityTypes;
    }

    //入职手续状态
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryEntryStatusDict")
    @ResponseBody
    public String getEntryStatus() throws Exception {
        String bsEntryStatus = toOpenPlateformGetDictResult(35);
        return bsEntryStatus;
    }

    //社保手续状态
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/querySocialSecurityStatusDict")
    @ResponseBody
    public String getSocialSecurityStatus() throws Exception {
        String bsSocialSecurityStatus = toOpenPlateformGetDictResult(35);
        return bsSocialSecurityStatus;
    }

    //档案手续状态
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryProfileStatusDict")
    @ResponseBody
    public String getProfileStatus() throws Exception {
        String bsProfileStatus = toOpenPlateformGetDictResult(35);
        return bsProfileStatus;
    }

    //入职体检手续状态
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryPhysicalExaminationStatusDict")
    @ResponseBody
    public String getPhysicalExaminationStatus() throws Exception {
        String bsPhysicalExaminationStatus = toOpenPlateformGetDictResult(35);
        return bsPhysicalExaminationStatus;
    }

    //劳动合同手续状态
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryContractStatusDict")
    @ResponseBody
    public String getContractStatus() throws Exception {
        String bsContractStatus = toOpenPlateformGetDictResult(35);
        return bsContractStatus;
    }

    //完成状态
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryCompleteStatusDict")
    @ResponseBody
    public String getCompleteStatus() throws Exception {
        String bsPhysicalExaminationStatus = toOpenPlateformGetDictResult(49);
        return bsPhysicalExaminationStatus;
    }

    private String getDictQueryParam(Integer val) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("dictType", val);
        String s = JSON.toJSONString(map);
        return s;
    }

    private String toOpenPlateformGetDictResult(Integer val) throws Exception {
        String dictQueryParam = getDictQueryParam(val);
        String jsonPost = ApiUtilOpenPlateform.callOpenPlateformApi("olentry.queryDict", dictQueryParam);
        Result result = JSON.parseObject(jsonPost, Result.class);
        Object successResult = result.getSuccessResult();
        String s = JSON.toJSONString(successResult);
        return s;
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
