package com.famiao.search.vo.form;

import java.io.Serializable;

/**
 * @author zengbin
 * @since 2019-06-18 18:48
 */
public class Lawer implements Serializable {

    private static final long serialVersionUID = 3297890061660354238L;

    private String province;
    private String city;
    private String area;
    private String realName;
    private String nickName;
    private String mobile;
    private String lawOfficeName;
    private String lawOfficeAddr;
    private String lawerType;
    private String qualificationNumber;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLawOfficeName() {
        return lawOfficeName;
    }

    public void setLawOfficeName(String lawOfficeName) {
        this.lawOfficeName = lawOfficeName;
    }

    public String getLawOfficeAddr() {
        return lawOfficeAddr;
    }

    public void setLawOfficeAddr(String lawOfficeAddr) {
        this.lawOfficeAddr = lawOfficeAddr;
    }

    public String getLawerType() {
        return lawerType;
    }

    public void setLawerType(String lawerType) {
        this.lawerType = lawerType;
    }

    public String getQualificationNumber() {
        return qualificationNumber;
    }

    public void setQualificationNumber(String qualificationNumber) {
        this.qualificationNumber = qualificationNumber;
    }

}
