package com.example.myapplication.dao;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class track {
    String userid;
    String devstr;
    String trid;
    double mile;
    String time;
   //public List<LatLng> latLngs;
   public List<LatLng> latLngs = new ArrayList<LatLng>();


    public void setLatLngs(List<LatLng> latLngs) {
        this.latLngs = latLngs;
    }

    public List<LatLng> getLatLngs() {
        return latLngs;
    }

    public double getMile() {
        return mile;
    }

    public String getDevstr() {
        return devstr;
    }

    public String getTime() {
        return time;
    }

    public String getTrid() {
        return trid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDevstr(String devstr) {
        this.devstr = devstr;
    }

    public void setMile(double mile) {
        this.mile = mile;
    }

    public void setTrid(String trid) {
        this.trid = trid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
