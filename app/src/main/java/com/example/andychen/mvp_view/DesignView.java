package com.example.andychen.mvp_view;

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
