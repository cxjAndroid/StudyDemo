package com.example.andychen.myapplication.activity.mvp_view;

import com.example.andychen.myapplication.activity.bean.Doctor;

import java.util.List;

/**
 * Created by andychen on 2016/6/24.
 */
public interface MainView extends BaseView{
    void refreshDocList(List<Doctor> doctorList);

    void createSlidingMenuView(List<String> list);
}
