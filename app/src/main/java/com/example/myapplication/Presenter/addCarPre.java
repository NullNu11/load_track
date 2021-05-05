package com.example.myapplication.Presenter;

import com.example.myapplication.Model.AddCar;
import com.example.myapplication.Model.loginModImp;
import com.example.myapplication.View.addCarView;
import com.example.myapplication.View.getLoginMess;

import okhttp3.Callback;

public class addCarPre {

     addCarView carView;
     AddCar addCar;

    public addCarPre(addCarView carView) {
        this.carView = carView;
        this.addCar = new AddCar();
    }

    public void addCarVandM(Callback call){
        addCar.addCarMod(carView.getCarmess(),call);
    }
}
