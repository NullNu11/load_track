package com.example.myapplication.Model;

import android.app.Application;
import android.content.Context;
import com.example.myapplication.R;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetTrackModel {

//http://8.133.178.130
    //获取历史信息列表
    //listview
    String url = "http://8.133.178.130:3000/route/trailapi/searchTrailByid?userid=";

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

    //获取历史信息的详细信息
    public void getBlockImp(String trid, Callback call) {

        String TridUrl= "http://8.133.178.130:3000/searchBlockByTrid?trid=";
        if (trid != null) {
            Request requests = new Request.Builder()
                    .url(TridUrl+trid)
                    .get()
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(requests).enqueue(call);
        } else {
            //  在view时判断了的
        }
    }


}
