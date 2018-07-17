package com.example.jonchen.model.entity;

import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.ToastUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jon on 4/1/17.
 */

public class Coder implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        LogUtils.e("coder receiver" +arg);
        //ToastUtils.show("coder receiver"+arg);
    }
}
