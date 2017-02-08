package com.example.jonchen.model;

import com.example.jonchen.model.entity.DailyBean;

/**
 * Created by andychen on 2017/2/7.
 */

public interface BannerModel {
    void loadBannerInfo(ModelCallback<DailyBean> listener);
}
