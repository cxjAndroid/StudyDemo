package com.example.jonchen.presenter;

import com.example.jonchen.R;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.util.List;

/**
 * Created by chenxujun on 16-9-5.
 */
public class BannerPresenter extends BasePresenter<BannerView> {

    //private BannerModel bannerModel;

    public BannerPresenter(BannerView mView) {
        super(mView);
        //bannerModel = new BannerModelImpl();
    }

    public void getBannerInfo() {
        mView.showLoadingPage(R.id.rl_content);
      /*  bannerModel.loadBannerInfo(new ModelCallback<DailyBean>() {
            @Override
            public void onSuccess(DailyBean result) {
                List<DailyBean.TopStoriesBean> topStories = result.getTop_stories();
                mView.initBanner(topStories);
                mView.showSuccessPage();
            }
        });*/
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
