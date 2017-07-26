package com.example.jonchen.presenter;

import com.example.jonchen.retrofit.ApiService;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by chenxujun on 2016/6/24.
 */
public class BasePresenter<T> {
    //public T mView;
    //public Context mContext;
    protected Reference<T> mViewRef;

    public BasePresenter(T mView) {
        attach(mView);
    }

  /*  public BasePresenter(T mView, Context mContext) {
        this.mContext = mContext;
        attach(mView);
    }*/

    public void attach(T view) {
        mViewRef = new WeakReference<T>(view);
        //mView = mViewRef.get();
    }

    protected T getView() {
        return mViewRef.get();
    }

    protected boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }


    public void detach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


    public ApiService getApiService() {
        return RetrofitMethods.getApiService();
    }

}
