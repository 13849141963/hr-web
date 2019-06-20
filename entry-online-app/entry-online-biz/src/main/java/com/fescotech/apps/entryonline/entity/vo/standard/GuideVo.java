package com.fescotech.apps.entryonline.entity.vo.standard;

import java.io.Serializable;

public class GuideVo implements Serializable{

	private Integer guideId;
	
	private String content;
	
	private Integer taskGuideId; 
	
	
	

	public Integer getTaskGuideId() {
		return taskGuideId;
	}

	public void setTaskGuideId(Integer taskGuideId) {
		this.taskGuideId = taskGuideId;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
