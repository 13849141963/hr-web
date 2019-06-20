package com.fescotech.apps.entryonline.web.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.entity.vo.QueryTaskVo;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.apps.entryonline.web.util.ZxingCodeUtil;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@Controller
public class GenerateCode {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/entryOnline/getPcEntryLink")
	@ResponseBody
	//@RequestBody EntryTask entryTask   String[] checkedCustomerIds
	public String createZxingCode(String taskName) throws Exception {
			Map<String, Object> map = new HashMap<>();
		 	String method = "olentry.queryEntryTask";
		 	String username = (String)ShiroUtils.getSessionAttribute("username");
		 	map.put("creator", username);
		 	map.put("taskName", taskName);
			String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method,JSON.toJSONString(map));
	        Result result = JSON.parseObject(resultStr, Result.class);
	        
	        Object successResult = result.getSuccessResult();

	        JSONObject successResultObject = JSONObject.parseObject(successResult.toString());
			Object recordListObj = successResultObject.get("pageRecords");
			
			List<QueryTaskVo> taskRecords = null;
			if (recordListObj != null) {
				taskRecords = JSONObject.parseArray(recordListObj.toString(),QueryTaskVo.class);
			}
			String pcEntryLink = "www.sina.com.cn";
			for (QueryTaskVo queryTaskVo : taskRecords) {
				if(queryTaskVo.getPcEntryLink()!=null && queryTaskVo.getPcEntryLink()!="") {
					pcEntryLink = queryTaskVo.getPcEntryLink();
				}
			}
			
			return pcEntryLink;
				
	}
	
	@RequestMapping(value = "/entryOnline/erweiCode")
	@ResponseBody
	public void erweiCode (HttpServletResponse response,@RequestParam(value="pcEntryLink",required=false,defaultValue="https://www.baidu.com") String pcEntryLink) throws IOException{
		
		ZxingCodeUtil.zxingCodeCreate(response,pcEntryLink, 300, 300, "jpg");
	}

}
