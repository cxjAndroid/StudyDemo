package com.example.andychen.mvpview;

import com.example.andychen.model.Doctor;

import java.util.List;

/**
 * Created by chenxujun on 2016/6/24.
 */
public interface MainView extends BaseView{
    void refreshDocList(List<Doctor> doctorList);

    void createSlidingMenuView(List<String> list);
}
