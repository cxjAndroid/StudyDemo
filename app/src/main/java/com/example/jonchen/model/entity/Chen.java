package com.example.jonchen.model.entity;

import javax.inject.Inject;

/**
 * Created by jon on 4/10/17.
 */

public class Chen {
    private Xujun xujun;

    @Inject
    public Chen(Xujun xujun) {
        this.xujun = xujun;
    }

    public void showXujunSay(){
        xujun.say();
    }
}
