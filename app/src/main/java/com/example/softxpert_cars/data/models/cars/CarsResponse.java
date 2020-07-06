package com.example.softxpert_cars.data.models.cars;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("data")
    private List<CarModel> cars;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CarModel> getCars() {
        return cars;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }
}
