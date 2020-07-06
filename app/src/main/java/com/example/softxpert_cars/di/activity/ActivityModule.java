package com.example.softxpert_cars.di.activity;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.softxpert_cars.base.BaseActivity;
import com.example.softxpert_cars.di.qualifier.ForActivity;
import com.example.softxpert_cars.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This class is responsible for providing the requested objects to {@link ActivityScope} annotated classes
 */

@Module
public class
ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    BaseActivity provideActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    @ForActivity
    Context provideActivityContext() {
        return activity;
    }

    @ActivityScope
    @ForActivity
    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

}