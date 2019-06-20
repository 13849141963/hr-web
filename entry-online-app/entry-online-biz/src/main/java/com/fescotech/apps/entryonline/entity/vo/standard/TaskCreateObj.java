package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.List;



public class TaskCreateObj {

	private String taskName;
	// 业务客户编号
	private List<String> checkedCustomerIds;

	// private List<BusiCust> checkedCustomerIds;

	private String[] processList;
	private String[] guideList;
	
	
	private List<OeTaskGuide> guideObjList;
	
	

	

	

	



	public List<OeTaskGuide> getGuideObjList() {
		return guideObjList;
	}



	public void setGuideObjList(List<OeTaskGuide> guideObjList) {
		this.guideObjList = guideObjList;
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
