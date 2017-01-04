package com.example.jonchen.retrofit;

import android.content.Context;

import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.LoadStatusPage;

import rx.Subscriber;

/**
 * Created by chenxujun on 2016/8/22.
 */
public abstract class CustomSubscriber<T> extends Subscriber<T> {
    private Context context;

    public CustomSubscriber(Context context) {
        this.context = context;
    }

    public CustomSubscriber() {
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
        ToastUtils.show(e.getMessage());
    }

    @Override
    public abstract void onNext(T t);

}
