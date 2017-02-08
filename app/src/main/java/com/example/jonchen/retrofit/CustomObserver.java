package com.example.jonchen.retrofit;

import android.view.View;

import com.example.jonchen.model.ModelCallback;
import com.example.jonchen.model.entity.KmResult;
import com.example.jonchen.mvpview.BaseView;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.LoadStatusPage;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by chenxujun on 2016/8/26.
 */
public abstract class CustomObserver<T> implements Observer<T> {

    //private Context context;
    private Observable<KmResult<T>> observable;
    private Subscription subscription;
    private BaseView mView;
    private ModelCallback<T> modelCallback;

    public CustomObserver() {

    }


    public CustomObserver(BaseView mView) {
        this.mView = mView;
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
        if (mView != null) {
            mView.showSuccessPage();
        }
    }

    @Override
    public void onError(final Throwable e) {
        if (mView != null) {
            mView.showErrorPage(e instanceof ApiException ?
                    LoadStatusPage.BIZ_ERROR : LoadStatusPage.SERVICE_ERROR);

            mView.getStatusPage().setOnLayoutClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getObservable() != null) {
                        mView.showLoadingPage();
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
