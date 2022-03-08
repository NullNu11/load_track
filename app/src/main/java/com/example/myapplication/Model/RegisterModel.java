package com.example.myapplication.Model;

import com.example.myapplication.dao.RegisterData;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class RegisterModel {

    //发送验证码
   public void verificationCode(String phone,Callback callback)
    {
        //http://47.101.48.189:8020/
        OkHttpClient client = new OkHttpClient();
        String url = "http://47.101.48.189:8020/user/getMobile?mobile=";
        final Request request = new Request.Builder()
                .url(url + phone)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }
    //伪get  加post form表单请求完成注册
    public void sendRegister(RegisterData data, Callback call)
    {
       if(!data.getPhoneNum().equals("error"))
       {
           OkHttpClient client = new OkHttpClient();
           String url = "http://47.101.48.189:8020/user/registe?code=";
//        RequestBody body = new FormBody.Builder()
//                .add("password", pass)
//                .add("userId", phone)
//                .add("username",userName).build();
           JSONObject jsonObject = new JSONObject();
           try {
               jsonObject.put("password", data.getPassWd());
               jsonObject.put("userId", data.getPhoneNum());
               jsonObject.put("username",data.getUserName());
           } catch (JSONException e) {
               e.printStackTrace();
           }
           RequestBody body=FormBody.create(MediaType.parse("application/json; charset=utf-8"),String.valueOf(jsonObject));
           final Request request = new Request.Builder()
                   .url(url+data.getVerifCode())
                   .post(body)
                   .build();
           client.newCall(request).enqueue(call);
       }
       else {
           //密码不一样不处理
       }
    }
}
