package com.example.myapplication.dao;

public class registerData {
    String userName;
    String phoneNum;
    String passWd;
    String verifCode;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setVerifCode(String verifCode) {
        this.verifCode = verifCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWd() {
        return passWd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getVerifCode() {
        return verifCode;
    }
}
