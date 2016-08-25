package com.example.andychen.myapplication.activity.retrofit;

import android.content.Context;
import android.view.View;

import com.example.andychen.myapplication.activity.base.BaseActivity;
import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.bean.Result;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.example.andychen.myapplication.activity.view.LoadStatusPage;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by andychen on 2016/8/22.
 */
public abstract class CustomSubscriber<T> extends Subscriber<T> {
    private Context context;

    public CustomSubscriber(Context context) {
        this.context = context;
    }

    public CustomSubscriber() {
    }

    private Observable<Result<T>> observable;

    public Observable<Result<T>> getObservable() {
        return observable;
    }

    public void setObservable(Observable<Result<T>> observable) {
        this.observable = observable;
    }

    @Override
    public void onCompleted() {
        if (context != null) {
            ((BaseActivity) context).showSuccessPage();
        }
    }

    @Override
    public void onError(final Throwable e) {
        if (context != null) {
            ((BaseActivity) context).showErrorPage(e instanceof ApiException ?
                    LoadStatusPage.BIZ_ERROR : LoadStatusPage.SERVICE_ERROR);

        }
        ((BaseActivity) context).getStatusPage().setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitMethods.commonRequest(getObservable(),CustomSubscriber.this);
            }
        });
        ToastUtils.show(e.getMessage());
    }

    @Override
    public abstract void onNext(T t);

}
