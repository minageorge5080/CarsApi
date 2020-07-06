package com.example.softxpert_cars.di.application;

import com.example.softxpert_cars.CarsApp;
import com.example.softxpert_cars.di.activity.ActivityComponent;
import com.example.softxpert_cars.di.activity.ActivityModule;
import com.example.softxpert_cars.di.scope.ApplicationScope;

import dagger.Component;

/**
 * This interface is used by dagger to generate the code that defines the connection between the provider of objects
 * (i.e. {@link AppModule}), and the object which expresses a dependency.
 */

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    ActivityComponent plus(ActivityModule activityModule);

    void inject(CarsApp carsApp);
}