package com.fescotech.apps.entryonline.entity;

import com.fescotech.apps.entryonline.dto.ImgBatchLoadCommonDtoResp;

import java.util.List;

/**
 * Created by cy on 2018/3/26.
 */
public class CommonImgInfo {
    //雇员入职标识
    private Integer empTaskId;
    //员工姓名
    private String empName;
    //身份证号
    private String idCode;
    //手机号
    private String mobile;
    //材料类型
    private Integer meterialType;
    //材料id
    private String fileId;
    //图片名
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public Integer getMeterialType() {
        return meterialType;
    }

    public void setMeterialType(Integer meterialType) {
        this.meterialType = meterialType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
