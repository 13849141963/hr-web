package com.fescotech.apps.entryonline.web.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.dto.EntryTaskDtoReq;
import com.fescotech.apps.entryonline.entity.BusiCust;
import com.fescotech.apps.entryonline.entity.EntryTask;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.WebUserVo;
import com.fescotech.apps.entryonline.entity.vo.CustomerVo;
import com.fescotech.apps.entryonline.entity.vo.QueryTaskVo;
import com.fescotech.apps.entryonline.entity.vo.TaskCreateVo;
import com.fescotech.apps.entryonline.entity.vo.standard.GuideVo;
import com.fescotech.apps.entryonline.entity.vo.standard.MyQueryTaskVo;
import com.fescotech.apps.entryonline.entity.vo.standard.OeGuide;
import com.fescotech.apps.entryonline.entity.vo.standard.OeTaskGuide;
import com.fescotech.apps.entryonline.entity.vo.standard.TaskCreateObj;
import com.fescotech.apps.entryonline.util.PageUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.util.Standard.EntryContants;
import com.fescotech.apps.entryonline.web.service.ITaskService;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.apps.entryonline.web.util.ZxingCodeUtil;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@Service
public class TaskServiceImp implements ITaskService {

	@Override
	public Result createTask(TaskCreateObj taskCreateVo) {
		Result result = new Result<>();
		try {
			String taskName = taskCreateVo.getTaskName();
			// String[] checkedCustomerIds =
			// taskCreateVo.getCheckedCustomerIds();
			List<String> checkedCustomerIds = taskCreateVo.getCheckedCustomerIds();
			String[] processList = taskCreateVo.getProcessList();
			String procedures = StringUtils.join(processList, ",");
			String[] guideList = taskCreateVo.getGuideList();
			String guides = StringUtils.join(guideList, ",");

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
			// 根据业务客户id查询业务员信息
			String method = "csmgr.getUserByBusiCust";
			Map<String, Object> map = new HashMap<>();
			map.put("busiCustId", Long.valueOf(busiCustNo));
			String res = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, JSON.toJSONString(map));
			Result parseObject = JSONObject.parseObject(res, Result.class);
			JSONObject successResultObject = JSONObject.parseObject(parseObject.getSuccessResult().toString());
			// 业务代表业务号
			Object userNumber = successResultObject.get("userId");

			EntryTask entryTask = new EntryTask();
			entryTask.setTaskName(taskName);
			entryTask.setBusiCusts(busiCusts);
			// entryTask.setBusiCustNos(checkedCustomerIds);
			entryTask.setProcedures(procedures);
			entryTask.setGuides(guides);
			
			entryTask.setGuideObjList(switchGuideObjList(taskCreateVo.getGuideObjList()));

			// String username =
			// (String)ShiroUtils.getSessionAttribute("username");
			WebUserVo webUserVo = (WebUserVo) ShiroUtils.getSessionAttribute("userVo");
			// 创建人Id hr账号
			entryTask.setCreator(webUserVo.getLoginName());
			// 创建人名称 fullName(hr登录接口返回fullName)
			entryTask.setCreatorName(webUserVo.getFullName());
			// 业务代表id 需要修改
			entryTask.setBusiRep(Long.valueOf(String.valueOf(userNumber)));
			// entryTask.setBusiRep(userNumber.toString());
			entryTask.setBizType(EntryContants.ENTRY_STANDARD_TYPE);
			entryTask.setSourceType(EntryContants.SOURCE_HELO_PC);
			String jsonStr = JSON.toJSONString(entryTask);
			System.out.println("-------------"+jsonStr);
			String methodEntryTask = "olentry.createEntryTask";
			String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
			result = JSON.parseObject(resultStr, Result.class);

		} catch (Exception e) {
			System.out.println("----------------" + e.getMessage());
			result.setCode("1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 由于开放平台不能接收& 所以保存的时候将&转为@@ 获取的时候将@@转为&
	 * @param guideObjList
	 * @return
	   @author guoliming
	 */
	private List<OeTaskGuide> switchGuideObjList(List<OeTaskGuide> guideObjList) {
		for (OeTaskGuide oeTaskGuide : guideObjList) {
			oeTaskGuide.setContent(oeTaskGuide.getContent().replaceAll("&", "@@"));
			
		}
		return guideObjList;
	}

	@Override
	public Result getProcedures(Integer bizType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizType", bizType);
		String jsonString = JSON.toJSONString(map);

		String method = "olentry.listEntryProcedures";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, jsonString);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

	@Override
	public Result listEntryGuides(Integer bizType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizType", bizType);
		String jsonString = JSON.toJSONString(map);

		String method = "olentry.listEntryGuides";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, jsonString);
		Result result = JSON.parseObject(resultStr, Result.class);
		List<OeGuide> list = JSONObject.parseArray(result.getSuccessResult().toString(), OeGuide.class);
		for (OeGuide oeGuide : list) {
			oeGuide.setContent(oeGuide.getContent().replaceAll("@@", "&"));
		}
		result.setSuccessResult(JSONObject.toJSON(list));
		return result;
	}

	@Override
	public Result customerCompany() throws Exception {
		 Map<String, Object> map = new HashMap<>();
	        String username = (String) ShiroUtils.getSessionAttribute("username");
	        map.put("hrAccount", username);
	        String jsonStr = JSON.toJSONString(map);
	        //根据HR帐号获得所管辖客户列表，包含客户到合同附件层级数据
	        String method = "hrmgr.findCusInfoList";
	        String companyNameResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, jsonStr);
	        Result res = JSON.parseObject(companyNameResultStr, Result.class);
	        Object customerResult = res.getSuccessResult();
	        List<CustomerVo> customerListResult = JSON.parseArray(JSON.toJSONString(customerResult), CustomerVo.class);
	        ArrayList<CustomerVo> removeDuplicateCustomer = removeDuplicateCustomer(customerListResult);
	        res.setSuccessResult(removeDuplicateCustomer);
		return res;
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

	@Override
	public Result modifyGuide(GuideVo guideVo) throws Exception {
		guideVo.setContent(guideVo.getContent().replaceAll("&", "@@"));
		String jsonStr = JSON.toJSONString(guideVo);
		String methodEntryTask = "olentry.modifyGuidContnt";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);

		return new Result(Result.SUCCESS,"","修改成功");
	}

