package com.example.andychen.myapplication.activity.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.adapter.MenuAdapter;
import com.example.andychen.myapplication.activity.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxujun on 16-9-8.
 */
public class MenuFragment extends BaseFragment {
    @BindView(R.id.menu_list)
    ListView menu_list;
    private View view;
    private MenuAdapter myAdapter;
    private ArrayList<String> arrayList;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_menu, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(String.valueOf(i));
        }

        ButterKnife.bind(this, view);

        myAdapter = new MenuAdapter(getActivity(), arrayList, R.layout.item_doctor);
        menu_list.setAdapter(myAdapter);

    }

}
