package webservice.test;

import com.fesco.fws.ws.WsUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

/**
 * @author cao.guo.dong
 * @create 2018-01-31 10:17
 * @desc 开放平台测试
 **/

public class openPlantformTest {

   /*2.1.56.获取雇员最新派出信息：getEsOfferInfoByUniqNo*/
    public static void main(String[] args) throws Exception {
		/*appkey是自己的
		 *sign签名不加时间戳
		 *param是json格式的入参例如{"uniqNo":"110"}
		 *最后一个参数是固定就是json*/

        String appkey = "WeiXinSystem";
        String sign = "8a93ec93fa219a48d1ec1c6f311939f8";
        String param = "{\"type\":\"2\",\"uniqNo\":\"5983421\"}";
        String[] value = {appkey,sign,param,"json"};
        Object[] objects = WsUtils.invoke2("http://10.0.75.151:9000/employeeService?wsdl", "http://employee.fws.fesco.com/", "getEsOfferInfoByUniqNo", value, new Class[]{String.class});
        System.out.println(objects[0].toString());
        String internalNerWorkUrl = ApiUtilOpenPlateform.OLD_INTERFACE_EMPLOYEE_INNER_NETWORK_URL+ ApiUtilOpenPlateform.OLD_INTERFACE_EMPLOYEE_PATH;
        String a = ApiUtilOpenPlateform.callOldInterfaceByInnerNetWork(internalNerWorkUrl, ApiUtilOpenPlateform.OLD_INTERFACE_EMPLOYEE_PATH,ApiUtilOpenPlateform.OLD_INTERFACE_EMPLOYEE_NAMESPACE, "getEsOfferInfoByUniqNo", param);
        System.out.println("a=====>" + a);
    }

    /*获取雇员派出统计信息：getEmpSendStatistics*/
    /*public static void main(String[] args) throws Exception {
		*//*appkey是自己的
		 *sign签名不加时间戳
		 *param是json格式的入参例如{"uniqNo":"110"}
		 *最后一个参数是固定就是json
		 *//*
        String appkey = "WeiXinSystem";
        String sign = "8a93ec93fa219a48d1ec1c6f311939f8";
        String param = "{\"type\":\"2\",\"uniqNo\":\"5983421\"}";
        String[] value = {appkey,sign,param,"json"};
        Object[] objects = WsUtils.invoke2("http://10.0.75.151:9000/employeeService?wsdl", "http://employee.fws.fesco.com/", "getEmpSendStatistics", value, new Class[]{String.class});
        System.out.println(objects[0].toString());
    }*/

    /**  getEmpBasicInfo
     * 获取雇员基本信息
     * @author shi.lei
     * @param dto
     * @return
     * @throws Exception
     */
   /* public static void main(String[] args) throws Exception {
        *//*appkey是自己的
                *sign签名不加时间戳
                *param是json格式的入参例如{"uniqNo":"110"}
		 *最后一个参数是固定就是json*//*
        String appkey = "WeiXinSystem";
        String sign = "8a93ec93fa219a48d1ec1c6f311939f8";
        String param = "{\"type\":\"2\",\"uniqNo\":\"5983421\"}";
        String[] value = {appkey,sign,param,"json"};
        Object[] objects = WsUtils.invoke2("http://10.0.75.151:9000/employeeService?wsdl", "http://employee.fws.fesco.com/", "getEmpBasicInfo", value, new Class[]{String.class});
        System.out.println(objects[0].toString());
    }*/

    /**
     * @author cao.guo.dong
     * @create 2018/1/4 13:24
     * @desc   判断接口是否停服
     * @param
     * @return
     **/
   /* public static void main(String[] args) throws Exception {
        String url = "http://10.0.75.151:8858/middlemgr/ifStopServer";
        Map<String, String> map = new HashMap<>();
        map.put("apiService","EMP");
        String jsonList = JSON.toJSONString(map);
        String result = HttpClientUtil.httpPost(url,jsonList);
        System.out.println(result);
    }*/

    /**
     * @author cao.guo.dong
     * @create 2018/1/4 13:24
     * @desc   hr接口
     * @param
     * @return
     **/
    /*public static void main(String[] args) throws Exception {
        String url = "http://10.0.75.151:8847/hrmgr/selectNsEsPasspayByCustomIdAndNoLevel";
        List<CustomerDto> dtos = new ArrayList<CustomerDto>();
        dtos.add(new CustomerDto(243691,2));
        dtos.add(new CustomerDto(185036,4));
        dtos.add(new CustomerDto(183614,4));
        PostMethod filePost = new PostMethod(url);
        filePost.setRequestEntity(new StringRequestEntity(JSON.toJSONString(dtos),"application/json","utf-8"));
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        int status = client.executeMethod(filePost);
        String rsp = filePost.getResponseBodyAsString();
        System.out.println(rsp);
    }*/
}
