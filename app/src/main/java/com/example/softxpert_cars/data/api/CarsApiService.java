package com.example.softxpert_cars.data.api;

import com.example.softxpert_cars.data.models.cars.CarsResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarsApiService {

    @GET("cars")
    Observable<CarsResponse> fetchCars(@Query("page") Integer page);
}