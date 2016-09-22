package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.mvp_model.ShareInfo;
import com.example.andychen.myapplication.activity.utils.FrescoUtils;
import com.facebook.drawee.drawable.ScalingUtils;
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

    public BannerAdapter(Context context, List<ShareInfo> shareInfoList) {
        this(shareInfoList);
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
        simpleDraweeView.setHierarchy(FrescoUtils.getHierarchy(context,
                R.drawable.banner_default2,
                R.drawable.banner_default2, ScalingUtils.ScaleType.FIT_XY));

        if (shareInfoList.size() > 0) {
            final int index = position % shareInfoList.size();
            ShareInfo shareInfo = shareInfoList.get(index);
            simpleDraweeView.setImageURI(Uri.parse(shareInfo.getItemPicURL()));
        } else {
            simpleDraweeView.setBackgroundResource(R.drawable.banner_default2);
        }
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }
}
