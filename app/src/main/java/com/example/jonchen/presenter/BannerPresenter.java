package com.example.jonchen.presenter;

import com.example.jonchen.R;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.retrofit.ApiService;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;
import com.example.jonchen.utils.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


       RetrofitMethods.modelCommonRequest(getApiService().rxModelGetZhiHuNews()
                , new CustomObserver<DailyBean>() {
                    @Override
                    public void doOnNext(DailyBean dailyBean) {
                        List<DailyBean.TopStoriesBean> topStories = dailyBean.getTop_stories();
                        mView.initBanner(topStories);
                    }
                });



       /* Gson gson = new GsonBuilder().serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitMethods.ZH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Call<DailyBean> daily = retrofit.create(ApiService.class).getDaily();
        daily.enqueue(new Callback<DailyBean>() {
            @Override
            public void onResponse(Call<DailyBean> call, Response<DailyBean> response) {
                DailyBean body = response.body();
            }

            @Override
            public void onFailure(Call<DailyBean> call, Throwable t) {

            }
        });*/
    }

}
