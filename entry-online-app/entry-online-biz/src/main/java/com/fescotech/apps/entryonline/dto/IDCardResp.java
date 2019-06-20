package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/2/28.
 */
public class IDCardResp {
    private String idCardFrontImgId;
    private String idCardBackImgId;
    private String idCardComImgId;
    private String empName;

    public String getIdCardFrontImgId() {
        return idCardFrontImgId;
    }

    public void setIdCardFrontImgId(String idCardFrontImgId) {
        this.idCardFrontImgId = idCardFrontImgId;
    }

    public String getIdCardBackImgId() {
        return idCardBackImgId;
    }

    public void setIdCardBackImgId(String idCardBackImgId) {
        this.idCardBackImgId = idCardBackImgId;
    }

    public String getIdCardComImgId() {
        return idCardComImgId;
    }

    public void setIdCardComImgId(String idCardComImgId) {
        this.idCardComImgId = idCardComImgId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
