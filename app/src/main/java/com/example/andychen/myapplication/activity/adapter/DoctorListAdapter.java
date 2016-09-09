package com.example.andychen.myapplication.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.myapplication.activity.bean.Doctor;
import com.example.andychen.myapplication.activity.mvp_model.BaseActivity;
import com.example.andychen.myapplication.activity.mvp_model.BaseListAdapter;
import com.example.andychen.myapplication.activity.mvp_model.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenxujun on 16-9-9.
 */
public class DoctorListAdapter extends BaseListAdapter<Doctor> {
    public DoctorListAdapter(Context context, List<Doctor> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void refreshView(BaseViewHolder holder, Doctor doctor, int p) {
        TextView text_dep = holder.getView(R.id.text_dep);
        TextView text_doc = holder.getView(R.id.text_doc);
        SimpleDraweeView image_doc = holder.getView(R.id.image_doc);

        text_dep.setText(doctor.getDepartmentName());
        text_doc.setText(doctor.getDoctorName());
        image_doc.setImageURI(Uri.parse(doctor.getThumbnail()));
    }
}
