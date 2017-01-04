package com.example.jonchen.presenter;

import android.content.Context;

import com.example.jonchen.model.ShareInfo;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.mvpview.BaseView;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;

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
        RetrofitMethods.hkCommonRequest(getApiService().getShareInfo(params),
                new CustomObserver<List<ShareInfo>>(mContext) {
            @Override
            public void doOnNext(List<ShareInfo> shareInfo) {
                ((BannerView)mView).initBanner(shareInfo);
            }
        });
    }

}