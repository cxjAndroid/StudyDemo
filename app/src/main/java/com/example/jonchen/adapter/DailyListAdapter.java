package com.example.jonchen.adapter;

import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseRecyclerAdapter;
import com.example.jonchen.base.BaseRecyclerViewHolder;
import com.example.jonchen.model.entity.DailyBean;
import com.example.jonchen.model.entity.DailyNewspaper;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DailyListAdapter extends BaseRecyclerAdapter<DailyBean.StoriesBean> {

    public DailyListAdapter(List<DailyBean.StoriesBean> mData, int layoutId) {
        super(mData, layoutId);
    }

    @Override
    public void refreshView(BaseRecyclerViewHolder holder, DailyBean.StoriesBean dailyNewspaper, int position) {

        SimpleDraweeView newsImage = holder.getView(R.id.newsImage);
        TextView titleTv = holder.getView(R.id.titleTv);
        TextView urlTv = holder.getView(R.id.urlTv);
        newsImage.setImageURI(dailyNewspaper.getImages().get(0));
        titleTv.setText(dailyNewspaper.getTitle());
        urlTv.setText(dailyNewspaper.getGa_prefix());
    }
}