	@Override
	public R queryEntryTask(Integer page, Integer rows) throws Exception {
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
		
		List<MyQueryTaskVo> taskRecords = null;
		if (recordListObj != null) {
			taskRecords = JSONObject.parseArray(recordListObj.toString(),MyQueryTaskVo.class);
		}
		
		//由于开放平台不能接收& 所以保存的时候将&转为@@ 获取的时候将@@转为&
		for (MyQueryTaskVo item : taskRecords) {
			List<OeTaskGuide> list = item.getGuidesList();
			for (OeTaskGuide taskGuide : list) {
				taskGuide.setContent(taskGuide.getContent().replaceAll("@@", "&"));
			}
		}
        
        //获取审核列表记录数
        PageUtils pageUtil = new PageUtils(taskRecords, totalCount, rows, page);
        return R.ok().put("page", pageUtil);
	}

	@Override
	public void generateCode(HttpServletResponse response, String pcEntryLink) throws IOException {
		ZxingCodeUtil.zxingCodeCreate(response,pcEntryLink, 300, 300, "jpg");
		
	}

	@Override
	public Result closeTask(String taskId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", taskId);
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.closeTask";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);

		return result;
		
	}

	@Override
	public Result openTask(String taskId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", taskId);
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.openTask";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);

		return result;
		
	}

	@Override
	public Result deleteTasks(List<String> taskIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskIds", taskIds);
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.deleteEntryTask";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

	@Override
	public Result modifyTaskGuide(Integer taskGuideId, String content) throws Exception {
		String reStr = content.replaceAll("&", "@@");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskGuideId", taskGuideId);
		map.put("content", reStr);
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.modifyTaskGuidContent";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

}
