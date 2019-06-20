package com.fescotech.apps.entryonline.entity.vo;

import java.util.List;



public class TaskCreateVo {

	private String taskName;
	// 业务客户编号
	private List<String> checkedCustomerIds;

	// private List<BusiCust> checkedCustomerIds;

	private String[] processList;
	private String[] guideList;

	public TaskCreateVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public List<String> getCheckedCustomerIds() {
		return checkedCustomerIds;
	}



	public void setCheckedCustomerIds(List<String> checkedCustomerIds) {
		this.checkedCustomerIds = checkedCustomerIds;
	}



	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String[] getProcessList() {
		return processList;
	}

	public void setProcessList(String[] processList) {
		this.processList = processList;
	}

	public String[] getGuideList() {
		return guideList;
	}

	public void setGuideList(String[] guideList) {
		this.guideList = guideList;
	}

}
