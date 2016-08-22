package com.example.andychen.myapplication.activity.retrofit;

import com.example.andychen.myapplication.activity.utils.ToastUtils;

import rx.Subscriber;

/**
 * Created by andychen on 2016/8/22.
 */
public abstract class CustomSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.show(e.getMessage());
    }

    @Override
    public abstract void onNext(T t);
}
