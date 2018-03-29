package com.example.jonchen.pattern.state;

import com.example.jonchen.utils.ToastUtils;

/**
 * Created by jon on 5/7/17.
 */

public class AliPayState implements PayState {
    @Override
    public void pay() {
        ToastUtils.show("Ali_PAY");
    }
}
