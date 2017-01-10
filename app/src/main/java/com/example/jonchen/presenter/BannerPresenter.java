package com.example.jonchen.presenter;

import android.content.Context;

import com.example.jonchen.model.ShareInfo;
import com.example.jonchen.mvpview.BannerView;
import com.example.jonchen.mvpview.BaseView;
import com.example.jonchen.retrofit.CustomObserver;
import com.example.jonchen.retrofit.RetrofitMethods;

import java.util.ArrayList;
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
        /*RetrofitMethods.hkCommonRequest(getApiService().getShareInfo(params),
                new CustomObserver<List<ShareInfo>>(mContext) {
            @Override
            public void doOnNext(List<ShareInfo> shareInfo) {
                ((BannerView)mView).initBanner(shareInfo);
            }
        });*/

        String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
                "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
                "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
                "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
                "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
        ArrayList<ShareInfo> shareInfoArrayList = new ArrayList<>();
        for(String url :imageUrls){
            ShareInfo shareInfo = new ShareInfo();
            shareInfo.setItemPicURL(url);
            shareInfoArrayList.add(shareInfo);
        }
        ((BannerView)mView).initBanner(shareInfoArrayList);
    }

}
