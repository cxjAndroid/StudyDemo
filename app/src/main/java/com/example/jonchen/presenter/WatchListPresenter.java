package com.example.jonchen.presenter;

import android.content.Context;

import com.example.jonchen.model.WatchInfo;
import com.example.jonchen.mvpview.WatchListView;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.util.List;

import rx.Observer;

/**
 * Created by chenxujun on 16-12-23.
 */

public class WatchListPresenter extends BasePresenter<WatchListView> {

    public WatchListPresenter(WatchListView mView, Context mContext) {
        super(mView, mContext);
    }

    public void getWatchList(String account) {
        RetrofitMethods.commonRequest(getApiService().getWatchList(account),
                new Observer<List<WatchInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<WatchInfo> watchInfoList) {
                        mView.RefreshWatchList(watchInfoList);
                    }
                });
    }

}
