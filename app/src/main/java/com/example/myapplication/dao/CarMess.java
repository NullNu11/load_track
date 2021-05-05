package com.example.myapplication.dao;

public class CarMess {
    String CarInfo;
    String CarUser;
    String CarStr;
    int CarId;

    public int getCarId() {
        return CarId;
    }

    public String getCarInfo() {
        return CarInfo;
    }

    public String getCarStr() {
        return CarStr;
    }

    public String getCarUser() {
        return CarUser;
    }

    public void setCarInfo(String carInfo) {
        CarInfo = carInfo;
    }

    public void setCarUser(String carUser) {
        CarUser = carUser;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public void setCarStr(String carStr) {
        CarStr = carStr;
    }
}
