package com.example.myapplication.JsonData;

import android.util.Log;

import com.example.myapplication.dao.LoginJson;

import org.json.JSONException;
import org.json.JSONObject;

public class getsJsonData {

    public LoginJson getLoginJson(String st) {
        LoginJson loginJson = new LoginJson();
        try {
            JSONObject jsonObject0 = new JSONObject(st);
            loginJson.setMsg(jsonObject0.getString("msg"));
            loginJson.setState(jsonObject0.getBoolean("state"));
            Log.d("222222228", String.valueOf(loginJson.getState()));
            if (true == loginJson.getState()) {
                JSONObject jsonObject = new JSONObject(String.valueOf(jsonObject0.getJSONObject("data")));
                loginJson.setUserid(jsonObject.getString("userId"));
                loginJson.setUsername(jsonObject.getString("username"));
                loginJson.setPassword(jsonObject.getString("password"));
            } else {
                //登录失败了，json不需要解析
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("22222222225", e.getMessage());
        }
        return loginJson;
    }

    public Boolean verificarionJson(String s)
    {
        Boolean a=null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            a= jsonObject.getBoolean("state");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }


}
