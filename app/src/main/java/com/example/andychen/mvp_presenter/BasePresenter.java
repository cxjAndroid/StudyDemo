package com.example.andychen.mvp_presenter;

import android.content.Context;

import com.example.andychen.retrofit.ApiService;
import com.example.andychen.retrofit.RetrofitMethods;

/**
 * Created by chenxujun on 2016/6/24.
 */
public class BasePresenter<T> {
    public T mView;
    public Context mContext;

    public BasePresenter(T mView) {
        attach(mView);
    }

    public BasePresenter(T mView, Context mContext) {
        this.mContext = mContext;
        attach(mView);
    }

    public void attach(T view){
        this.mView = view;
    }

    public void detach() {
        mView = null;
    }


    public ApiService getApiService(){
        return RetrofitMethods.getApiService();
    }
}
