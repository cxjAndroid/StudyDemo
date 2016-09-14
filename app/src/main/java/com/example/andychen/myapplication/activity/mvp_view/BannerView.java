package com.example.andychen.myapplication.activity.mvp_view;

import com.example.andychen.myapplication.activity.bean.ShareInfo;

import java.util.List;

/**
 * Created by andychen on 2016/8/30.
 */
public interface BannerView extends BaseView  {

    /**
     * 适配轮播图布局比例
     */
    void adjustAdvLayout();

    /**
     * 初始化广告轮播图
     * @param shareInfoList
     */
    void initBanner(List<ShareInfo> shareInfoList);

    /**
     * 关联侧滑菜单
     */
    void syncDrawLayout();
}
