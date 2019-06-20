package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/26.
 */
public class Material {
    //材料类型
    public Integer meterialType;
    //材料id
    public String fileId;

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
