package com.fescotech.apps.entryonline.web.controller;

/**
 * Created by cy on 2018/2/11.
 */
import java.util.*;

import com.fescotech.apps.entryonline.entity.ModelData.SocialSecurityDataVoResp;
import com.fescotech.apps.entryonline.util.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.dto.SocialSecurityDto;
import com.fescotech.apps.entryonline.entity.vo.SocialSecurityVoReq;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@Controller
public class ShebaoController {

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryShebao")
    @ResponseBody
    public Map getList(SocialSecurityVoReq params) throws Exception {
        SocialSecurityDto socialSecurityDto = new SocialSecurityDto();
        socialSecurityDto.setInsStatus(params.getInsStatus());
        socialSecurityDto.setJobType(params.getJobType());
        socialSecurityDto.setIntoChangeFlag(params.getIntoChangeFlag());

        if (params.getUserInfoType() != null && params.getUserInfoType().equals(1)) {
            socialSecurityDto.setEmpName(params.getUserInfoContent());
        } else if (params.getUserInfoType() != null && params.getUserInfoType().equals(2)) {
            socialSecurityDto.setMobile(params.getUserInfoContent());
        } else if (params.getUserInfoType() != null && params.getUserInfoType().equals(3)) {
            socialSecurityDto.setIdCode(params.getUserInfoContent());
        }
        if (params.getCompanyName() != null && params.getCompanyName() != "") {
            socialSecurityDto.setBusiCustNos((new String[]{params.getCompanyName()}));
        } else {
            socialSecurityDto.setBusiCustNos(new String[]{"大望路店", "6"});
        }
        String s = JSON.toJSONString(socialSecurityDto);

        String method = "olentry.queryInsMaterial";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
        HashMap<String, Object> map = new HashMap<>();
        // Result result = JSON.parseObject(resultStr, Result.class);
        ArrayList<SocialSecurityDataVoResp> result = new ArrayList<>();
        result.add(new SocialSecurityDataVoResp());
        result.add(new SocialSecurityDataVoResp());
        result.add(new SocialSecurityDataVoResp());
        result.add(new SocialSecurityDataVoResp());
        result.add(new SocialSecurityDataVoResp());
        map.put("result", result);
        map.put("page", params.getPage());
        return map;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/entryOnline/queryDictForShebao")
    @ResponseBody
    public Map getrawtypes() throws Exception {
        Map result =  new HashMap<String,Object>();
        String bsInsStatus = toOpenPlateformGetDictResult(33);
        String bsJobType = toOpenPlateformGetDictResult(30);
        String bsUserInfoType = toOpenPlateformGetDictResult(24);
        String bsIntoChangeFlag = toOpenPlateformGetDictResult(29);
        String bsEmpStatus = toOpenPlateformGetDictResult(1);
        String bsEmpType = toOpenPlateformGetDictResult(19);
        Map<String, Object> map = new HashMap<>();
        map.put("useraccount", "dianzhang1");
        String jsonStr = JSON.toJSONString(map);
        String companyNameResultStr = HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/getOrgInfoByUserAccount", jsonStr);

        Result companyNameResult = JSON.parseObject(companyNameResultStr, Result.class);
        Object successResult = companyNameResult.getSuccessResult();
        String bsCompanyName = JSON.toJSONString(successResult);

        result.put("insStatus",bsInsStatus);
        result.put("jobType",bsJobType);
        result.put("intoChangeFlag",bsIntoChangeFlag);
        result.put("userInfoType",bsUserInfoType);
        result.put("companyName",bsCompanyName);
        result.put("empStatus",bsEmpStatus);
        result.put("empTypes",bsEmpType);
        return result;
    }

    private String getDictQueryParam(Integer val){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("dictType",val);
        String s =  JSON.toJSONString(map);
        return s;
    }

    private String toOpenPlateformGetDictResult(Integer val) throws Exception {
        String dictQueryParam = getDictQueryParam(val);
        String jsonPost = ApiUtilOpenPlateform.callOpenPlateformApi("olentry.queryDict",dictQueryParam);
        Result result = JSON.parseObject(jsonPost, Result.class);
        Object successResult = result.getSuccessResult();
        String s = JSON.toJSONString(successResult);
        return s;
    }


}