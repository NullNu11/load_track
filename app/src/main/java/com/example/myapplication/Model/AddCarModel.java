package com.example.myapplication.Model;

import com.example.myapplication.dao.CarDao;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddCarModel {
    String url="http://47.101.48.189:8010/car/add";

    public void addCarMod(CarDao carDao, Callback call) {
       if(carDao !=null){
           if(!carDao.getCarUser().equals("error")) {
               JSONObject jsonObject = new JSONObject();
               try {
                   jsonObject.put("carInfo", carDao.getCarInfo());
                   jsonObject.put("carStr", carDao.getCarStr());
                   jsonObject.put("carUser", carDao.getCarUser());
                   //jsonObject.put("CarId", String.valueOf(carDao.getCarId()));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               RequestBody body=FormBody.create(MediaType.parse("application/json; charset=utf-8"),String.valueOf(jsonObject));

               Request requests = new Request.Builder()
                       .url(url)
                       .post(body)
                       .build();
               OkHttpClient client = new OkHttpClient();
               client.newCall(requests).enqueue(call);
           }
           else{
               //未登录
           }
       }
    }
}
