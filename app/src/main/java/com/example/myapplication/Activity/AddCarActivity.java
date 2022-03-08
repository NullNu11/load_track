package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.AddCarModel;
import com.example.myapplication.R;
import com.example.myapplication.View.addCarView;
import com.example.myapplication.dao.CarDao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddCarActivity extends AppCompatActivity implements addCarView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
    }

    public void btnSubmit(View view) {
       new AddCarModel().addCarMod(getCarmess(), new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               Looper.prepare();
               Toast.makeText(getApplicationContext(),"网络请求失败",Toast.LENGTH_LONG).show();
               Looper.loop();
              // Log.d("1111111111", e.getMessage());
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               String msg=response.body().string();
               try {
                   JSONObject jsonObject = new JSONObject(msg);
                   msg=jsonObject.getString("msg");
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               Looper.prepare();
               Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
               Looper.loop();
           }
       });
    }

    @Override
    public CarDao getCarmess() {
        CarDao carDao = new CarDao();
        //后端数据库自增列    不需要传输
        //carDao.setCarId(98);
        SharedPreferences userInfo = getSharedPreferences("TrackDao",MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        String userId=null;
        userId=userInfo.getString("userid","error");
        EditText id=findViewById(R.id.textCarid);
        EditText  info=findViewById(R.id.textCarstr);
        if(info.getText()!=null&&id.getText()!=null)
        {
            carDao.setCarInfo(info.getText().toString());
            carDao.setCarStr(id.getText().toString());
            carDao.setCarUser(userId);
            return carDao;
        }
       return null;
    }
}
