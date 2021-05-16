package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.JsonData.getsJsonData;
import com.example.myapplication.Presenter.RegisterPre;
import com.example.myapplication.R;
import com.example.myapplication.View.registerView;
import com.example.myapplication.dao.RegisterData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements registerView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    RegisterPre registerPre = new RegisterPre(this);
    //注册按钮
    public void register(View view) {
        final String[] msg = {","};
         registerPre.registerImp(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 Log.d("11111", "onFailure: "+e.getMessage());
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {

                 //Log.d("1111113", "onResponse: "+response.body().string());
                 String retRegister=response.body().string();
                 try {
                     JSONObject jsonObject = new JSONObject(retRegister);
                     msg[0] =jsonObject.getString("msg");
                     Looper.prepare();
                     Toast.makeText(RegisterActivity.this, msg[0],Toast.LENGTH_LONG).show();
                     if(msg[0].equals("注册失败"))
                     {

                     }
                     else {
                         //注册成功
                         //存在当前用户
                         finish();
                     }
                     Looper.loop();

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         });


    }

    //验证码按钮
    public void getVerificationCode(View view) {

        registerPre.sengVerification(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("111111", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String statu = response.body().string();
                getsJsonData getsJsonData = new getsJsonData();
                Boolean a = getsJsonData.verificarionJson(statu);
                if(a){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }
        });
    }


    @Override
    public String getphone() {
        String a = null;
        EditText editText = findViewById(R.id.phoneNum);
        a = String.valueOf(editText.getText());
        return a;
    }

    @Override
    public RegisterData getRegisterData() {
        EditText userid=findViewById(R.id.user_id);
        EditText phone=findViewById(R.id.phoneNum);
        EditText verif=findViewById(R.id.verif);
        EditText pass1=findViewById(R.id.passwd1);
        EditText pass2=findViewById(R.id.passwd2);

        String stUserid= String.valueOf(userid.getText());
        String stPhone= String.valueOf(phone.getText());
        String stVerif= String.valueOf(verif.getText());
        String stPass1= String.valueOf(pass1.getText());
        String stPass2=String.valueOf(pass2.getText());

        RegisterData registerData = new RegisterData();

        if(!stPass1.equals(stPass2)){
            Toast.makeText(this,"输入的密码不一致",Toast.LENGTH_LONG).show();
            registerData.setPhoneNum("error");
            return registerData;
        }
        else {
            registerData.setPhoneNum(stPhone);
            registerData.setPassWd(stPass1);
            registerData.setUserName(stUserid);
            registerData.setVerifCode(stVerif);
            return registerData;
        }
    }
}
