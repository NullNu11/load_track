package com.example.myapplication.Model;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.myapplication.dao.LoginJson;
import com.example.myapplication.dao.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//版本迭代的牺牲品
//disapper
public class GetUserpre {


    private String mBaseUrl = "http://192.168.43.248:8080/OkHttp_Get/";
    String responStr = "";
    OkHttpClient client = new OkHttpClient();
    //message信息发送到主线程
    Message message = Message.obtain();
    Boolean status = true;
    LoginJson loginJson = new LoginJson();
    //handler


    public Handler handler = new Handler() {
        int a=0;
        //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //失败    网络请求失败          不是  登录失败
                    Log.d("2222222", String.valueOf(msg.obj));
                    getJson(String.valueOf(msg.obj));
                    break;
                case 1:
                    //  请求成功
                    getJson(String.valueOf(msg.obj));
                    Boolean sta=loginJson.getState();
                    Log.d("22222222222222222227", String.valueOf(sta));
                    break;
            }
        }
    };


    //接口回调函数
    public interface getuserid {
        void returnMess(Boolean s);
    }

    //解析json数据
    //数据类型
    //{"msg":"登录成功","data":{"userId":"18616338025","username":"刘先生","password":"123456"},"state":true}
    public LoginJson getJson(String st) {

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

    //普通okhttp请求   网络请求数据成功
    //参数传回handler   ui线程无法拿到数据
    public void login(User user, getuserid getusers) {
        //http://8.133.178.130:8020/user/login?id=1&password=2
        RequestBody body = new FormBody.Builder()
                .add("id",user.getUserid())
                .add("password", user.getPassed()).build();
        Request requests = new Request.Builder()
                .url("http://8.133.178.130:8020/user/login")
                .post(body).build();
        Log.d("11111111111111", String.valueOf(requests));

        //新开线程同步请求
        //1
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //提交
//                    Response rs=client.newCall(requests).execute();
//                    Log.d("11111111111111", rs.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e("1111111111112", String.valueOf(e));
//                }
//            }
//        }).start();

        //异步请求
        //2
        Call call = client.newCall(requests);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("login failure", e.getMessage());
                message.what = 0;
                message.obj = e.getMessage();
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responStr = response.body().string();
                Log.d("111111114", responStr);
                message.what = 1;
                message.obj = responStr;
                handler.sendMessage(message);
            }
        });
        //接口回调
        getusers.returnMess(status);
    }

    //succ
//封装okhttp   接口回调在mainactiv
    public void postReq(User user,Callback call)
    {
        RequestBody body = new FormBody.Builder()
                .add("id",user.getUserid())
                .add("password", user.getPassed()).build();
        Request requests = new Request.Builder()
                .url("http://8.133.178.130:8020/user/login")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(requests).enqueue(call);
    }
}
