package com.example.myapplication.JsonData;

import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.example.myapplication.dao.LoginJsonReturn;
import com.example.myapplication.dao.TrackDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class getsJsonData {

    public LoginJsonReturn getLoginJson(String st) {
        LoginJsonReturn loginJsonReturn = new LoginJsonReturn();
        try {
            JSONObject jsonObject0 = new JSONObject(st);
            loginJsonReturn.setMsg(jsonObject0.getString("msg"));
            loginJsonReturn.setState(jsonObject0.getBoolean("state"));
            Log.d("222222228", String.valueOf(loginJsonReturn.getState()));
            if (true == loginJsonReturn.getState()) {
                JSONObject jsonObject = new JSONObject(String.valueOf(jsonObject0.getJSONObject("data")));
                loginJsonReturn.setUserid(jsonObject.getString("userId"));
                loginJsonReturn.setUsername(jsonObject.getString("username"));
                loginJsonReturn.setPassword(jsonObject.getString("password"));
            } else {
                //登录失败了，json不需要解析
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("22222222225", e.getMessage());
        }
        return loginJsonReturn;
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


    //删除某个字符
    public static String delete_char(String str, char delChar) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != delChar) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    //在某个位置添加某个字符
    public static String insert_char(String str, String insertChar,int location) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if(str.length()<=location)
        {

        }else {
            //stringBuffer.replace(location,location+1,insertChar);
            stringBuffer.insert(location,insertChar);
            stringBuffer.delete(19,23);
        }
        return stringBuffer.toString();
    }
    //存在转义字符的json
    public TrackDao trackJson(String str) {
        //List<LatLng> latLngs = new ArrayList<LatLng>();
        TrackDao TrackDao = new TrackDao();
        List<LatLng> latLngs = TrackDao.latLngs;
        try {
            //返回值  ，jsonarray
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject = new JSONObject();
            //第0一条数据
            jsonObject = jsonArray.getJSONObject(0);
            String jsonStr = jsonObject.getString("jsonStr");
            //一个搞定
            String time=jsonObject.getString("time");
            time= delete_char(time,'T');
            time= delete_char(time,'Z');
            time=insert_char(time," ",10);
            TrackDao.setTime(time);
            //去转义字符
            jsonStr = delete_char(jsonStr, '\\');
            //Log.d("111111111111", jsonStr);
            //经测试数据符合预期
            JSONObject jsonObject1 = new JSONObject(jsonStr);
            jsonStr = jsonObject1.getString("points");
            TrackDao.setDevstr(jsonObject1.getString("devstr"));
            TrackDao.setDevid(jsonObject1.getString("devid"));
            TrackDao.setTrid(jsonObject1.getString("trid"));
            TrackDao.setMile(jsonObject1.getDouble("mile"));
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
        Log.d("88888888888", TrackDao.getDevstr()+ TrackDao.getTime()+ TrackDao.getTrid()+ TrackDao.getMile());
        return TrackDao;
    }


}
