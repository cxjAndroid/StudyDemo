package com.example.jonchen.model.entity;

import com.example.jonchen.dagger.Say;
import com.example.jonchen.utils.ToastUtils;

/**
 * Created by jon on 3/17/17.
 */

public class Jack implements Say {
    @Override
    public void sayWhat() {
        ToastUtils.show("jack say");
    }
}
