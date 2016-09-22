package com.example.andychen.adapter;

import android.net.Uri;
import android.widget.TextView;

import com.example.andychen.myapplication.R;
import com.example.andychen.base.BaseRecyclerAdapter;
import com.example.andychen.base.BaseRecyclerViewHolder;
import com.example.andychen.mvp_model.Doctor;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenxujun on 16-9-9.
 */
public class DoctorListAdapter extends BaseRecyclerAdapter<Doctor> {

    public DoctorListAdapter(List<Doctor> mData, int layoutId) {
        super(mData, layoutId);
    }

    @Override
    public void RefreshView(BaseRecyclerViewHolder holder, Doctor doctor, int position) {
        TextView text_dep = holder.getView(R.id.text_dep);
        TextView text_doc = holder.getView(R.id.text_doc);
        SimpleDraweeView image_doc = holder.getView(R.id.image_doc);

        text_dep.setText(doctor.getDepartmentName());
        text_doc.setText(doctor.getDoctorName());
        image_doc.setImageURI(Uri.parse(doctor.getThumbnail()));
    }
}