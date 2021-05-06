package com.example.myapplication.JsonData;

import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.example.myapplication.dao.LoginJson;
import com.example.myapplication.dao.track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class getsJsonData {

    public LoginJson getLoginJson(String st) {
        LoginJson loginJson = new LoginJson();
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

    public Boolean verificarionJson(String s) {
        Boolean a = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            a = jsonObject.getBoolean("state");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }

    public String delete_escape(String str, char delChar) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != delChar) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    //存在转义字符的json
    public track trackJson(String str) {
        //List<LatLng> latLngs = new ArrayList<LatLng>();
        track track = new track();
        List<LatLng> latLngs = track.latLngs;
        try {
            //返回值  ，jsonarray
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject = new JSONObject();
            //第0一条数据
            jsonObject = jsonArray.getJSONObject(0);
            String jsonStr = jsonObject.getString("jsonStr");
            //一个搞定


            String time=jsonObject.getString("time");
            time=delete_escape(time,'T');
            time=delete_escape(time,'Z');

            track.setTime(time);
            //去转义字符
            jsonStr = delete_escape(jsonStr, '\\');
            //Log.d("111111111111", jsonStr);
            //经测试数据符合预期
            JSONObject jsonObject1 = new JSONObject(jsonStr);
            jsonStr = jsonObject1.getString("points");
            track.setDevstr(jsonObject1.getString("devstr"));
            track.setDevid(jsonObject1.getString("devid"));
            track.setTrid(jsonObject1.getString("trid"));
            track.setMile(jsonObject1.getDouble("mile"));
            JSONArray jsonArray1 = new JSONArray(jsonStr);
            Log.d("333333333333", String.valueOf(jsonArray1));
            double[] x = new double[1000];
            double[] y = new double[1000];
            for (int i = 0; i < jsonArray1.length(); i++) {
                jsonObject = jsonArray1.getJSONObject(i);
                x[i] = Double.parseDouble(jsonObject.getString("locationx"));
                y[i] = Double.parseDouble(jsonObject.getString("locationy"));
                latLngs.add(new LatLng(y[i],x[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("11111111111", String.valueOf(latLngs));
        Log.d("88888888888", track.getDevstr()+track.getTime()+track.getTrid()+track.getMile());
        return track;
    }


}
