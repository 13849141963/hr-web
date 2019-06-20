package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.Date;

/**
 * 
 */
public class OeGuide {

	/**
	 * 入职指南ID
	 */
	private Long guideId; 
	
	/**
	 * 入职指南名称
	 */
	private String guideName; 
	
	/**
	 * 入职指南内容
	 */
	private String content; 
	
	/**
	 * 创建人ID
	 */
	private Integer creator; 
	
	/**
	 * CREATOR_NAME
	 */
	private String creatorName; 
	
	/**
	 * 创建时间
	 */
	private Date createTime; 
	
	private Integer busiType;
	
	
	
	
	
	public Integer getBusiType() {
		return busiType;
	}

	public void setBusiType(Integer busiType) {
		this.busiType = busiType;
	}

	/**
	 * 读取入职指南ID
	 */
	public Long getGuideId(){
		return guideId;
	} 
	
	/**
	 * 设置 入职指南ID
	 */
	public void setGuideId(Long guideId){
		this.guideId = guideId;
	}
	
	/**
	 * 读取入职指南名称
	 */
	public String getGuideName(){
		return guideName;
	} 
	
	/**
	 * 设置 入职指南名称
	 */
	public void setGuideName(String guideName){
		this.guideName = guideName;
	}
	
	/**
	 * 读取入职指南内容
	 */
	public String getContent(){
		return content;
	} 
	
	/**
	 * 设置 入职指南内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	/**
	 * 读取创建人ID
	 */
	public Integer getCreator(){
		return creator;
	} 
	
	/**
	 * 设置 创建人ID
	 */
	public void setCreator(Integer creator){
		this.creator = creator;
	}
	
	/**
	 * 读取CREATOR_NAME
	 */
	public String getCreatorName(){
		return creatorName;
	} 
	
	/**
	 * 设置 CREATOR_NAME
	 */
	public void setCreatorName(String creatorName){
		this.creatorName = creatorName;
	}
	
	/**
	 * 读取创建时间
	 */
	public Date getCreateTime(){
		return createTime;
	} 
	
	/**
	 * 设置 创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	
}
