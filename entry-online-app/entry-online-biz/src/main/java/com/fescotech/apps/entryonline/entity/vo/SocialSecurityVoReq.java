package com.fescotech.apps.entryonline.entity.vo;

import java.util.List;

/**
 * Created by cy on 2018/2/11.
 */
public class SocialSecurityVoReq extends PageRequest{
    private Integer insStatus;
    private Integer jobType;
    private Integer intoChangeFlag;
    private Integer userInfoType;
    private String userInfoContent;
    private String companyName;

    public Integer getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getIntoChangeFlag() {
        return intoChangeFlag;
    }

    public void setIntoChangeFlag(Integer intoChangeFlag) {
        this.intoChangeFlag = intoChangeFlag;
    }

    public Integer getUserInfoType() {
        return userInfoType;
    }

    public void setUserInfoType(Integer userInfoType) {
        this.userInfoType = userInfoType;
    }

    public String getUserInfoContent() {
        return userInfoContent;
    }

    public void setUserInfoContent(String userInfoContent) {
        this.userInfoContent = userInfoContent;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
