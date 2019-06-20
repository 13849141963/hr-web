package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/2/28.
 */
public class LoadHouseholdReq {
    private String[] empTaskIds;

    private String[] busiCustNos;

    public String[] getEmpTaskIds() {
        return empTaskIds;
    }

    public void setEmpTaskIds(String[] empTaskIds) {
        this.empTaskIds = empTaskIds;
    }

    public String[] getBusiCustNos() {
        return busiCustNos;
    }

    public void setBusiCustNos(String[] busiCustNos) {
        this.busiCustNos = busiCustNos;
    }
}
