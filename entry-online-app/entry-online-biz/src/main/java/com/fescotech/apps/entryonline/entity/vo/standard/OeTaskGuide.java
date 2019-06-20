package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.Date;

/**
 * 
 */
public class OeTaskGuide {

	/**
	 * 入职任务指南ID
	 */
	private Long taskGuideId; 
	
	/**
	 * 系统id
	 */
	private String taskId; 
	
	/**
	 * 入职指南ID
	 */
	private Integer guideId; 
	
	/**
	 * 入职指南名称
	 */
	private String name; 
	
	
	
	/**
	 * 入职指南内容
	 */
	private String content; 
	
	/**
	 * 创建时间
	 */
	private Date createTime; 
	
	
	/**
	 * 读取入职任务指南ID
	 */
	public Long getTaskGuideId(){
		return taskGuideId;
	} 
	
	/**
	 * 设置 入职任务指南ID
	 */
	public void setTaskGuideId(Long taskGuideId){
		this.taskGuideId = taskGuideId;
	}
	
	/**
	 * 读取系统id
	 */
	public String getTaskId(){
		return taskId;
	} 
	
	/**
	 * 设置 系统id
	 */
	public void setTaskId(String taskId){
		this.taskId = taskId;
	}
	
	/**
	 * 读取入职指南ID
	 */
	public Integer getGuideId(){
		return guideId;
	} 
	
	/**
	 * 设置 入职指南ID
	 */
	public void setGuideId(Integer guideId){
		this.guideId = guideId;
	}
	
	/**
	 * 读取入职指南名称
	 */
	public String getName(){
		return name;
	} 
	
	/**
	 * 设置 入职指南名称
	 */
	public void setName(String name){
		this.name = name;
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
