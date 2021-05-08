package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.Model.AddCarModel;
import com.example.myapplication.R;
import com.example.myapplication.View.addCarView;
import com.example.myapplication.dao.CarDao;

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
               Log.d("1111111111", e.getMessage());
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               Log.d("11111111119", response.body().string());
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
