package com.example.softxpert_cars.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.softxpert_cars.base.BaseViewModel;
import com.example.softxpert_cars.data.models.cars.CarModel;
import com.example.softxpert_cars.data.repositry.CarsRepo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private final CarsRepo carsRepo;
    private MutableLiveData<List<CarModel>> carsLiveData = new MutableLiveData<>();
    private boolean carsPagesEnd = false;

    public MainViewModel(CarsRepo carsRepo) {
        this.carsRepo = carsRepo;
    }

    public void getCars(int page) {
        if (!carsPagesEnd)
            compositeDisposable.add(carsRepo.fetchCars(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(v-> getInProgress().setValue(true))
                    .doFinally(()-> getInProgress().setValue(false))
                    .subscribe(carsResponse -> {
                        if (carsResponse.getStatus() == 1) {
                            carsLiveData.setValue(carsResponse.getCars());
                        } else {
                            carsPagesEnd = true;
                        }

                    }, this::processError));
    }

    public MutableLiveData<List<CarModel>> getCarsLiveData() {
        return carsLiveData;
    }

    public void refreshCarsData() {
        carsPagesEnd = false;
        getCars(1);
    }
}
