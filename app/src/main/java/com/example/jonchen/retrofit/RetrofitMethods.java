package com.example.jonchen.retrofit;

import com.example.jonchen.model.entity.KmResult;
import com.example.jonchen.utils.RxUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
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

    private static ApiService apiService;
    private static RetrofitMethods retrofitMethods = null;


    private RetrofitMethods() {
        Retrofit retrofit = RetrofitSetting.buildRetrofit();
        apiService = retrofit.create(ApiService.class);
    }

    private static RetrofitMethods getInstance() {
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


    public static <T> Subscription modelCommonRequest(final Observable<T> observable, final Observer<T> observer) {
        if (observer instanceof CustomObserver) {
            ((CustomObserver) observer).setObservable(observable);
        }
        Subscription subscription = observable.subscribeOn(Schedulers.io())
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

 /*   public static <T> Subscription request(final Observable<ResponseBody> observable, final Observer<T> observer) {
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

    public static <T> Subscription request(final Observable<ResponseBody> observable, final ModelCallback<T> listener) {
        return request(observable, new CustomObserver<T>() {
            @Override
            public void doOnNext(T t) {
                listener.onSuccess(t);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                listener.onError();
            }
        });
    }*/


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


    public static <T> Call<T> originRequest(Call<T> call, RespCallback<T> callBack) {
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
