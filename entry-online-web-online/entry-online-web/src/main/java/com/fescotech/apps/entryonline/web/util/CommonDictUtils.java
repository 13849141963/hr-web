package com.fescotech.apps.entryonline.web.util;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.util.*;
import com.fescotech.apps.entryonline.util.HttpUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

import java.util.*;

/**
 * Created by cy on 2018/3/6.
 */
public class CommonDictUtils {

    private static String CUSINFO_URL = "http://10.0.75.151:8847/hrmgr/findCusInfoList";
    private static String ORGINFO_URL = "http://10.0.75.151:8847/hrmgr/getOrgInfoByUserAccount";

    //品牌名/公司名字典
    public static Map<String, Object> getCompanyNameDict() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String username = (String)ShiroUtils.getSessionAttribute("username");
        map.put("hrAccount", username);
        String jsonStr = JSON.toJSONString(map);
        String companyNameResultStr = com.fescotech.apps.entryonline.util.HttpUtils.jsonPost(CUSINFO_URL, jsonStr);

        Result companyNameResult = JSON.parseObject(companyNameResultStr, Result.class);
        Object successResult = companyNameResult.getSuccessResult();
        String bsCompanyNames = JSON.toJSONString(successResult);
        List<Map> maps = JSON.parseArray(bsCompanyNames, Map.class);
        Map<String,Object> result = new HashMap<String,Object>();
        for (Map s:maps) {
            result.put(s.get("busiCustId").toString(),s.get("busiCustName"));
        }
        return result;
    }

    //门店字典
    public Map<String, Object> getOrgNameDict() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String username = (String)ShiroUtils.getSessionAttribute("username");
        map.put("useraccount", username);
        String jsonStr = JSON.toJSONString(map);
        String storeNameResultStr = HttpUtils.jsonPost(ORGINFO_URL, jsonStr);

        Result companyNameResult = JSON.parseObject(storeNameResultStr, Result.class);
        Object successResult = companyNameResult.getSuccessResult();
        String bsStoreNames = JSON.toJSONString(successResult);

        List<Map> maps = JSON.parseArray(bsStoreNames, Map.class);
        Map<String,Object> result = new HashMap<String,Object>();
        for (Map s:maps) {
            result.put(s.get("orgId").toString(),s.get("orgName"));
        }
        return result;
    }

    //普通数据字典
    //hr端mapper:百盛职务类型:29,百盛工作类型:28,社保手续进度-入职登记-人事档案:35,
    //入职查询:百盛职务类型:29,用工类型:40,签三方:32,是否粮农:32,社保新增类型:41,
    //        缴费人员类别:19,需转入变更:32,是否缴纳公积金:32,入职手续办理进度:35
    public static Map<String,Object> getCommonDict(Integer dictNo) throws Exception {
        String bsTaskStatus = toOpenPlateformGetDictResult(dictNo);
        Map<String, Object> stringObjectMap = commonResolveSuccessResult(bsTaskStatus);
        return stringObjectMap;
    }




    private static String getDictQueryParam(Integer val){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("dictType",val);
        String s =  JSON.toJSONString(map);
        return s;
    }

    private static String toOpenPlateformGetDictResult(Integer val) throws Exception {
        String dictQueryParam = getDictQueryParam(val);
        String jsonPost = ApiUtilOpenPlateform.callOpenPlateformApi("olentry.queryDict",dictQueryParam);
        Result result = JSON.parseObject(jsonPost, Result.class);
        Object successResult = result.getSuccessResult();
        String s = JSON.toJSONString(successResult);
        return s;
    }

    private static Map<String, Object> commonResolveSuccessResult(String str){

        List<Map> maps = JSON.parseArray(str, Map.class);
        Map<String,Object> result = new HashMap<String,Object>();
        for (Map s:maps) {
            result.put(s.get("itemCode").toString(),s.get("itemName"));
        }
        return result;
    }


    public static String[] getRemoveDuplicateCustomerIds() throws Exception {
        List<CustomerVo> removeDuplicateCustomer = getRemoveDuplicateCustomer();
        String[] busiCustIds  = new String[removeDuplicateCustomer.size()];
        for (int i = 0; i < removeDuplicateCustomer.size(); i++) {
            String busiCustId = removeDuplicateCustomer.get(i).getBusiCustId();
            busiCustIds[i] = busiCustId;
        }
        return busiCustIds;
    }

    public static List<CustomerVo> getRemoveDuplicateCustomer() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String username = (String)ShiroUtils.getSessionAttribute("username");
        map.put("hrAccount", username);
        String jsonStr = JSON.toJSONString(map);
        //String companyNameResultStr = HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/findCusInfoList", jsonStr);

        String method = "hrmgr.findCusInfoList";
        String companyNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);

        Result companyNameResult = JSON.parseObject(companyNameResultStr, Result.class);
        Object successResult = companyNameResult.getSuccessResult();
        String companyNames = JSON.toJSONString(successResult);

        List<CustomerVo> customerVos = JSON.parseArray(companyNames, CustomerVo.class);

        ArrayList<CustomerVo> removeDuplicateCustomer = removeDuplicateCustomer(customerVos);
        return removeDuplicateCustomer;
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
