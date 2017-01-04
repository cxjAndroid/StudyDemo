package com.example.jonchen.adapter;

import android.net.Uri;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonchen.base.BaseRecyclerAdapter;
import com.example.jonchen.base.BaseRecyclerViewHolder;
import com.example.jonchen.model.Doctor;
import com.example.jonchen.R;
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
    public void refreshView(BaseRecyclerViewHolder holder, final Doctor doctor, int position) {
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