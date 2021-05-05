package com.example.myapplication.Model;

import com.example.myapplication.dao.CarMess;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class getTrack {

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
