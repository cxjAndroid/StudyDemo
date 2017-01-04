package com.example.jonchen.mvpview;

import com.example.jonchen.model.WatchInfo;

import java.util.List;

/**
 * Created by chenxujun on 16-12-23.
 */

public interface WatchListView extends BaseView {

    void RefreshWatchList(List<WatchInfo> watchInfoList);
}
