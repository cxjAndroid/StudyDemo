package com.example.jonchen.model;

/**
 * Created by chenxujun on 16-10-27.
 */

public class UserKm {
    private String realName;
    private long birthday;
    private String address;
    private String memberIdNumber;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberIdNumber() {
        return memberIdNumber;
    }

    public void setMemberIdNumber(String memberIdNumber) {
        this.memberIdNumber = memberIdNumber;
    }
}
