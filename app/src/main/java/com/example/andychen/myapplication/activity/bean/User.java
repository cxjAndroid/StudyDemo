package com.example.andychen.myapplication.activity.bean;

/**
 * Created by andychen on 2016/7/13.
 */
public class User {

    private String mobilePhone;
    private String password;
    private String phoneOsType;
    private String pushInfo;
    private String userType;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneOsType() {
        return phoneOsType;
    }

    public void setPhoneOsType(String phoneOsType) {
        this.phoneOsType = phoneOsType;
    }

    public String getPushInfo() {
        return pushInfo;
    }

    public void setPushInfo(String pushInfo) {
        this.pushInfo = pushInfo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
