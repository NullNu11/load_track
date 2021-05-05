package com.example.myapplication.Presenter;

import com.example.myapplication.Model.getTrack;
import com.example.myapplication.View.trackView;

import okhttp3.Callback;

public class trackPre {
    trackView trackV;
    getTrack getTrackM;
 public trackPre(trackView trackView){
     this.getTrackM=new getTrack();
     this.trackV=trackView;
 }

    public void addTrack(Callback callback) {
        getTrackM.addCarMod(trackV.getUserid(), callback);
    }
}
