package com.example.jonchen.model.entity;

import com.example.jonchen.dagger.Say;
import com.example.jonchen.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by jon on 3/17/17.
 */

public class Rose implements Say {

    @Inject
    public Rose() {
    }

    @Override
    public void sayWhat() {
        ToastUtils.show("rose say");
    }
}
