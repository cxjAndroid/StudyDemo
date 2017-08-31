package com.example.jonchen.model.entity;

import com.example.jonchen.utils.LogUtils;

/**
 * Created by jon on 7/31/17.
 */

public class Child extends Parent{
    private String department;
    public Child() {
        LogUtils.e(" child ");
    }
    public String getValue(){ return name; }

}
