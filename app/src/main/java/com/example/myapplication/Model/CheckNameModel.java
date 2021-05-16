package com.example.myapplication.Model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckNameModel {
    String url="http://8.133.178.130:8020/user/makeSureIdCard?ID=";
    String id="4564968456496456";
    String name="刘强";
    public void check_name() {
        Request requests = new Request.Builder()
                .url(url+id+"&"+"name="+name)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(requests).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("1111111112", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("1111111111111113", response.body().string());
            }
        });
    }
}
