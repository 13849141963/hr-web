package com.fescotech.apps.entryonline.entity.vo;

/**
 * Created by cy on 2018/6/8.
 */
public class PolicyqueryReq {
    private String areaName;
    private String cateId;
    private String dispTime;
    private String stanEffectTime;
    private String title;
    private Integer pageIndex;
    private Integer pageSize;

    public PolicyqueryReq() {
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getDispTime() {
        return dispTime;
    }

    public void setDispTime(String dispTime) {
        this.dispTime = dispTime;
    }

    public String getStanEffectTime() {
        return stanEffectTime;
    }

    public void setStanEffectTime(String stanEffectTime) {
        this.stanEffectTime = stanEffectTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
