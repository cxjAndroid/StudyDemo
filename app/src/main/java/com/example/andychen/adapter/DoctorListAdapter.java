package com.example.andychen.adapter;

import android.net.Uri;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andychen.model.Doctor2;
import com.example.andychen.myapplication.R;
import com.example.andychen.base.BaseRecyclerAdapter;
import com.example.andychen.base.BaseRecyclerViewHolder;
import com.example.andychen.model.Doctor;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenxujun on 16-9-9.
 */
public class DoctorListAdapter extends BaseRecyclerAdapter<Doctor2> {

    public DoctorListAdapter(List<Doctor2> mData, int layoutId) {
        super(mData, layoutId);
    }

    @Override
    public void RefreshView(BaseRecyclerViewHolder holder, final Doctor2 doctor, int position) {
        TextView text_dep = holder.getView(R.id.text_dep);
        TextView text_doc = holder.getView(R.id.text_doc);
        SimpleDraweeView image_doc = holder.getView(R.id.image_doc);
        RelativeLayout doc_item = holder.getView(R.id.rl_doc_item);

        text_dep.setText(doctor.getHospitalDepartmentName());
        text_doc.setText(doctor.getDoctorName());
        image_doc.setImageURI(Uri.parse(doctor.getAvatarUrl()));
    }

    /*@Override
    public void refreshView(BaseViewHolder holder, Doctor doctor, int p) {
        TextView text_dep = holder.getView(R.id.text_dep);
        TextView text_doc = holder.getView(R.id.text_doc);
        SimpleDraweeView image_doc = holder.getView(R.id.image_doc);

        text_dep.setText(doctor.getDepartmentName());
        text_doc.setText(doctor.getDoctorName());
        image_doc.setImageURI(Uri.parse(doctor.getThumbnail()));
    }*/
}