package com.example.myapplication.dao;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class TrackDao {
    String userid="";
    String devstr="";
    String trid="";
    double mile=0;
    String time="";
    String devid="";

    String carid="";

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    //public List<LatLng> latLngs;
    public List<LatLng> latLngs = new ArrayList<LatLng>();

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }


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
