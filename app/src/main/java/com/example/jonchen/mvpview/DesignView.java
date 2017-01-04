package com.example.jonchen.mvpview;

import java.util.List;

/**
 * Created by chenxujun on 16-9-19.
 */
public interface DesignView extends BaseView {
    void initBottomSheet();
    void changeBottomSheetStatus();
    void createBottomSheetDialog(List<String> data);
    void showBottomSheetDialog();
}
