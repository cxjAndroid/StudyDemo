package com.example.andychen.myapplication.activity.mvp_view;

import com.example.andychen.myapplication.activity.bean.ShareInfo;

import java.util.List;

/**
 * Created by andychen on 2016/8/30.
 */
public interface BannerView extends BaseView  {
    void adjustAdvLayout();

    void initBanner(List<ShareInfo> shareInfoList);

    void syncDrawLayout();
}
