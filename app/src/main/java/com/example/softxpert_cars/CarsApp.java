package com.example.softxpert_cars;

import android.app.Application;
import android.content.Context;

import com.example.softxpert_cars.di.application.AppComponent;
import com.example.softxpert_cars.di.application.AppModule;
import com.example.softxpert_cars.di.application.DaggerAppComponent;

public class CarsApp extends Application {


    private final AppComponent appComponent = createAppComponent();

    public static AppComponent getComponent(Context context) {
        return getApp(context).appComponent;
    }

    //This is a hack to get a non-static field from a static method (i.e. appComponent)
    private static CarsApp getApp(Context context) {
        return (CarsApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
