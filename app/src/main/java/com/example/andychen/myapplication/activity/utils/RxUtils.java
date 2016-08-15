package com.example.andychen.myapplication.activity.utils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by andychen on 2016/8/4.
 */
public class RxUtils {

    private List<Subscription> subscriptionList = new ArrayList<>();
    private static RxUtils rxUtils = null;

    private RxUtils() {

    }

    public static RxUtils get() {
        if (rxUtils == null) {
            synchronized (RxUtils.class) {
                if (rxUtils == null) {
                    rxUtils = new RxUtils();
                }
            }
        }
        return rxUtils;
    }


    public void addList(Subscription subscription) {
        if (subscription != null) {
            subscriptionList.add(subscription);
        }
    }

    public void unSubscribe() {
        if (subscriptionList.size() > 0) {
            for (Subscription subscription : subscriptionList) {
                if (subscription != null && !subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
            subscriptionList.clear();
        }
    }

}
