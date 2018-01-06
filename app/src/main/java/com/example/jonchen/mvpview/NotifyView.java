package com.example.jonchen.mvpview;

import android.content.Intent;

/**
 * @author 17041931
 * @since 2017/10/11
 */

public interface NotifyView extends BaseView {
    void showNotification(Intent intent);
}
