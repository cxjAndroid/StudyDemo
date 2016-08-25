package com.example.andychen.myapplication.activity.mvp_view_interface;

import com.example.andychen.myapplication.activity.bean.Doctor;

import java.util.List;

/**
 * Created by andychen on 2016/6/24.
 */
public interface MainView extends BaseView {
    void RefreshDocList(List<Doctor> doctorList);
}
