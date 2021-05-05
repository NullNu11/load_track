package com.example.myapplication.Model;

import com.example.myapplication.dao.CarMess;
import com.example.myapplication.dao.User;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddCar {
    String url="http://8.133.178.130:8010/car/add";

    public void addCarMod(CarMess carMess, Callback call) {
       if(carMess!=null){
           if(!carMess.getCarUser().equals("error")) {
               JSONObject jsonObject = new JSONObject();
               try {
                   jsonObject.put("carInfo",carMess.getCarInfo());
                   jsonObject.put("carStr",carMess.getCarStr());
                   jsonObject.put("carUser",carMess.getCarUser());
                   //jsonObject.put("CarId", String.valueOf(carMess.getCarId()));
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
