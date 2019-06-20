package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.List;

import com.fescotech.apps.entryonline.entity.BusiCust;

public class MyQueryTaskVo {
	private String appQcodeLink;
	private String creator;
	private String creatorName;
	private String delFlag;
	private String guides;
	private String otherGuideContent;
	private String otherGuideName;
	private String pcEntryLink;
	private String procedures;
	private String taskId;
	private String taskName;
	private Integer taskStatus;
	private String taskType;
	private String wxQcodeLink;
	private Integer registerNum;
	private Integer importNum;
	private List<BusiCust> busiCusts;
	
	private List<OeTaskGuide> guidesList;
	
	
/*
	public MyQueryTaskVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyQueryTaskVo(String appQcodeLink, String creator, String creatorName,
			String delFlag, String guides, String otherGuideContent,
			String otherGuideName, String pcEntryLink, String procedures,
			String taskId, String taskName, String taskStatus, String taskType,
			String wxQcodeLink) {
		super();
		this.appQcodeLink = appQcodeLink;
		this.creator = creator;
		this.creatorName = creatorName;
		this.delFlag = delFlag;
		this.guides = guides;
		this.otherGuideContent = otherGuideContent;
		this.otherGuideName = otherGuideName;
		this.pcEntryLink = pcEntryLink;
		this.procedures = procedures;
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.taskType = taskType;
		this.wxQcodeLink = wxQcodeLink;
	}*/

	
	
	
	
	public List<BusiCust> getBusiCusts() {
		return busiCusts;
	}

	public List<OeTaskGuide> getGuidesList() {
		return guidesList;
	}

	public void setGuidesList(List<OeTaskGuide> guidesList) {
		this.guidesList = guidesList;
	}

	public void setBusiCusts(List<BusiCust> busiCusts) {
		this.busiCusts = busiCusts;
	}

	public Integer getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(Integer registerNum) {
		this.registerNum = registerNum;
	}

	public Integer getImportNum() {
		return importNum;
	}

	public void setImportNum(Integer importNum) {
		this.importNum = importNum;
	}

	public String getAppQcodeLink() {
		return appQcodeLink;
	}

	public void setAppQcodeLink(String appQcodeLink) {
		this.appQcodeLink = appQcodeLink;
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getGuides() {
		return guides;
	}

	public void setGuides(String guides) {
		this.guides = guides;
	}

	public String getOtherGuideContent() {
		return otherGuideContent;
	}

	public void setOtherGuideContent(String otherGuideContent) {
		this.otherGuideContent = otherGuideContent;
	}

	public String getOtherGuideName() {
		return otherGuideName;
	}

	public void setOtherGuideName(String otherGuideName) {
		this.otherGuideName = otherGuideName;
	}

	public String getPcEntryLink() {
		return pcEntryLink;
	}

	public void setPcEntryLink(String pcEntryLink) {
		this.pcEntryLink = pcEntryLink;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
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



	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getWxQcodeLink() {
		return wxQcodeLink;
	}

	public void setWxQcodeLink(String wxQcodeLink) {
		this.wxQcodeLink = wxQcodeLink;
	}

}
