package com.example.jonchen.presenter;

import com.example.jonchen.model.entity.WatchInfo;
import com.example.jonchen.mvpview.WatchListView;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.util.List;

import rx.Observer;

/**
 * Created by chenxujun on 16-12-23.
 */

public class WatchListPresenter extends BasePresenter<WatchListView> {

    public WatchListPresenter(WatchListView mView) {
        super(mView);
    }

    public void getWatchList(String account) {
        //mView.showLoadingPage();
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
                        getView().RefreshWatchList(watchInfoList);
                    }
                });
    }

}
