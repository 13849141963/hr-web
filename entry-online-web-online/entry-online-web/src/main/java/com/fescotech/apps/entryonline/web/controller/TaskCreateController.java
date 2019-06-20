package com.fescotech.apps.entryonline.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.entity.BusiCust;
import com.fescotech.apps.entryonline.entity.EntryTask;
import com.fescotech.apps.entryonline.entity.WebUserVo;
import com.fescotech.apps.entryonline.entity.vo.TaskCreateVo;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@Controller
public class TaskCreateController {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/entryOnline/addEntryTask")
	@ResponseBody
	//@RequestBody EntryTask entryTask   String[] checkedCustomerIds
	public Result taskCreate(TaskCreateVo taskCreateVo){
		Result result = new Result<>();
		try {
			String taskName = taskCreateVo.getTaskName();
			//String[] checkedCustomerIds = taskCreateVo.getCheckedCustomerIds();
			List<String> checkedCustomerIds= taskCreateVo.getCheckedCustomerIds();
			String[] processList = taskCreateVo.getProcessList();
			String procedures = StringUtils.join(processList,",");
			String[] guideList = taskCreateVo.getGuideList();
			String guides = StringUtils.join(guideList,",");
			
			List<BusiCust> busiCusts = new ArrayList<>();
			String busiCustNo = null;
			for (String checkedCustomer : checkedCustomerIds) {
				String[] checkedCustomerInfo = checkedCustomer.split("&");
				busiCustNo = checkedCustomerInfo[0];
				String busiCustName = checkedCustomerInfo[1];
				BusiCust busiCust = new BusiCust();
				busiCust.setBusiCustNo(busiCustNo);
				busiCust.setBusiCustName(busiCustName);
				busiCusts.add(busiCust);
			}
			//根据业务客户id查询业务员信息
			  String method = "csmgr.getUserByBusiCust";
	    	  Map<String, Object> map = new HashMap<>();
	          map.put("busiCustId",Long.valueOf(busiCustNo));
	          String res = ApiUtilOpenPlateform.callOpenPlateformApiHr(method,JSON.toJSONString(map));
	          Result parseObject = JSONObject.parseObject(res, Result.class);
	          JSONObject successResultObject = JSONObject.parseObject(parseObject.getSuccessResult().toString());
	          //业务代表业务号
	  		  Object userNumber = successResultObject.get("userId");
	  		
			EntryTask entryTask = new EntryTask();
			entryTask.setTaskName(taskName);
			entryTask.setBusiCusts(busiCusts);
			//entryTask.setBusiCustNos(checkedCustomerIds);
			entryTask.setProcedures(procedures);
			entryTask.setGuides(guides);
			
			//String username = (String)ShiroUtils.getSessionAttribute("username");
			WebUserVo webUserVo = (WebUserVo)ShiroUtils.getSessionAttribute("userVo");
			//创建人Id  hr账号
			entryTask.setCreator(webUserVo.getLoginName());
			//创建人名称  fullName(hr登录接口返回fullName)
			entryTask.setCreatorName(webUserVo.getFullName());
			//业务代表id  需要修改
			entryTask.setBusiRep(Long.valueOf(String.valueOf(userNumber)));
			//entryTask.setBusiRep(userNumber.toString());
			
			String jsonStr = JSON.toJSONString(entryTask);
			String methodEntryTask = "olentry.createEntryTask";
			String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
			result = JSON.parseObject(resultStr, Result.class);
			
		} catch (Exception e) {
			System.out.println("----------------"+e.getMessage());
			result.setCode("1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

}
