package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.List;

public class TaskVo {
private String taskId;

private List<String> taskIds;

private String pcEntryLink;




public String getPcEntryLink() {
	return pcEntryLink;
}

public void setPcEntryLink(String pcEntryLink) {
	this.pcEntryLink = pcEntryLink;
}

public List<String> getTaskIds() {
	return taskIds;
}

public void setTaskIds(List<String> taskIds) {
	this.taskIds = taskIds;
}

public String getTaskId() {
	return taskId;
}

public void setTaskId(String taskId) {
	this.taskId = taskId;
}


}
