package com.example.jonchen.state;

import com.example.jonchen.interfaces.PayState;
import com.example.jonchen.utils.ToastUtils;

/**
 * Created by jon on 5/7/17.
 */

public class WeChatPayState implements PayState {
    @Override
    public void pay() {
        ToastUtils.show("WX_PAY");
    }
}
