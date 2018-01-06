package com.example.jonchen.presenter;

import android.content.Intent;

import com.example.jonchen.base.BaseApplication;
import com.example.jonchen.mvpview.NotifyView;
import com.example.jonchen.service.AlarmService;

/**
 * @author 17041931
 * @since 2017/10/11
 */

public class NotifyPresenter extends BasePresenter<NotifyView> {
    public NotifyPresenter(NotifyView mView) {
        super(mView);
    }

    public void createNotifyIntent() {
        long start = System.currentTimeMillis();
        long notifyTime = start + 10 * 1000;

        Intent intent = new Intent(BaseApplication.getApplication(), AlarmService.class);
        intent.putExtra("notifyTime", notifyTime);
        NotifyView view = getView();
        if (view != null) view.showNotification(intent);
    }
}
