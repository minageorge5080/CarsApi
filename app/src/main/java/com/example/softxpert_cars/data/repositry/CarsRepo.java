package com.example.softxpert_cars.data.repositry;

import androidx.lifecycle.MutableLiveData;

import com.example.softxpert_cars.data.api.ApiUtils;
import com.example.softxpert_cars.data.models.cars.CarModel;
import com.example.softxpert_cars.data.models.cars.CarsResponse;
import com.example.softxpert_cars.di.scope.ActivityScope;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class CarsRepo {

    private final ApiUtils apiUtils;
    private MutableLiveData<List<CarModel>> cardLiveData = new MutableLiveData<>();

    @Inject
    CarsRepo(ApiUtils apiUtils) {
        this.apiUtils = apiUtils;
    }


    public Observable<CarsResponse> fetchCars(int page) {
        return  apiUtils.getCarsApiService().fetchCars(page);
    }


}
