package com.fescotech.apps.entryonline.web.service;

import javax.servlet.http.HttpServletResponse;

import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.standard.QueryEntryVo;
import com.fescotech.apps.entryonline.util.Result;

public interface IEntryStatusService {

	/**
	 * 查询员工状态字典
	 * @return
	   @author guoliming
	 */
	Result getEmpStatusDict() throws Exception;

	/**
	 * 查询手续列表字典
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	Result getProceduresListDict()throws Exception;

	/**
	 * 查询手续状态字典
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	Result getProceduresStatusDict()throws Exception;

	/**
	 * 查询入职任务状态
	 * @param vo
	 * @return
	   @author guoliming
	 */
	R queryEntryStatus(QueryEntryVo vo)throws Exception;
	
	Result queryEntryStatusData(QueryEntryVo vo)throws Exception;

	void exportData(HttpServletResponse response, Result result);

	

}
