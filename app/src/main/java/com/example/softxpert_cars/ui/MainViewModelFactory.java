package com.example.softxpert_cars.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.softxpert_cars.data.repositry.CarsRepo;
import com.example.softxpert_cars.di.scope.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CarsRepo carsRepo;

    @Inject
    MainViewModelFactory(CarsRepo carsRepo) {
        this.carsRepo = carsRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(carsRepo);
    }
}
