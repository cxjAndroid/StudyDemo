package com.example.jonchen.presenter;

import com.example.jonchen.model.DailyBean;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.util.List;

/**
 * Created by chenxujun on 16-9-5.
 */
public class BannerPresenter extends BasePresenter<BannerView> {

    public BannerPresenter(BannerView mView) {
        super(mView);
    }

    public void getBannerInfo(){
        mView.showLoadingPage();
        new RetrofitMethods(RetrofitMethods.ZH_BASE_URL)
                .request(getSpApiService().rxGetZhiHuNews(), new CustomObserver<DailyBean>(mView) {
                    @Override
                    public void doOnNext(DailyBean dailyBean) {
                        List<DailyBean.TopStoriesBean> topStories = dailyBean.getTop_stories();
                        mView.initBanner(topStories);
                    }
                });
    }

}
