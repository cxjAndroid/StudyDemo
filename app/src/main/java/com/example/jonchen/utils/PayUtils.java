package com.example.jonchen.utils;

import com.example.jonchen.interfaces.PayState;
import com.example.jonchen.state.AliPayState;

/**
 * Created by jon on 5/7/17.
 */

public class PayUtils {
    private PayState payState = new AliPayState();

    public PayState getPayState() {
        return payState;
    }

    public void setPayState(PayState payState) {
        this.payState = payState;
    }

    public void pay() {
        payState.pay();
    }

}
