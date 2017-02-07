package com.example.jonchen.presenter;

import com.example.jonchen.event.EventMessage;
import com.example.jonchen.model.BannerModel;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.model.impl.BannerModelImpl;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by chenxujun on 16-9-5.
 */
public class BannerPresenter extends BasePresenter<BannerView> {

    private BannerModel bannerModel;
    public BannerPresenter(BannerView mView) {
        super(mView);
        bannerModel = new BannerModelImpl();
    }

    public void getBannerInfo(){
        //EventBus.getDefault().register(this);
        mView.showLoadingPage();
        //bannerModel.loadBannerInfo();
        new RetrofitMethods(RetrofitMethods.ZH_BASE_URL)
                .request(getSpApiService().rxGetZhiHuNews(), new CustomObserver<DailyBean>(mView) {
                    @Override
                    public void doOnNext(DailyBean dailyBean) {
                        List<DailyBean.TopStoriesBean> topStories = dailyBean.getTop_stories();
                        mView.initBanner(topStories);
                        EventBus.getDefault().postSticky(topStories);
                    }
                });
    }

}
