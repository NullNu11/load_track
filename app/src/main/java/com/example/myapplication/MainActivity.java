package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.JsonData.LoginJsonData;
import com.example.myapplication.Model.GetUserpre;
import com.example.myapplication.Presenter.loginPre;
import com.example.myapplication.View.getLoginMess;
import com.example.myapplication.dao.LoginJson;
import com.example.myapplication.dao.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements getLoginMess {

    LoginJsonData loginJsonData = new LoginJsonData();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    updateUI(String.valueOf(msg.obj));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginbtn(View view) {

        loginPre loginPre = new loginPre(this);
        loginPre.loginVandM(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("11111112", e.getMessage());
                //网络请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String reMess = response.body().string();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = reMess;
                handler.sendMessage(message);
            }
        });

        //        getUserpre.postReq(user, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("1111111", e.getMessage());
//                //Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("11111112", "33333333333");
//              loginJson=getUserpre.getJson(response.body().string());
//                //Log.d("11111113", loginJson.getUsername());
//              Boolean a=loginJson.getState();
//                Log.d("11111114",loginJson.getMsg());
//              if(a)
//              {
//                  Looper.prepare();
//                  //Log.d("11111113", response.body().string());
//                  Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
//                  Looper.loop();
//              }
//              else
//              {
//                  runOnUiThread(new Runnable() {
//                      @Override
//                      public void run() {
//                          Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
//                      }
//                  });
//                  Looper.prepare();
//                  Looper.loop();
//              }
//            }
//        });
    }


    @Override
    public User getUser() {
        User user = new User();
        EditText edid_userid = findViewById(R.id.user_id);
        EditText edid_passwd = findViewById(R.id.passwd);
        user.setUserid(edid_userid.getText().toString());
        user.setPassed(edid_passwd.getText().toString());
        return user;
    }

    void updateUI(String a) {
        LoginJson loginJson = new LoginJson();
        loginJson=loginJsonData.getJson(a);
        if(loginJson.getState()) {
            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
            //登录成功保存用户信息到sharedPreference
            SharedPreferences userInfo = getSharedPreferences("track",MODE_PRIVATE);
            SharedPreferences.Editor editor = userInfo.edit();//获取Editor
            //得到Editor后，写入需要保存的数据
            editor.putString("username", loginJson.getUsername());
            editor.putString("userid",loginJson.getUserid());
            editor.putString("passwd",loginJson.getPassword());
            editor.putString("state", String.valueOf(loginJson.getState()));
            editor.commit();//提交修改
            Log.i("111", "保存用户信息成功");
        }
        else {
            Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {
//        SharedPreferences track = getSharedPreferences("track", MODE_PRIVATE);
//        String name=track.getString("username","0000000");
//        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
    }
}
