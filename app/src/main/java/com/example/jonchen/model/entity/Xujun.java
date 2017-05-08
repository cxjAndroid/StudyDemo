package com.example.jonchen.model.entity;

import com.example.jonchen.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by jon on 4/10/17.
 */

public class Xujun {

    @Inject
    public Xujun() {

    }

    void say(){
        ToastUtils.show("xujun say");
    }
}
