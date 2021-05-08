package com.example.myapplication.dao;
//需要在登录的时候记录  使用一次
public class LoginJsonReturn {
    String userid;
    String password;
    String username;
    Boolean state;
    String msg;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public String getPassword() {
        return password;
    }

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }
}
