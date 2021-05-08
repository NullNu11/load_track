package com.example.myapplication.Model;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetTrackModel {

    String url = "http://139.196.30.139:3000/searchTrailByid?userid=";

    public void addCarMod(String userid, Callback call) {
        if (userid != null) {
            //RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
            Request requests = new Request.Builder()
                    .url(url+userid)
                    .get()
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(requests).enqueue(call);
        }
    }
}
