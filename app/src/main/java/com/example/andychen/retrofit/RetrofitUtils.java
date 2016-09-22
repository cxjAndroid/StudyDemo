package com.example.andychen.retrofit;

import com.example.andychen.utils.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenxujun on 2016/8/19.
 */
public class RetrofitUtils {

    public static ApiService getApiService() {
        return RetrofitMethods.getApiService();
    }

}









