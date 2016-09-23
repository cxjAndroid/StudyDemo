package com.example.andychen.utils;


import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.example.andychen.base.BaseApplication;


/**
 * Toast Utils
 *
 * @author weicheng
 */
public class ToastUtils {

    private static Toast toast;

    /**
     * show
     */
    public static void show(int resourcesId) {
        show(BaseApplication.getApplication().getResources().getString(resourcesId));
    }

    /**
     * show
     */
    public static void show(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return;
        }

        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getApplication(), msg + "", Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        /*置屏幕中央*/
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 取消toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    /**
     * 将显示Toast请求发给Handler
     */
    public static void sendToast(Handler h, final String msg) {
        if (h != null && !StringUtils.isEmpty(msg)) {
            h.post(new Runnable() {

                @Override
                public void run() {
                    show(msg);
                }
            });
        }
    }

}
