package com.example.myapplication.Presenter;


import com.example.myapplication.Model.RegisterModel;
import com.example.myapplication.View.registerView;

import okhttp3.Callback;


public class RegisterPre {
    registerView registerView;
    RegisterModel registerModel;

    public RegisterPre(registerView registerView) {
        this.registerModel = new RegisterModel();
        this.registerView = registerView;
    }

    //发送验证码
    public void sengVerification(Callback callback) {
        registerModel.verificationCode(registerView.getphone(), callback);
    }
    //注册
    public void registerImp(Callback callback)
    {
        registerModel.sendRegister(registerView.getRegisterData(),callback);
    }

}
