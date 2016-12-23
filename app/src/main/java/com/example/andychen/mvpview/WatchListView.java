package com.example.andychen.mvpview;

import com.example.andychen.model.WatchInfo;

import java.util.List;

/**
 * Created by chenxujun on 16-12-23.
 */

public interface WatchListView extends BaseView {

    void RefreshWatchList(List<WatchInfo> watchInfoList);
}
