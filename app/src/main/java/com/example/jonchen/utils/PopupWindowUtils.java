package com.example.jonchen.utils;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * @author jon
 * @since 9/7/17
 */

public class PopupWindowUtils {

    private PopupWindow popupWindow;

    public void showPop(View parent, View currentView) {
        popupWindow = new PopupWindow(currentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        //设置popupwindow动画

       /* TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(300);
        currentView.startAnimation(translateAnimation);*/
        popupWindow.showAsDropDown(parent);
    }


}
