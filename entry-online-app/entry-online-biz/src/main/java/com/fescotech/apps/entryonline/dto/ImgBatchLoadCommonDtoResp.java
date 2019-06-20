package com.fescotech.apps.entryonline.dto;

import java.util.List;

/**
 * Created by cy on 2018/3/26.
 */
public class ImgBatchLoadCommonDtoResp {
    //雇员入职标识
    private Integer empTaskId;
    //员工姓名
    private String empName;
    //身份证号
    private String idCode;
    //手机号
    private String mobile;
    //材料集合
    public List<Material> materials;

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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

}
