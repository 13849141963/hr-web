package com.fescotech.apps.entryonline.web.controller.standard;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.TaskCreateVo;
import com.fescotech.apps.entryonline.entity.vo.standard.GuideVo;
import com.fescotech.apps.entryonline.entity.vo.standard.TaskCreateObj;
import com.fescotech.apps.entryonline.entity.vo.standard.TaskVo;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.util.Standard.EntryContants;
import com.fescotech.apps.entryonline.web.service.ITaskService;

@Controller
public class CreateTaskController {

	@Autowired
	private ITaskService taskService;

	/**
	 * 获取业务客户列表
	 * 
	 * @return
	 * @throws Exception
	 * @author guoliming
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/customerCompany")
	@ResponseBody
	public Result customerCompany() throws Exception {

		return taskService.customerCompany();
	}

	/**
	 * 获取入职手续
	 * 
	 * @return
	 * @throws Exception
	 * @author guoliming
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/entryProcedures")
	@ResponseBody
	public Result getProcedures() throws Exception {

		return taskService.getProcedures(EntryContants.ENTRY_STANDARD_TYPE);
	}

	/**
	 * 获取入职指南
	 * 
	 * @return
	 * @throws Exception
	 * @author guoliming
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/entryGuides")
	@ResponseBody
	public Result listEntryGuides() throws Exception {

		return taskService.listEntryGuides(EntryContants.ENTRY_STANDARD_TYPE);
	}

	/**
	 * 创建任务
	 * 
	 * @param taskCreateVo
	 * @return
	 * @author guoliming
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/addEntryTask")
	@ResponseBody
	public Result taskCreate(@RequestBody TaskCreateObj taskCreateObj) {
		Result result = taskService.createTask(taskCreateObj);
		return result;
	}
	/**
	 * 修改指南
	 * 
	 * @param taskCreateVo
	 * @return
	 * @author guoliming
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/modifyGuide")
	@ResponseBody
	public Result modifyGuide(@RequestBody GuideVo guideVo) throws Exception {
		Result result = taskService.modifyGuide(guideVo);
		return result;
	}
	
	
	/**
	 * 查询任务列表
	 * @return
	 * @author guoliming
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/queryEntryTask")
	@ResponseBody
	public R queryEntryTask(Integer page,Integer rows) throws Exception {
		R result = taskService.queryEntryTask(page,rows);
		return result;
	}
	
	/**
	 * 生成二维码
	 * @return
	 * @author guoliming
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/generateCode")
	public void queryEntryTask(HttpServletResponse response,@RequestBody TaskVo taskVo) throws Exception {
		taskService.generateCode(response,taskVo.getPcEntryLink());
		
	}
	
	/**
	 * 关闭任务
	 * @return
	 * @author guoliming
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/closeTask")
	@ResponseBody
	public Result closeTask(@RequestBody TaskVo taskVo) throws Exception {
		return taskService.closeTask(taskVo.getTaskId());
		
	}
	
	/**
	 * 开启任务
	 * @return
	 * @author guoliming
	 * @throws Exception 
	 */
	
	@RequestMapping(value = "/standard/openTask")
	@ResponseBody
	public Result openTask(@RequestBody TaskVo taskVo) throws Exception {
		return taskService.openTask(taskVo.getTaskId());
		
	}
	
	/**
	 * 删除任务
	 * 传数组 可接收
	 * @author guoliming
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/deleteTask")
	@ResponseBody
	public Result deleteTask(@RequestBody TaskVo taskVo) throws Exception {
		return taskService.deleteTasks(taskVo.getTaskIds());
		
	}
	
	/**
	 * 修改任务指南
	 * 
	 * @param 
	 * @return
	 * @author guoliming
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/standard/modifyTaskGuide")
	@ResponseBody
	public Result modifyTaskGuide(@RequestBody GuideVo guideVo) throws Exception {
		Result result = taskService.modifyTaskGuide(guideVo.getTaskGuideId(),guideVo.getContent());
		return result;
	}
	
	
	

}
