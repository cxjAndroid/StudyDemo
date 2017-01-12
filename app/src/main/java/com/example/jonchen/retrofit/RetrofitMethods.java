package com.example.jonchen.retrofit;

import com.example.jonchen.model.Result;
import com.example.jonchen.model.ZhiHuResult;
import com.example.jonchen.utils.NullStringToEmptyAdapterFactory;
import com.example.jonchen.utils.RxUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
    //public static final String BASE_URL = "http://120.25.225.5:8090/kmhc-modem-restful/services/";
    //public static final String BASE_URL = NetUrl.URL + "/kmhc-modem-restful/services/";
    public static final String KM_BASE_URL = "http://120.25.225.5:8090/kmhc-modem-restful/services/";
    public static final String ZH_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String JKY_URL = "http://218.17.23.74:8089/jkyh_app/";
    private static ApiService apiService;
    private static ApiService spApiService;
    private static RetrofitMethods retrofitMethods = null;


    private RetrofitMethods() {
        Retrofit retrofit = createRetrofit();
        apiService = retrofit.create(ApiService.class);
    }



    private Retrofit createRetrofit() {
       return createRetrofit(KM_BASE_URL);
    }

    private Retrofit createRetrofit(String url) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getHttpClient())
                .build();
    }


    public RetrofitMethods(String url){
        Retrofit retrofit = createRetrofit(url);
        spApiService = retrofit.create(ApiService.class);
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

    public static ApiService getSpApiService() {
        return spApiService;
    }


    public static <T> Subscription hkCommonRequest(final Observable<Result<T>> observable, final Subscriber<T> subscriber) {
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

    public static <T> Subscription hkCommonRequest(final Observable<Result<T>> observable, final Observer<T> observer) {
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

    public static <T> Subscription commonRequest(final Observable<KmResult<T>> observable, final Subscriber<T> subscriber) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<KmResult<T>, T>() {
                    @Override
                    public T call(KmResult<T> tResult) {
                        if (tResult.getErrorCode() != 0) {
                            throw new ApiException(tResult.getErrorCode(), tResult.getMsg());
                        }
                        return tResult.getContent().getList();
                    }
                })
                .subscribe(subscriber);
        RxUtils.get().addList(subscription);
        return subscription;

    }

    public static <T> Subscription commonRequest(final Observable<KmResult<T>> observable, final Observer<T> observer) {
        if (observer instanceof CustomObserver) {
            ((CustomObserver) observer).setObservable(observable);
        }
        Subscription subscription = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<KmResult<T>, T>() {
                    @Override
                    public T call(KmResult<T> tResult) {
                        if (tResult.getErrorCode() != 0) {
                            throw new ApiException(tResult.getErrorCode(), tResult.getMsg());
                        }
                        return tResult.getContent().getList();
                    }
                })
                .subscribe(observer);
        RxUtils.get().addList(subscription);
        return subscription;
    }

    public static <T> Subscription spCommonRequest(final Observable<ZhiHuResult<T>> observable, final Observer<T> observer) {
        if (observer instanceof CustomObserver) {
            ((CustomObserver) observer).setObservable(observable);
        }
        Subscription subscription = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ZhiHuResult<T>, T>() {
                    @Override
                    public T call(ZhiHuResult<T> zhiHuResult) {
                        if (zhiHuResult==null) {
                            throw new ApiException(110, "error zh");
                        }
                        return zhiHuResult.getStories();
                    }
                })
                .subscribe(observer);
        RxUtils.get().addList(subscription);
        return subscription;
    }

    public static <T> Subscription modelCommonRequest(final Observable<T> observable, final Observer<T> observer) {
        if (observer instanceof CustomObserver) {
            ((CustomObserver) observer).setObservable(observable);
        }
        Subscription subscription = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
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

    public static <T> Subscription request(final Observable<ResponseBody> observable, final Observer<T> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBody) {
                        try {
                            String res = responseBody.string();
                            Gson gson = new GsonBuilder()
                                    .serializeNulls()
                                    .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                                    .create();

                            Type genType = observer.getClass().getGenericSuperclass();
                            Class<T> tClass = (Class<T>) ((ParameterizedType) genType).getActualTypeArguments()[0];
                            return gson.fromJson(res, tClass);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribe(observer);
        RxUtils.get().addList(subscription);
        return subscription;
    }


    public static Subscription originRequest(final Observable<ResponseBody> observable,
                                             boolean isUpload, final Observer<ResponseBody> observer) {
        if (observer instanceof CustomObserver) {
            ((CustomObserver) observer).setObservable(observable);
            //((CustomObserver) observer).setUpload(isUpload);
        }
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        RxUtils.get().addList(subscription);
        return subscription;
    }


    public static Subscription download(final Observable<ResponseBody> observable, final Observer<ResponseBody> observer) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(observer);
        //RxUtils.get().addList(subscription);
        return subscription;
    }


    public static Call<ResponseBody> originRequest(Call<ResponseBody> call, RespCallback callBack) {
        call.enqueue(callBack);
        return call;
    }


   /* public static <T> Call<Result<T>> commonRequest(Call<Result<T>> call, BeanRespCallBack<T> callBack) {
        call.enqueue(callBack);
        return call;
    }*/


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
