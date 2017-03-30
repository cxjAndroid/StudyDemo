package com.example.jonchen.model.entity;

import com.example.jonchen.dagger.Say;
import com.example.jonchen.utils.ToastUtils;

/**
 * Created by jon on 3/29/17.
 */

public class Jon implements Say{
    @Override
    public void sayWhat() {
        ToastUtils.show("jon say");
    }
}
