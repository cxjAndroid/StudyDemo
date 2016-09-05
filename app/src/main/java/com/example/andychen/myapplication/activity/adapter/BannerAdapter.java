package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.andychen.myapplication.activity.bean.ShareInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenxujun on 16-9-5.
 */
public class BannerAdapter extends PagerAdapter {

    private List<ShareInfo> shareInfoList;
    private Context context;

    public BannerAdapter(List<ShareInfo> shareInfoList) {
        this.shareInfoList = shareInfoList;
    }

    public BannerAdapter(Context context,List<ShareInfo> shareInfoList) {
        this.shareInfoList = shareInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return shareInfoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        if (object != null && object instanceof SimpleDraweeView) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) object;
            container.removeView(simpleDraweeView);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        if (shareInfoList.size() > 0) {
            final int index = position % shareInfoList.size();
            ShareInfo shareInfo = shareInfoList.get(index);
            simpleDraweeView.setImageURI(Uri.parse(shareInfo.getItemPicURL()));
        }
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }
}
