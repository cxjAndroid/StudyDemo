package com.example.andychen.myapplication.activity.retrofit;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.activity.MainActivity;
import com.example.andychen.myapplication.activity.utils.LogUtils;
import com.example.andychen.myapplication.activity.utils.ToastUtils;
import com.example.andychen.myapplication.activity.view.EmptyLayout;

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

    @Override
    public void onCompleted() {
        EmptyLayout emptyLayout = (EmptyLayout) ((Activity) context).findViewById(R.id.status_layout);
        if(emptyLayout!=null){
            emptyLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.show(e.getMessage());
    }

    @Override
    public  abstract void onNext(T t);
}
