package com.example.myapplication.Presenter;

import com.example.myapplication.Model.GetTrackModel;
import com.example.myapplication.View.trackView;

import okhttp3.Callback;

public class TrackPre {
    trackView trackV;
    GetTrackModel getTrackModelM;

    public TrackPre(trackView trackView) {
        this.getTrackModelM = new GetTrackModel();
        this.trackV = trackView;
    }

    public void addTrack(Callback callback) {
        getTrackModelM.getTrackImp(trackV.getUserid(), callback);
    }

    public void findTrid(Callback callback) {
        getTrackModelM.getBlockImp(trackV.getTrid(),callback);
    }
}
