package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.Model.GetUserpre;
import com.example.myapplication.dao.LoginJson;
import com.example.myapplication.dao.User;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    GetUserpre getUserpre = new GetUserpre();
    LoginJson loginJson = new LoginJson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {

        User user = new User();
        EditText edid_userid = findViewById(R.id.user_id);
        EditText edid_passwd = findViewById(R.id.passwd);
        user.setUserid(edid_userid.getText().toString());
        user.setPassed(edid_passwd.getText().toString());
        //
        getUserpre.postReq(user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("1111111", e.getMessage());
                //Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("11111112", "33333333333");
              loginJson=getUserpre.getJson(response.body().string());
                //Log.d("11111113", loginJson.getUsername());
              Boolean a=loginJson.getState();
                Log.d("11111114",loginJson.getMsg());
              if(a)
              {
                  Looper.prepare();
                  //Log.d("11111113", response.body().string());
                  Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                  Looper.loop();
              }
              else
              {
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                      }
                  });
                  Looper.prepare();
                  Looper.loop();
              }
            }
        });
    }
}
