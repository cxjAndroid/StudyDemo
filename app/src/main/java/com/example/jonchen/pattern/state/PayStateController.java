package com.example.jonchen.pattern.state;

/**
 * Created by jon on 5/7/17.
 */

public class PayStateController {
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
