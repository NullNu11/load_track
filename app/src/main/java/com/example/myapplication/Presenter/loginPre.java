package com.example.myapplication.Presenter;

import com.example.myapplication.Model.loginModImp;
import com.example.myapplication.Model.returnLoginMess;
import com.example.myapplication.View.getLoginMess;

import okhttp3.Callback;


public class loginPre {
    getLoginMess getLoginMess;
    loginModImp loginModImp;

    public loginPre(getLoginMess views) {
        this.getLoginMess = views;
        this.loginModImp = new loginModImp();
    }

    public void loginVandM(Callback call){
        loginModImp.login(getLoginMess.getUser(),call);
     }
}
