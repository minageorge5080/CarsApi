package com.example.softxpert_cars.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.softxpert_cars.data.models.error.ErrorResponse;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> inProgress = new MutableLiveData<>();
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }



    public MutableLiveData<Boolean> getInProgress() {
        return inProgress;
    }



    public void processError(Throwable t) {
        if (t instanceof HttpException) {
            errorLiveData.setValue(getHttpErrorMessage((HttpException) t));
        } else if (t instanceof IOException) {
            errorLiveData.setValue("Check internet connection");
        } else {
            errorLiveData.setValue("an error happened");
        }
    }


    private String getHttpErrorMessage(HttpException httpException) {
        Gson gson = new Gson();
        try {
            ErrorResponse errorResponse = gson.fromJson(httpException.response().errorBody().string(), ErrorResponse.class);
            if (errorResponse != null) {
                return errorResponse.getError().getMessage();
            } else {
                return "an error happened";
            }
        } catch (Exception e) {
            return "an error happened";
        }
    }


    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
