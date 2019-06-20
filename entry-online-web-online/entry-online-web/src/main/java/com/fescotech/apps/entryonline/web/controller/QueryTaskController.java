package com.fescotech.apps.entryonline.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.dto.EntryTaskDtoReq;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.QueryTaskVo;
import com.fescotech.apps.entryonline.util.PageUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
@Controller
public class QueryTaskController {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/entryOnline/queryEntryTask")
	@ResponseBody
	public R taskCreate (Integer page, Integer rows) throws Exception {
		String username = (String)ShiroUtils.getSessionAttribute("username");
		EntryTaskDtoReq entryTask = new EntryTaskDtoReq();
		entryTask.setCreator(username);
		entryTask.setPageNo(page);
		entryTask.setPageSize(rows);

        String method = "olentry.queryEntryTask";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method,JSON.toJSONString(entryTask));
        Result result = JSON.parseObject(resultStr, Result.class);
        
        Object successResult = result.getSuccessResult();

        JSONObject successResultObject = JSONObject.parseObject(successResult.toString());
		Object recordListObj = successResultObject.get("pageRecords");
		int totalCount = (int)successResultObject.get("totalCount");
		
		List<QueryTaskVo> taskRecords = null;
		if (recordListObj != null) {
			taskRecords = JSONObject.parseArray(recordListObj.toString(),QueryTaskVo.class);
		}
        
        //获取审核列表记录数
        PageUtils pageUtil = new PageUtils(taskRecords, totalCount, rows, page);
        return R.ok().put("page", pageUtil);
        
        
	}
}
