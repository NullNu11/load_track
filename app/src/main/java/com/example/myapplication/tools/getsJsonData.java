package com.example.myapplication.tools;

import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.example.myapplication.dao.LoginJsonReturn;
import com.example.myapplication.dao.TrackDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
    public static String insert_char(String str, String insertChar, int location) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (str.length() <= location) {

        } else {
            //stringBuffer.replace(location,location+1,insertChar);
            stringBuffer.insert(location, insertChar);
            stringBuffer.delete(19, 23);
        }
        return stringBuffer.toString();
    }

    //存在转义字符的json
    public List<TrackDao> trackJson(String str) {
        //List<LatLng> latLngs = new ArrayList<LatLng>();
        HashMap<String, String> map = new HashMap<>();
        {
            map.put("xin", "新");
            map.put("zang", "藏");
            map.put("qing", "青");
            map.put("chuan", "川");
            map.put("hu", "沪");
            map.put("xiang", "湘");
            map.put("zhe", "浙");
            map.put("su", "苏");
            map.put("min", "闽");
            map.put("yue", "粤");
            map.put("qiong", "琼");
            map.put("hei", "黑");
            map.put("jing", "京");
            map.put("meng", "蒙");
            map.put("ji", "吉");
            map.put("ji1", "冀");
            map.put("gan", "赣");
            map.put("yu", "豫");
            map.put("yu1", "渝");
            map.put("jin1", "晋");
            map.put("gan1", "甘");
            map.put("jin", "津");
            map.put("wan", "皖");
            map.put("qian", "黔");
            map.put("tai", "台");
            map.put("shan", "陕");
            map.put("e", "鄂");
            map.put("lu", "鲁");
            map.put("dian", "滇");
            map.put("liao", "辽");
            map.put("gui", "桂");
            map.put("ning", "宁");
            map.put("gang", "港");
            map.put("ao", "澳");
        }

        List<LatLng> latLngs;
        //TrackDao[] Track=new ;
        List<TrackDao> Track = new ArrayList<>();
        try {
            //返回值  ，jsonarray
            JSONArray jsonArray = new JSONArray(str);
            JSONObject[] jsonObject = new JSONObject[jsonArray.length()];
            //Track = new TrackDao[jsonArray.length()];
            //第0一条数据

            for (int i = 0; i < jsonArray.length(); i++) {
                //一条轨迹\
                Track.add(new TrackDao());
                jsonObject[i] = jsonArray.getJSONObject(i);
                //            //轨迹点
                String jsonStr = jsonObject[i].getString("jsonStr");
                String time = jsonObject[i].getString("time");
                time = delete_char(time, 'T');
                time = delete_char(time, 'Z');
                time = insert_char(time, " ", 10);
                //Track[i]=new TrackDao();
                Track.get(i).setTime(time);
                jsonStr = delete_char(jsonStr, '\\');
                //Log.d("111111111111", jsonStr);
                //经测试数据符合预期
                JSONObject jsonObject1 = new JSONObject(jsonStr);
                jsonStr = jsonObject1.getString("points");
                Track.get(i).setDevstr(jsonObject1.getString("devstr"));
                Track.get(i).setDevid(jsonObject1.getString("devid"));
                Track.get(i).setTrid(jsonObject1.getString("trid"));
                Track.get(i).setMile(jsonObject1.getDouble("mile"));
                JSONArray jsonArray1 = new JSONArray(jsonStr);
                latLngs = Track.get(i).latLngs;
                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject jsonObject_loc = jsonArray1.getJSONObject(j);
                    latLngs.add(new LatLng(Double.parseDouble(jsonObject_loc.getString("locationy")), Double.parseDouble(jsonObject_loc.getString("locationx"))));
                }
            }


            //jsonObject = jsonArray.getJSONObject(0);


//            //轨迹点
//            String jsonStr = jsonObject.getString("jsonStr");
//            String time=jsonObject.getString("time");
//            time= delete_char(time,'T');
//            time= delete_char(time,'Z');
//            time=insert_char(time," ",10);
//            TrackDao.setTime(time);
//            //去转义字符
//            jsonStr = delete_char(jsonStr, '\\');
//            //Log.d("111111111111", jsonStr);
//            //经测试数据符合预期
//            JSONObject jsonObject1 = new JSONObject(jsonStr);
//            jsonStr = jsonObject1.getString("points");
//            TrackDao.setDevstr(jsonObject1.getString("devstr"));
//            TrackDao.setDevid(jsonObject1.getString("devid"));
//            TrackDao.setTrid(jsonObject1.getString("trid"));
//            TrackDao.setMile(jsonObject1.getDouble("mile"));
//            JSONArray jsonArray1 = new JSONArray(jsonStr);
//
//            for (int i = 0; i < jsonArray1.length(); i++) {
//                jsonObject = jsonArray1.getJSONObject(i);
//                latLngs.add(new LatLng(Double.parseDouble(jsonObject.getString("locationx")),Double.parseDouble(jsonObject.getString("locationy"))));
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Track;
    }


}
