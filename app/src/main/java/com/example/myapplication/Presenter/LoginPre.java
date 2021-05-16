package com.example.myapplication.Presenter;

import com.example.myapplication.Model.LoginModel;
import com.example.myapplication.View.getLoginMess;

import okhttp3.Callback;


public class LoginPre {
    getLoginMess getLoginMess;
    LoginModel LoginModel;

    public LoginPre(getLoginMess getLoginMess) {
        this.getLoginMess = getLoginMess;
        this.LoginModel = new LoginModel();
    }

    public void loginVandM(Callback call){
        LoginModel.login(getLoginMess.getUser(),call);
     }
}
