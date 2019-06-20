package com.fescotech.apps.entryonline.entity.vo;

import java.util.List;

/**
 * Created by cy on 2018/6/8.
 */
public class EmpentryhandleReq {
    private List<String> custIds;
    private List<String> busiCustIds;
    private List<String> custConIds;
    private List<String> conAcceIds;
    private Integer pageIndex;
    private Integer pageSize;

    public EmpentryhandleReq() {
    }

    public List<String> getCustIds() {
        return custIds;
    }

    public void setCustIds(List<String> custIds) {
        this.custIds = custIds;
    }

    public List<String> getBusiCustIds() {
        return busiCustIds;
    }

    public void setBusiCustIds(List<String> busiCustIds) {
        this.busiCustIds = busiCustIds;
    }

    public List<String> getCustConIds() {
        return custConIds;
    }

    public void setCustConIds(List<String> custConIds) {
        this.custConIds = custConIds;
    }

    public List<String> getConAcceIds() {
        return conAcceIds;
    }

    public void setConAcceIds(List<String> conAcceIds) {
        this.conAcceIds = conAcceIds;
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
