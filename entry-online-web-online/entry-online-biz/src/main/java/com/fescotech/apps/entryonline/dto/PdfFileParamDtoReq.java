package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/5.
 */
public class PdfFileParamDtoReq {
    /*材料类型   
    1：身份证正反面合成图； 
	2：户口本首页
	3：户口本本人页
	4：户口本本人页变更页*/
private Integer type;
//上传文件后缀类型  限定jpg、jpeg、png等多种格式 JPG：1  JPEG：2  PNG：3 
private Integer fileType;
//返回文件后缀类型   限定pdf格式 pdf:1
private Integer returnFileType;
//文件主键Id
private String fileId;

    public PdfFileParamDtoReq() {
    }

    public PdfFileParamDtoReq(Integer type, Integer fileType, Integer returnFileType, String fileId) {
        this.type = type;
        this.fileType = fileType;
        this.returnFileType = returnFileType;
        this.fileId = fileId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getReturnFileType() {
        return returnFileType;
    }

    public void setReturnFileType(Integer returnFileType) {
        this.returnFileType = returnFileType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
