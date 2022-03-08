package com.example.myapplication.Model;

import com.example.myapplication.dao.UserDao;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LoginModel {

    public void login(UserDao userDao, Callback call) {
        RequestBody body = new FormBody.Builder()
                .add("id", userDao.getUserid())
                .add("password", userDao.getPassed()).build();
        Request requests = new Request.Builder()
                .url("http://47.101.48.189:8020/user/login")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(requests).enqueue(call);
    }
}
