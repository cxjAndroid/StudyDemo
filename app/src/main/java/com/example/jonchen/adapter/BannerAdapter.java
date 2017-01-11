package com.example.jonchen.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jonchen.R;
import com.example.jonchen.model.DailyBean;
import com.example.jonchen.model.ShareInfo;
import com.example.jonchen.utils.FrescoUtils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenxujun on 16-9-5.
 */
public class BannerAdapter extends PagerAdapter {

    private List<DailyBean.TopStoriesBean> storiesBeanList;
    private Context context;

    public BannerAdapter(List<DailyBean.TopStoriesBean> shareInfoList) {
        this.storiesBeanList = shareInfoList;

    }

    public BannerAdapter(Context context, List<DailyBean.TopStoriesBean> shareInfoList) {
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
            SimpleDraweeView bannerLayout = (SimpleDraweeView) object;
            container.removeView(bannerLayout);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setHierarchy(FrescoUtils.getHierarchy(context,
                R.drawable.banner_default2,
                R.drawable.banner_default2, ScalingUtils.ScaleType.FIT_XY));

        if (storiesBeanList.size() > 0) {
            final int index = position % storiesBeanList.size();
            DailyBean.TopStoriesBean storiesBean = storiesBeanList.get(index);
            simpleDraweeView.setImageURI(Uri.parse(storiesBean.getImage()));
        } else {
            simpleDraweeView.setBackgroundResource(R.drawable.banner_default2);
        }
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }


}
