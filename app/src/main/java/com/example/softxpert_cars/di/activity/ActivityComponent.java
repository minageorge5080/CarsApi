package com.example.softxpert_cars.di.activity;

import com.example.softxpert_cars.ui.MainActivity;
import com.example.softxpert_cars.base.BaseActivity;
import com.example.softxpert_cars.di.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

}