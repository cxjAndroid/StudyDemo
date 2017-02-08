package com.example.jonchen.model;

import com.example.jonchen.mvpview.BaseView;

/**
 * Created by Administrator on 2015/2/7.
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 */
public abstract class ModelCallback<T> {
    /**
     * 成功时回调
     *
     * @param result
     */
    public abstract void onSuccess(T result);



    /**
     * 失败时回调
     */
    public void onError(){

    }

}
