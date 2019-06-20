package com.fescotech.apps.entryonline.entity;

import java.util.List;

public class EntryTask {
	// 入职任务编号
	private String taskId;
	// 入职任务名称
	private String taskName;
	private String[] busiCustNos;

	private List<BusiCust> busiCusts;
	// 数组中是相关手续的id
	private String procedures;
	// 数组中是相关指南的id
	private String guides;
	// 体检套餐
	private String checkupService;
	// 其它入职告知名称
	private String otherGuideName;
	// 其他入职告知内容
	private String otherGuideContent;
	// 创建人Id
	private String creator;
	// 创建人名称
	private String creatorName;
	// 业务代表id
	private Long busiRep;

	public Long getBusiRep() {
		return busiRep;
	}

	public void setBusiRep(Long busiRep) {
		this.busiRep = busiRep;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String[] getBusiCustNos() {
		return busiCustNos;
	}

	public void setBusiCustNos(String[] busiCustNos) {
		this.busiCustNos = busiCustNos;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	public String getGuides() {
		return guides;
	}

	public void setGuides(String guides) {
		this.guides = guides;
	}

	public String getCheckupService() {
		return checkupService;
	}

	public void setCheckupService(String checkupService) {
		this.checkupService = checkupService;
	}

	public String getOtherGuideName() {
		return otherGuideName;
	}

	public void setOtherGuideName(String otherGuideName) {
		this.otherGuideName = otherGuideName;
	}

	public String getOtherGuideContent() {
		return otherGuideContent;
	}

	public void setOtherGuideContent(String otherGuideContent) {
		this.otherGuideContent = otherGuideContent;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<BusiCust> getBusiCusts() {
		return busiCusts;
	}

	public void setBusiCusts(List<BusiCust> busiCusts) {
		this.busiCusts = busiCusts;
	}

}
