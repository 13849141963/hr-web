package com.fescotech.apps.entryonline.entity.vo;

import java.util.List;

/**
 * Created by cy on 2018/6/8.
 */
public class EmployeequeryReq {
    private Long uniqNo;
    private String empName;
    private String idCardNo;
    private List<String> custIds;
    private List<String> busiCustIds;
    private Integer pageIndex;
    private Integer pageSize;

    public EmployeequeryReq() {
    }

    public Long getUniqNo() {
        return uniqNo;
    }

    public void setUniqNo(Long uniqNo) {
        this.uniqNo = uniqNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
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
