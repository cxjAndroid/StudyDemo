/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jonchen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jonchen.R;
import com.example.jonchen.utils.StringUtils;


public class LoadStatusPage extends RelativeLayout implements
        View.OnClickListener {

    public static final int NETWORK_ERROR = 1; // 网络错误s
    public static final int NETWORK_LOADING = 2; // 加载中
    public static final int NO_DATA = 3; // 没有数据
    public static final int HIDE_LAYOUT = 4; // 隐藏
    public static final int SERVICE_ERROR = 5; // 服务器错误
    public static final int BIZ_ERROR = 6; // 业务错误 isSuccess = false
    private int mErrorState = NETWORK_LOADING;

    private OnClickListener listener;
    private boolean clickEnable = true;
    private String strNoDataContent = "";

    private TextView tv;
    public ImageView img;
    private ProgressBar animProgress;

    public LoadStatusPage(Context context) {
        super(context);
        init();
    }

    public LoadStatusPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View
                .inflate(getContext(), R.layout.view_status_layout, null);
        img = (ImageView) view.findViewById(R.id.img_error_layout);
        tv = (TextView) view.findViewById(R.id.tv_error_layout);
        animProgress = (ProgressBar) view.findViewById(R.id.animProgress);

        //setBackgroundColor(-1);
        setOnClickListener(this);
        //setStatusType(NETWORK_LOADING);

        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickEnable) {
                    if (listener != null)
                        listener.onClick(v);
                }
            }
        });
        this.addView(view);
    }

    @Override
    public void onClick(View v) {
        if (clickEnable && listener != null) {
            listener.onClick(v);
        }
    }

    public void dismiss() {
        mErrorState = HIDE_LAYOUT;
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return mErrorState;
    }

    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }

    public void setErrorMessage(String msg) {
        tv.setText(msg);
    }

    public void setErrorImag(int imgResource) {
        try {
            img.setImageResource(imgResource);
        } catch (Exception e) {
        }
    }

    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if (StringUtils.isEmpty(strNoDataContent)) {
            tv.setText("no data!!!");
        } else {
            tv.setText(strNoDataContent);
        }
    }

    public void setStatusType(int i) {
        setVisibility(View.VISIBLE);
        switch (i) {
            case NETWORK_ERROR:
                mErrorState = NETWORK_ERROR;
                tv.setText("net is dead");
                //if (SystemTool.isWiFi(getContext())) {
                    img.setBackgroundResource(R.drawable.page_icon_network);
                /*} else {
                    img.setBackgroundResource(R.drawable.pagefailed_bg);
                }*/
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                clickEnable = true;
                break;
            case NETWORK_LOADING:
                mErrorState = NETWORK_LOADING;
                animProgress.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                tv.setText("loading...");
                clickEnable = false;
                break;
            case NO_DATA:
                mErrorState = NO_DATA;
                img.setBackgroundResource(R.drawable.page_icon_empty);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            case HIDE_LAYOUT:
                dismiss();
                break;
            case SERVICE_ERROR:
                img.setBackgroundResource(R.drawable.pagefailed_bg);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                tv.setText("service_error...");
                clickEnable = true;
                break;
            case BIZ_ERROR:
                img.setBackgroundResource(R.drawable.pagefailed_bg);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                tv.setText("error...");
                clickEnable = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE) {
            mErrorState = HIDE_LAYOUT;
        }
        super.setVisibility(visibility);
    }
}
