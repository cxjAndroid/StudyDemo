package com.example.andychen.myapplication.activity.mvp_model;

/**
 * Created by chenxujun on 2016/7/13.
 */
public class User {

    private int id;
    private String phone;
    private String name;
    private String portait;
    private String pushNo;
    private String accessToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortait() {
        return portait;
    }

    public void setPortait(String portait) {
        this.portait = portait;
    }

    public String getPushNo() {
        return pushNo;
    }

    public void setPushNo(String pushNo) {
        this.pushNo = pushNo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
