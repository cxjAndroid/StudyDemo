package com.example.andychen.retrofit;

import com.example.andychen.model.DoctorResult;
import com.example.andychen.model.Result;
import com.example.andychen.utils.NullStringToEmptyAdapterFactory;
import com.example.andychen.utils.RxUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chenxujun on 2016/8/19.
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


    public static <T> Subscription commonRequest(final Observable<Result<T>> observable, final Subscriber<T> subscriber) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Result<T>, T>() {
                    @Override
                    public T call(Result<T> tResult) {
                        if (!tResult.isSuccess()) {
                            throw new ApiException(ApiException.NO_SUCCESS, tResult.getReturnMessage());
                        }
                        return tResult.getData();
                    }
                })
                .subscribe(subscriber);
        RxUtils.get().addList(subscription);
        return subscription;

    }

    public static <T> Subscription commonRequest(final Observable<Result<T>> observable, final Observer<T> observer) {
        if (observer instanceof CustomObserver) {
            ((CustomObserver) observer).setObservable(observable);
        }
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Result<T>, T>() {
                    @Override
                    public T call(Result<T> tResult) {
                        if (!tResult.isSuccess()) {
                            throw new ApiException(ApiException.NO_SUCCESS, tResult.getReturnMessage());
                        }
                        return tResult.getData();
                    }
                })
                .subscribe(observer);
        RxUtils.get().addList(subscription);
        return subscription;
    }

    public static Subscription originRequest(final Observable<ResponseBody> observable, final Subscriber<ResponseBody> subscriber) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        RxUtils.get().addList(subscription);
        return subscription;

    }

    public static Subscription originRequest(final Observable<ResponseBody> observable, final Observer<ResponseBody> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        RxUtils.get().addList(subscription);
        return subscription;

    }


    public static <T> Call<Result<T>> commonRequest(Call<Result<T>> call, BeanRespCallBack<T> callBack) {
        call.enqueue(callBack);
        return call;
    }

    public static Call<ResponseBody> originRequest(Call<ResponseBody> call, RespCallback callBack) {
        call.enqueue(callBack);
        return call;
    }


    public static <T> Subscription flatRequest(Observable<T> observable, final flatCallback<T> callback,
                                               Observer<T> observer) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T t) {
                        return callback.onFlat(t);
                    }
                })
                .subscribe(observer);
    }


    public interface flatCallback<T> {
        Observable onFlat(T t);
    }

}
