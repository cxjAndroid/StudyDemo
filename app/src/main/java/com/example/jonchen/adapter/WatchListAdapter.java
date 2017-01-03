package com.example.jonchen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonchen.activity.VoiceActivity;
import com.example.jonchen.base.BaseRecyclerAdapter;
import com.example.jonchen.base.BaseRecyclerViewHolder;
import com.example.jonchen.event.EventMessage;
import com.example.jonchen.model.ChatMessage;
import com.example.jonchen.model.WatchInfo;
import com.example.jonchen.R;
import com.example.jonchen.utils.IntentUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by chenxujun on 16-12-23.
 */

public class WatchListAdapter extends BaseRecyclerAdapter<WatchInfo> implements View.OnClickListener {
    private Context context;

    public WatchListAdapter(List<WatchInfo> mData, int layoutId) {
        super(mData, layoutId);
    }

    public WatchListAdapter(Context context, List<WatchInfo> mData, int layoutId) {
        super(mData, layoutId);
        this.context = context;
    }

    @Override
    public void refreshView(BaseRecyclerViewHolder holder, WatchInfo watchInfo, int position) {
        TextView nameTV = holder.getView(R.id.nameTV);
        TextView phoneTV = holder.getView(R.id.phoneTV);
        RelativeLayout itemRL = holder.getView(R.id.itemRL);
        nameTV.setText(watchInfo.getRealName());
        phoneTV.setText(watchInfo.getPhone());
        itemRL.setOnClickListener(this);
        itemRL.setTag(watchInfo);
    }

    @Override
    public void onClick(View v) {
        WatchInfo watchInfo = (WatchInfo) v.getTag();
        IntentUtils.startActivityLeftIn((Activity) context, VoiceActivity.class);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setWatchId(watchInfo.getImei());
        chatMessage.setUserId("13691993691");
        chatMessage.setWatchName(watchInfo.getRealName());
        EventBus.getDefault().postSticky(new EventMessage<>(chatMessage));
    }
}
