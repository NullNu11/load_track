package com.example.myapplication.Model;

import com.example.myapplication.dao.User;

import java.util.logging.StreamHandler;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class loginModImp {

    public void login(User user, Callback call) {
        RequestBody body = new FormBody.Builder()
                .add("id", user.getUserid())
                .add("password", user.getPassed()).build();
        Request requests = new Request.Builder()
                .url("http://8.133.178.130:8020/user/login")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(requests).enqueue(call);
    }

}
