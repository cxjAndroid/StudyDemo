package com.example.jonchen.model.impl;

import com.example.jonchen.model.BannerModel;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by andychen on 2017/2/7.
 */

public class BannerModelImpl implements BannerModel {
    @Override
    public void loadBannerInfo() {
        new RetrofitMethods(RetrofitMethods.ZH_BASE_URL)
                .request(RetrofitMethods.getSpApiService().rxGetZhiHuNews(), new CustomObserver<DailyBean>() {
                    @Override
                    public void doOnNext(DailyBean dailyBean) {
                        List<DailyBean.TopStoriesBean> topStories = dailyBean.getTop_stories();
                        //mView.initBanner(topStories);
                        //EventBus.getDefault().postSticky(topStories);
                    }
                });
    }
}
