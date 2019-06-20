import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fesco.pafa.util.HttpClientUtil;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.apps.entryonline.common.util.ImgUtilOpenPlateform;
import com.fescotech.apps.entryonline.dto.LoadHouseholdReq;
import com.fescotech.apps.entryonline.dto.PdfFileParamDtoReq;
import com.fescotech.apps.entryonline.entity.BusiCust;
import com.fescotech.apps.entryonline.entity.EntryTask;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.util.HttpUtils;
import com.fescotech.apps.entryonline.util.Res;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import com.fescotech.common.util.Md5Util;

import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ApiTest {

    // 获取用户登录功能菜单
    @Test
    public void getUserRoleMenu() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("userAccount", "yum-onsite1");
            map.put("userType", "3");
            
            /*map.put("userAccount", "hr3");
            map.put("userType", "3");*/
            String jsonStr = JSON.toJSONString(map);
            String jsonPost = HttpUtils.jsonPost(
                    "http://10.0.75.151:8847/hrmgr/getUserRoleMenu", jsonStr);

            String json = HttpClientUtil.httpPost(
                    "http://10.0.75.151:8847/hrmgr/getUserRoleMenu", jsonStr);

            System.out.println("调用结果---> " + jsonPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserRoleMenuOpenPlateForm() {
        try {
            Map<String, Object> map = new HashMap<>();
            //bjn255User
            //yum-onsite1
            map.put("userAccount", "yum-onsite1");
            map.put("userType", "3");
            String jsonStr = JSON.toJSONString(map);
            String method = "hrmgr.getUserRoleMenu";
            String companyNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method,
                    jsonStr);
            System.out.println(companyNameResultStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void hrLogin() {
        try {
            Map<String, Object> map = new HashMap<>();
            String md5Encode = Md5Util.MD5Encode("123456");
            System.out.print(md5Encode);
            map.put("hrAccount", "dianzhang1");
            map.put("password", md5Encode);
            //map.put("userType", 1);
            String jsonStr = JSON.toJSONString(map);
            String jsonPost = HttpUtils.jsonPost(
                    "http://10.0.75.151:8847/hrmgr/hrLogin", jsonStr);
            System.out.println("调用结果---> " + jsonPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hrLoginOpenPlateform() {
        try {
            Map<String, Object> map = new HashMap<>();
            String md5Encode = Md5Util.MD5Encode("123456");
            map.put("hrAccount", "yum-onsite1");
            map.put("password", md5Encode);
            String jsonStr = JSON.toJSONString(map);

            String method = "hrmgr.hrLogin";
            String result = ApiUtilOpenPlateform.callOpenPlateformApiHr(method,
                    jsonStr);
            System.out.println(result);

			/*
             * String jsonPost =
			 * HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/hrLogin",
			 * jsonStr); System.out.println("调用结果---> " + jsonPost);
			 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findCusInfoList() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("hrAccount", "hr3");
            String jsonStr = JSON.toJSONString(map);

            String method = "hrmgr.findCusInfoList";
            String companyNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                    jsonStr);

            System.out.println(companyNameResultStr);
			/*Result res = JSON.parseObject(companyNameResultStr,Result.class);
			Object customerResult = res.getSuccessResult();*/

			/*
			 * String jsonPost =
			 * HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/findCusInfoList"
			 * , jsonStr); System.out.println("调用结果---> " + jsonPost);
			 * Result<List<CustomerVo>>res = JSON.parseObject(jsonPost,new
			 * TypeReference<Result<List<CustomerVo>>>(){});
			 * System.out.println("调用结果---> " +
			 * JSON.toJSONString(res.getSuccessResult()));
			 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void findCusInfoListOpenplateform() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("hrAccount", "dianzhang1");
            String jsonStr = JSON.toJSONString(map);
            String jsonPost = HttpUtils.jsonPost(
                    "http://10.0.75.151:8847/hrmgr/findCusInfoList", jsonStr);
            System.out.println("调用结果---> " + jsonPost);
            Result<List<CustomerVo>> res = JSON.parseObject(jsonPost,
                    new TypeReference<Result<List<CustomerVo>>>() {
                    });
            System.out.println("调用结果---> "
                    + JSON.toJSONString(res.getSuccessResult()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 根据hr账号查询管理的机构信息 getOrgInfoByUserAccount
    @Test
    public void getOrgInfoByUserAccount() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("useraccount", "dianzhang1");
            String jsonStr = JSON.toJSONString(map);
            String jsonPost = HttpUtils.jsonPost(
                    "http://10.0.75.151:8847/hrmgr/getOrgInfoByUserAccount",
                    jsonStr);
            System.out.println("调用结果---> " + jsonPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hrFindPass() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("hrAccount", "11111111");
            map.put("newPassword", "ea8a0d75d21283e123");
            String jsonStr = JSON.toJSONString(map);
            String jsonPost = HttpUtils.jsonPost(
                    "http://10.0.75.151:8847/hrmgr/hrFindPass", jsonStr);
            System.out.println("调用结果---> " + jsonPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建入职任务
    @Test
    public void createEntryTask() throws Exception {
        List<BusiCust> busiCusts = new ArrayList<>();
        BusiCust busiCust1 = new BusiCust();
        busiCust1.setBusiCustNo("1");
        busiCust1.setBusiCustName("555");
		
		/*BusiCust busiCust2 = new BusiCust();
		busiCust2.set*/


        busiCusts.add(busiCust1);
        String[] s = {"1", "2", "3"};
        String[] s1 = {"1", "2", "3"};
        String s2 = "1,2,3";
        EntryTask entryTask = new EntryTask();
        entryTask.setBusiCusts(busiCusts);
        entryTask.setCreator("1");
        // entryTask.setBusiCustNos(s);
        entryTask.setCreatorName("44");
        entryTask.setTaskName("testName2fffrrraaa");
        entryTask.setProcedures("1,2,3");
        entryTask.setGuides(s2);
        entryTask.setOtherGuideName("111");
        entryTask.setOtherGuideContent("111111");
        // entryTask.setBusiRep(111L);
        String jsonStr = JSON.toJSONString(entryTask);
        String method = "olentry.createEntryTask";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 修改入职任务 olentry.updateEntryTask
    @Test
    public void updateEntryTask() throws Exception {
        String[] s = {"1", "2", "4"};
        String s1 = "1,2,3";
        String s2 = "1,2,3";
        EntryTask entryTask = new EntryTask();
        entryTask.setTaskId("7351108bb3");
        entryTask.setCreator("1");
        entryTask.setBusiCustNos(s);
        entryTask.setCreatorName("test");
        entryTask.setTaskName("testName3");
        entryTask.setProcedures(s1);
        entryTask.setGuides(s2);
        entryTask.setOtherGuideName("1112");
        entryTask.setOtherGuideContent("1111112");
        String jsonStr = JSON.toJSONString(entryTask);
        String method = "olentry.updateEntryTask";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    @Test
    public void queryEntryTask() throws Exception {
        EntryTask entryTask = new EntryTask();
        entryTask.setCreator("dianzhang1");
        String jsonStr = JSON.toJSONString(entryTask);
        String method = "olentry.queryEntryTask";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    @Test
    public void listEntryProcedures() throws Exception {
        String method = "olentry.listEntryProcedures";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method, "{}");
        System.out.println(result);
    }

    @Test
    public void listEntryGuides() throws Exception {
        String method = "olentry.listEntryGuides";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method, "{}");

        Result result22 = JSON.parseObject(result, Result.class);
        System.out.println(result22);
    }

    // 2.4获取手续进度列表 olentry.queryDictItem
    @Test
    public void listEntryProcedureSteps() throws Exception {
        String method = "olentry.listEntryProcedureSteps";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                "{procedureId:22}");
        System.out.println(result);
    }

    // 字典项接口 olentry.queryDict
    @Test
    public void queryDictItem() throws Exception {
        String method = "olentry.queryDictItem";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                "{\"dictType\":\"19\",\"dictCode\":\"3\"}");
        System.out.println(result);
    }

    // 2.5获取入职套餐列表
    @Test
    public void listEntryCheckupServices() throws Exception {
        String method = "olentry.listEntryCheckupServices";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method, "{}");
        System.out.println(result);
    }

    // 2.2字典项列表接口
    @Test
    public void queryDict() throws Exception {
        String method = "olentry.queryDict";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                "{\"dictType\":\"30\"}");
        System.out.println(result);
    }

    // 生成访问链接
    @Test
    public void createLinks() throws Exception {
        EntryTask entryTask = new EntryTask();
        entryTask.setTaskId("25c19a6508");
        String jsonStr = JSON.toJSONString(entryTask);
        String method = "olentry.createLinks";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 没有权限 社保电子材料列表查询
    @Test
    public void queryInsMaterial() throws Exception {
        String[] a = {"CUST003", "CUST001"};
        Map<String, Object> map = new HashMap<>();
        map.put("busiCustNos", a);
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.queryInsMaterial";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 5.2 获得员工社保新参信息，
    // 没有访问权限
    @Test
    public void getNewInsInfo() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("empTaskId", "123");
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.getNewInsInfo";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 5.3获取身份证照片信息,
    // 没有访问权限
    @Test
    public void getIdPics() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("empTaskId", "123");
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.getIdPics";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 5.4获取社保照片信息
    // 没有访问权限
    @Test
    public void getInsPics() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("empTaskId", "123");
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.getInsPics";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 获取通知模板
    @Test
    public void getNotifyTemplate() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("creator", "1");
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.getNotifyTemplate";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 5.5批量下载户口本
    // 没有访问权限
    @Test
    public void batchLoadHousehold() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String[] s = {"CUST003", "CUST001"};
        map.put("busiCustNos", s);
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.batchLoadHousehold";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 5.6批量打印新参表
    // 没有访问权限
    @Test
    public void batchPrintNewIns() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String[] s = {"CUST003", "CUST001"};
        map.put("busiCustNos", s);
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.batchPrintNewIns";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    // 5.7入职办理情况查询/导出 hr端
    @Test
    public void queryEntryProgressList() throws Exception {
        Map<String, Object> map = new HashMap<>();
        //String[] s = { "cp001", "cp002", "cp003" };
        String[] s = {"243691", "430676", "430678"};
        map.put("busiCustNos", s);
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.queryEntryProgressList";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }

    @Test
    public void queryLoadHousehold() throws Exception {
        LoadHouseholdReq loadHouseholdReq = new LoadHouseholdReq();
        loadHouseholdReq.setBusiCustNos(new String[]{"cp001"});
        loadHouseholdReq.setEmpTaskIds(new String[]{});

        String method = "olentry.batchGetIdPics";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                JSON.toJSONString(loadHouseholdReq));
        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result);
    }

    // 员工入职情况查询
    @Test
    public void queryEmpEntryList() throws Exception {
        Long[] s = new Long[]{78L, 79L, 80L, 81L};
        Map<String, Object> map = new HashMap<>();
        map.put("busiCustNos", new String[]{"cp001", "cp002", "cp003"});
        map.put("empTaskIds", s);
        String method = "olentry.queryEmpEntryList";
        System.out.println("3333333333" + JSON.toJSONString(map));
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                JSON.toJSONString(map));
        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result);
    }

    // 员工数据下载导出
    @Test
    public void queryExpEntryList() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("busiCustNos", new String[]{"cp001", "cp002", "cp003"});
        String method = "olentry.queryExpEntryList";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                JSON.toJSONString(map));
        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result);
    }

    @Test
    public void sss() throws Exception {
        String[] aa = {"3"};
        HashMap<String, Object> map = new HashMap<>();
        map.put("dictType", aa);
        String s = JSON.toJSONString(map);
        String sss = ApiUtilOpenPlateform.callOpenPlateformApi(
                "olentry.queryDict", s);
    }

    @Test
    public void getUserByBusiCust() throws Exception {
        String method = "csmgr.getUserByBusiCust";
        Map<String, Object> map = new HashMap<>();
        map.put("busiCustId", 430678L);
        String s = JSON.toJSONString(map);
        String sss = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
        System.out.println(sss);
    }

    //获取门店list   {"orgId":5,"orgName":"大望路店"},{"orgId":6,"orgName":"朝阳门店"},{"orgId":7,"orgName":"大望路店"}
    @Test
    public void getOrgInfoByUserAccountOpen() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("useraccount", "hr3");
        String jsonStr = JSON.toJSONString(map);
        String method = "hrmgr.getOrgInfoByUserAccount";
        //String storeNameResultStr = HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/getOrgInfoByUserAccount", jsonStr);
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method, jsonStr);
        System.out.println(result);
    }


    @Test
    public void download() throws Exception {
        List list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 2);
        map.put("fileType", 1);
        map.put("returnFileType", 1);
        map.put("fileId", "66F30274CE56130FE053DF4B000A1B8C");
        list.add(map);
        String result = ApiUtilOpenPlateform.callOpenPlateformApiHr("publicmgr.mergePdfFileByFileId", JSON.toJSONString(list));
        //InputStream is =new ByteArrayInputStream(text.getBytes("UTF-8"))
        System.out.println(result);
    }

    @Test
    public void downloadInnerNetWork() throws Exception {
        List list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 2);
        map.put("fileType", 1);
        map.put("returnFileType", 1);
        map.put("fileId", "66F30274CE56130FE053DF4B000A1B8C");
        list.add(map);

        String result = HttpUtils.jsonPost("http://10.0.75.151:8855/publicmgr/mergePdfFileByFileId", JSON.toJSONString(list));
        //InputStream is =new ByteArrayInputStream(text.getBytes("UTF-8"))
        System.out.println(result);
    }

    @Test
    public void batchLoadEmpInfo() throws Exception {
        Map<String, Object> map = new HashMap<>();
        String[] s = {"430676", "430678"};
        map.put("busiCustNos", s);
        String jsonStr = JSON.toJSONString(map);
        String method = "olentry.batchLoadEmpInfo";
        String result = ApiUtilOpenPlateform.callOpenPlateformApi(method,
                jsonStr);
        System.out.println(result);
    }
    @Test
    public void ImgUtilOpenPlateform() throws Exception {

        ImgUtilOpenPlateform.downloadFile("68B132BC06EE01A6E053C0A8005CB24C");
    }

}
