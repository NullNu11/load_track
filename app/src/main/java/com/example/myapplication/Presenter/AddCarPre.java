package com.example.myapplication.Presenter;

import com.example.myapplication.Model.AddCarModel;
import com.example.myapplication.View.addCarView;

import okhttp3.Callback;

public class AddCarPre {

     addCarView carView;
     AddCarModel addCarModel;

    public AddCarPre(addCarView carView) {
        this.carView = carView;
        this.addCarModel = new AddCarModel();
    }

    public void addCarVandM(Callback call){
        addCarModel.addCarMod(carView.getCarmess(),call);
    }
}
