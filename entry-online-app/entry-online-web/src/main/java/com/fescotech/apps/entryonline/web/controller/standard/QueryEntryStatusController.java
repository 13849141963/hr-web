package com.fescotech.apps.entryonline.web.controller.standard;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.WebUserVo;
import com.fescotech.apps.entryonline.entity.vo.standard.QueryEntryVo;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.service.IEntryStatusService;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;

@Controller
public class QueryEntryStatusController {

	@Autowired
	private IEntryStatusService entryService;
	
	/**
	 * 查询员工状态字典
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	@ResponseBody
	@RequestMapping(value = "/standard/getEmpStatusDict")
	public Result getEmpStatusDict()throws Exception{
		return entryService.getEmpStatusDict();
	}
	
	/**
	 * 查询手续列表字典
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	@ResponseBody
	@RequestMapping(value = "/standard/getProceduresListDict")
	public Result getProceduresListDict()throws Exception{
		return entryService.getProceduresListDict();
	}
	/**
	 * 查询手续状态字典
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	@ResponseBody
	@RequestMapping(value = "/standard/getProceduresStatusDict")
	public Result getProceduresStatusDict()throws Exception{
		return entryService.getProceduresStatusDict();
	}
	/**
	 * 查询入职状况
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/standard/queryEntryStatus")
	public R queryEntryStatus(QueryEntryVo vo)throws Exception{
		//vo.setBizType(EntryContants.ENTRY_STANDARD_TYPE);
		R queryEntryStatus = entryService.queryEntryStatus(vo);
		return queryEntryStatus;
	}
	/**
	 * 导出数据
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	@ResponseBody
	@RequestMapping(value = "/standard/export")
	public void exportData(HttpServletResponse response,@RequestBody QueryEntryVo vo)throws Exception{
		/*public void exportData(HttpServletResponse response)throws Exception{*/
		//vo.setBizType(EntryContants.ENTRY_STANDARD_TYPE);
		Result result =entryService.queryEntryStatusData(vo);
		entryService.exportData(response,result);
		
	}
	
	/**
	 * 获得用户名称
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	@ResponseBody
	@RequestMapping(value = "/standard/getUser")
	public Result getUserName()throws Exception{
		WebUserVo webUserVo = (WebUserVo) ShiroUtils.getSessionAttribute("userVo");
		return new Result(Result.SUCCESS,"",webUserVo);
	}
	
	
}
