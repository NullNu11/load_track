package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.myapplication.JsonData.getsJsonData;
import com.example.myapplication.Presenter.LoginPre;
import com.example.myapplication.R;
import com.example.myapplication.View.getLoginMess;
import com.example.myapplication.dao.LoginJsonReturn;
import com.example.myapplication.dao.UserDao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements getLoginMess {

    getsJsonData getsJsonData = new getsJsonData();

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

        LoginPre loginPre = new LoginPre(this);
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
    public UserDao getUser() {
        UserDao userDao = new UserDao();
        EditText edid_userid = findViewById(R.id.user_id);
        EditText edid_passwd = findViewById(R.id.passwd1);
        userDao.setUserid(edid_userid.getText().toString());
        userDao.setPassed(edid_passwd.getText().toString());
        return userDao;
    }

    void updateUI(String a) {
        LoginJsonReturn loginJsonReturn = new LoginJsonReturn();
        loginJsonReturn = getsJsonData.getLoginJson(a);
        if(loginJsonReturn.getState()) {
            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
            //登录成功保存用户信息到sharedPreference
            SharedPreferences userInfo = getSharedPreferences("TrackDao",MODE_PRIVATE);
            SharedPreferences.Editor editor = userInfo.edit();//获取Editor
            //得到Editor后，写入需要保存的数据
            editor.putString("username", loginJsonReturn.getUsername());
            editor.putString("userid", loginJsonReturn.getUserid());
            editor.putString("passwd", loginJsonReturn.getPassword());
            editor.putString("state", String.valueOf(loginJsonReturn.getState()));
            editor.commit();//提交修改
            Log.i("111", "保存用户信息成功");
        }
        else {
            Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {
//        SharedPreferences TrackDao = getSharedPreferences("TrackDao", MODE_PRIVATE);
//        String name=TrackDao.getString("username","0000000");
//        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void addCarBtn(View view) {
        Intent intent = new Intent(MainActivity.this,AddCarActivity.class);
        startActivity(intent);
    }

    public void mapBtn(View view) {
        Intent intent = new Intent(MainActivity.this,GaodeActivity.class);
        startActivity(intent);
    }

    public void locationBtn(View view) {
        Intent intent = new Intent(MainActivity.this,locationActivity.class);
        startActivity(intent);
    }
}
