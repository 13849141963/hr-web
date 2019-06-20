package com.fescotech.apps.admin.biz.admin.service;

/**
 * @author cao.guo.dong
 * @create 2017-09-12 14:17
 * @desc 原hosp表结构
 **/

public class hosp {
    public String hospName;
    public String code;
    public String province;
    public String city;
    public String grade;

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
