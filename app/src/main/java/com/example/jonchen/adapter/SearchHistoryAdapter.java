package com.example.jonchen.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.activity.SearchActivity;
import com.example.jonchen.base.BaseRecyclerAdapter;
import com.example.jonchen.base.BaseRecyclerViewHolder;
import com.example.jonchen.presenter.SearchPresenter;

import java.util.List;

/**
 * @author 17041931
 * @since 2017/10/19
 */

public class SearchHistoryAdapter extends BaseRecyclerAdapter<String> {

    private Context context;

    public SearchHistoryAdapter(List<String> mData, int layoutId, Context context) {
        super(mData, layoutId);
        this.context = context;
    }

    @Override
    public void refreshView(BaseRecyclerViewHolder holder, String s, int position) {
        final TextView historyItem = holder.getView(R.id.text_item);
        RelativeLayout searchContent = holder.getView(R.id.rl_search_content);
        historyItem.setText(s);
        searchContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //builder.setTitle("Material Design Dialog");
                builder.setMessage("确认删除该条搜索历史？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SearchActivity activity = (SearchActivity) SearchHistoryAdapter.this.context;
                        SearchPresenter presenter = activity.getPresenter();
                        presenter.deleteSingleHistory(historyItem.getText().toString());
                        presenter.getSearchHistory();
                    }
                });
                builder.show();
                return false;
            }
        });
    }
}
