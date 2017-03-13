package com.example.jonchen.presenter;

import com.example.jonchen.R;
import com.example.jonchen.mvpview.BannerView;

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
        //mView.showLoadingPage(R.id.contentRl);
      /*  bannerModel.loadBannerInfo(new ModelCallback<DailyBean>() {
            @Override
            public void onSuccess(DailyBean result) {
                List<DailyBean.TopStoriesBean> topStories = result.getTop_stories();
                mView.initBanner(topStories);
                mView.showSuccessPage();
            }
        });*/
      /*  new RetrofitMethods(RetrofitMethods.ZH_BASE_URL)
                .request(getSpApiService().rxGetZhiHuNews(), new CustomObserver<DailyBean>(mView) {
                    @Override
                    public void doOnNext(DailyBean dailyBean) {
                        List<DailyBean.TopStoriesBean> topStories = dailyBean.getTop_stories();
                        mView.initBanner(topStories);
                    }
                });*/
    }

}
