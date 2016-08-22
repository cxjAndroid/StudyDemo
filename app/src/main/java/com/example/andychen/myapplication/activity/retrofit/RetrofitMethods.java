package com.example.andychen.myapplication.activity.retrofit;

import com.example.andychen.myapplication.activity.bean.Result;
import com.example.andychen.myapplication.activity.utils.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOError;
import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by andychen on 2016/8/19.
 */
public class RetrofitMethods {

    public static final String HK_BASE_URL = "https://patientapi.hk515.com/";
    public static final String KM_BASE_URL = "http://120.25.225.5:8090/";
    private static ApiService apiService;
    private static RetrofitMethods retrofitMethods = null;

    private RetrofitMethods() {

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getHttpClient())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitMethods getInstance() {
        if (retrofitMethods == null) {
            synchronized (RetrofitMethods.class) {
                if (retrofitMethods == null) {
                    retrofitMethods = new RetrofitMethods();
                }
            }
        }
        return retrofitMethods;
    }


    public static ApiService getApiService() {
        getInstance();
        return apiService;
    }


    public static <T> Subscription CommonRequest(final Observable<Result<T>> observable, final Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Result<T>, T>() {
                    @Override
                    public T call(Result<T> tResult) {
                        if (!tResult.isSuccess()) throw new ApiException(tResult.getReturnMessage());
                        return tResult.getData();
                    }
                })
                .subscribe(subscriber);
    }


    public static <T> Subscription flatRequest(Observable<T> observable, final flatCallback<T> callback,
                                               Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T t) {
                        return callback.onFlat(t);
                    }
                })
                .subscribe(subscriber);
    }


    public interface flatCallback<T> {
        Observable onFlat(T t);
    }

}
