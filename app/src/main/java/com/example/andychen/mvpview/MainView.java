package com.example.andychen.mvpview;

import com.example.andychen.model.Doctor;
import com.example.andychen.model.Doctor2;

import java.util.List;

/**
 * Created by chenxujun on 2016/6/24.
 */
public interface MainView extends BaseView{
    void refreshDocList(List<Doctor2> doctorList);

    void createSlidingMenuView(List<String> list);
}
