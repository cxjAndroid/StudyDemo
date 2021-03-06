package com.example.jonchen.presenter;

import com.example.jonchen.mvpview.DesignView;

import java.util.ArrayList;

/**
 * Created by chenxujun on 16-9-20.
 */

public class DesignPresenter extends BasePresenter<DesignView> {

    public DesignPresenter(DesignView mView) {
        super(mView);
    }

    public void getBottomSheetDialogData() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            arrayList.add(String.valueOf(i));
        }
        getView().createBottomSheetDialog(arrayList);
    }

}
