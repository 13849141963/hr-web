package com.fescotech.apps.entryonline.web.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.TaskCreateVo;
import com.fescotech.apps.entryonline.entity.vo.standard.GuideVo;
import com.fescotech.apps.entryonline.entity.vo.standard.TaskCreateObj;
import com.fescotech.apps.entryonline.util.Result;

public interface ITaskService {
    /**
     * 创建任务
     * @param taskCreateVo
     * @return
       @author guoliming
     */
	Result createTask( TaskCreateObj taskCreateVo);
	
	/**
	 * 获取手续
	 * @param bizType
	 * @return
	   @author guoliming
	 */
	Result getProcedures(Integer bizType)throws Exception;

	/**
	 * 获取指南列表
	 * @param bizType
	 * @return
	   @author guoliming
	 */
	Result listEntryGuides(Integer bizType)throws Exception;
	
	
	/**
	 * 获取业务客户编号
	 * @return
	   @author guoliming
	 */
	Result customerCompany()throws Exception;
	
	/**
	 * 修改指南内容
	 * @param guideVo
	 * @return
	   @author guoliming
	 */
	Result modifyGuide(GuideVo guideVo) throws Exception;

	/**
	 * 查询任务列表
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	R queryEntryTask(Integer page, Integer rows)throws Exception;

	/**
	 * 生成二维码
	 * @param response
	 * @param pcEntryLink
	   @author guoliming
	 */
	void generateCode(HttpServletResponse response, String pcEntryLink)throws Exception;

	/**
	 * 关闭任务
	 * @param taskId
	 * @throws Exception
	   @author guoliming
	 */
	Result  closeTask(String taskId)throws Exception;

	/**
	 * 开启任务
	 * @param taskId
	 * @throws Exception
	   @author guoliming
	 */
	Result openTask(String taskId)throws Exception;

	/**
	 * 删除任务
	 * @param taskIds
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	Result deleteTasks(List<String> taskIds) throws Exception;

	/**
	 * 修改任务指南
	 * @param taskGuideId
	 * @param content
	 * @return
	   @author guoliming
	 */
	Result modifyTaskGuide(Integer taskGuideId, String content)throws Exception;
	
	
}
