package com.example.andychen.myapplication.activity.mvp_presenter;

import android.view.View;

import com.example.andychen.myapplication.activity.retrofit.ApiService;
import com.example.andychen.myapplication.activity.retrofit.RetrofitMethods;

/**
 * Created by andychen on 2016/6/24.
 */
public class BasePresenter<T> {

    public T view;

    public void attach(T view){
        this.view = view;
    }

    public void detach() {
        view = null;
    }


    public ApiService getApiService(){
        return RetrofitMethods.getApiService();
    }
}
