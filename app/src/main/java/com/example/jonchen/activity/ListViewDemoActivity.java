package com.example.jonchen.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.model.entity.People;
import com.example.jonchen.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 17041931
 * @since 2018/1/12
 */

public class ListViewDemoActivity extends BaseActivity {
    @BindView(R.id.demoListView)
    MyListView demoListView;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_listview;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        ArrayList<People> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            People people = new People();
            people.setAge(i);
            people.setName("dataName" + i);
            people.setEmailAddress("address" + i);
            data.add(people);
        }


    }



    class RecycleAdapter extends RecyclerView.Adapter{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }



    class MyAdapter<People> extends BaseAdapter {
        private List<People> data;
        private ViewHolder viewHolder;

        public MyAdapter(List<People> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(ListViewDemoActivity.this, R.layout.item_watch, null);
                viewHolder = new ViewHolder();
                viewHolder.nameTV = convertView.findViewById(R.id.nameTV);
                viewHolder.phoneTV = convertView.findViewById(R.id.phoneTV);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }

    class ViewHolder {
        private TextView nameTV;
        private TextView phoneTV;
    }


}
