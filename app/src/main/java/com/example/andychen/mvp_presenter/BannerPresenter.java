package com.example.andychen.mvp_presenter;

import android.content.Context;

import com.example.andychen.mvp_model.ShareInfo;
import com.example.andychen.mvp_view.BannerView;
import com.example.andychen.mvp_view.BaseView;
import com.example.andychen.retrofit.CustomObserver;
import com.example.andychen.retrofit.RetrofitMethods;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxujun on 16-9-5.
 */
public class BannerPresenter extends BasePresenter<BaseView> {

    public BannerPresenter(BaseView mView, Context mContext) {
        super(mView, mContext);
    }

    public void getShareInfo(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("BlockType",2);
        RetrofitMethods.commonRequest(getApiService().getShareInfo(params), new CustomObserver<List<ShareInfo>>(mContext) {
            @Override
            public void doOnNext(List<ShareInfo> shareInfo) {
                ((BannerView)mView).initBanner(shareInfo);
            }
        });
    }

}