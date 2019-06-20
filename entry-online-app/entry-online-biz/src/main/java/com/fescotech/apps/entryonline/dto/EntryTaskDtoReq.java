package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/4.
 */
public class EntryTaskDtoReq {
    private String creator;
    private String taskName;
    private String[] busiCusNos;
    private Integer pageSize;
    private Integer pageNo;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String[] getBusiCusNos() {
        return busiCusNos;
    }

    public void setBusiCusNos(String[] busiCusNos) {
        this.busiCusNos = busiCusNos;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
