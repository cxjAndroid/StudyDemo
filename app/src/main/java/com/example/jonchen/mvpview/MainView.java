package com.example.jonchen.mvpview;

import com.example.jonchen.model.entity.DailyNewspaper;

import java.util.List;

/**
 * Created by chenxujun on 2016/6/24.
 */
public interface MainView extends BaseView{
    void refreshPage(List<DailyNewspaper> dailyNewspapers);

    void createSlidingMenuView(List<String> list);
}
