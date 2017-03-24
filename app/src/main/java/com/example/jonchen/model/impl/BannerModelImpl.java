package com.example.jonchen.model.impl;

import com.example.jonchen.model.BannerModel;
import com.example.jonchen.model.ModelCallback;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.util.List;

import static com.example.jonchen.retrofit.RetrofitMethods.getApiService;

/**
 * Created by andychen on 2017/2/7.
 */

public class BannerModelImpl implements BannerModel {
    @Override
    public void loadBannerInfo(final ModelCallback<DailyBean> listener) {

        RetrofitMethods.modelCommonRequest(getApiService().rxModelGetZhiHuNews()
                , new CustomObserver<DailyBean>() {
                    @Override
                    public void doOnNext(DailyBean dailyBean) {
                        listener.onSuccess(dailyBean);
                    }
                });
    }
}
