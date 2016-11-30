package com.example.andychen.presenter;

import android.content.Context;

import com.example.andychen.model.ShareInfo;
import com.example.andychen.mvpview.BannerView;
import com.example.andychen.mvpview.BaseView;
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
