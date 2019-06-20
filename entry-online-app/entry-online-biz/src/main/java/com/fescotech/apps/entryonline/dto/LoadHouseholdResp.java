package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/2/28.
 */
public class LoadHouseholdResp {
    private Integer empTaskId;
    private String empName;
    private String idCode;
    private String mobile;
    private Integer householdCate;
    private Integer householdCity;
    private String firstHuKouPicId;
    private String selfHuKouPicId;
    private String huKouChangePicId;

    public Integer getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Integer empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getHouseholdCate() {
        return householdCate;
    }

    public void setHouseholdCate(Integer householdCate) {
        this.householdCate = householdCate;
    }

    public Integer getHouseholdCity() {
        return householdCity;
    }

    public void setHouseholdCity(Integer householdCity) {
        this.householdCity = householdCity;
    }

    public String getFirstHuKouPicId() {
        return firstHuKouPicId;
    }

    public void setFirstHuKouPicId(String firstHuKouPicId) {
        this.firstHuKouPicId = firstHuKouPicId;
    }

    public String getSelfHuKouPicId() {
        return selfHuKouPicId;
    }

    public void setSelfHuKouPicId(String selfHuKouPicId) {
        this.selfHuKouPicId = selfHuKouPicId;
    }

    public String getHuKouChangePicId() {
        return huKouChangePicId;
    }

    public void setHuKouChangePicId(String huKouChangePicId) {
        this.huKouChangePicId = huKouChangePicId;
    }
}
