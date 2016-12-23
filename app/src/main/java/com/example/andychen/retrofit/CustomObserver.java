package com.example.andychen.retrofit;

import android.content.Context;
import android.view.View;

import com.example.andychen.base.BaseActivity;
import com.example.andychen.model.Result;
import com.example.andychen.utils.ToastUtils;
import com.example.andychen.view.LoadStatusPage;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by chenxujun on 2016/8/26.
 */
public abstract class CustomObserver<T> implements Observer<T> {

    private Context context;
    private Observable<KmResult<T>> observable;
    private Subscription subscription;

    public CustomObserver() {
    }

    public CustomObserver(Context context) {
        this.context = context;
    }


    public Observable<KmResult<T>> getObservable() {
        return observable;
    }

    public void setObservable(Observable<KmResult<T>> observable) {
        this.observable = observable;
    }

    public abstract void doOnNext(T t);

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

            ((BaseActivity) context).getStatusPage().setOnLayoutClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getObservable() != null) {
                        ((BaseActivity) context).showLoadingPage();
                        RetrofitMethods.commonRequest(getObservable(), CustomObserver.this);
                    }
                }
            });
        }

        ToastUtils.show(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        doOnNext(t);
    }
}
