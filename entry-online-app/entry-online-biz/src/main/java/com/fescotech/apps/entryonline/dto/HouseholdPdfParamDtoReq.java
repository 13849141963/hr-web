package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/5.
 */
public class HouseholdPdfParamDtoReq {



    private PdfFileParamDtoReq firstHuKouPicId;
    private PdfFileParamDtoReq selfHuKouPicId;
    private PdfFileParamDtoReq huKouChangePicIds;
    private String empName;
    private String idCode;

    public PdfFileParamDtoReq getFirstHuKouPicId() {
        return firstHuKouPicId;
    }

    public void setFirstHuKouPicId(PdfFileParamDtoReq firstHuKouPicId) {
        this.firstHuKouPicId = firstHuKouPicId;
    }

    public PdfFileParamDtoReq getSelfHuKouPicId() {
        return selfHuKouPicId;
    }

    public void setSelfHuKouPicId(PdfFileParamDtoReq selfHuKouPicId) {
        this.selfHuKouPicId = selfHuKouPicId;
    }

    public PdfFileParamDtoReq getHuKouChangePicIds() {
        return huKouChangePicIds;
    }

    public void setHuKouChangePicIds(PdfFileParamDtoReq huKouChangePicIds) {
        this.huKouChangePicIds = huKouChangePicIds;
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
}
