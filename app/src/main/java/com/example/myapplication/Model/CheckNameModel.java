package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.Presenter.CheckNamePre;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckNameModel {
    String url="http://47.101.48.189:8020/user/makeSureIdCard?ID=";

    public void check_name(String id, String name, Callback call) {
        Request requests = new Request.Builder()
                .url(url+id+"&"+"name="+name)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(requests).enqueue(call);
    }
}
