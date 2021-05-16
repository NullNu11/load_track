package com.example.myapplication.Model;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.R;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetTrackModel {

    String url = "http://139.196.30.139:3000/searchTrailByid?userid=";

    public void getTrackImp(String userid, Callback call) {
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


    public void getBlockImp(String trid, Callback call) {

        String TridUrl= "http://139.196.30.139:3000/searchBlockByTrid?trid=";
        if (trid != null) {
            Request requests = new Request.Builder()
                    .url(TridUrl+trid)
                    .get()
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(requests).enqueue(call);
        } else {
            //大概在view时判断了的
        }
    }


}
