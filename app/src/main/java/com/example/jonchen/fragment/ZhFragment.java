package com.example.jonchen.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jonchen.R;
import com.example.jonchen.adapter.DailyListAdapter;
import com.example.jonchen.adapter.MenuAdapter;
import com.example.jonchen.base.BaseActivity;
import com.example.jonchen.base.BaseFragment;
import com.example.jonchen.model.entity.DailyNewspaper;
import com.example.jonchen.mvpview.MainView;
import com.example.jonchen.presenter.MainPresenter;
import com.example.jonchen.utils.AnimatorUtil;
import com.example.jonchen.utils.IntentUtils;
import com.example.jonchen.utils.LogUtils;
import com.example.jonchen.utils.ToastUtils;
import com.example.jonchen.view.MyRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.BindView;

public class ZhFragment extends BaseFragment<MainPresenter> implements MainView, MenuAdapter.MenuItemCallBack {
  /*  @BindView(R.id.btn)
    Button btn;/
    @BindView(R.id.tv)
    TextView tv;*/
    @BindView(R.id.recyclerView)
    MyRecyclerView recyclerView;
    /*@BindView(R.id.slidingLayout)
    SlidingPaneLayout slidingPaneLayout;*/
  /*  @BindView(R.id.menuList)
    MyListView menuList;*/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.floatBtn)
    FloatingActionButton floatBtn;
    @BindView(R.id.mainRL)
    CoordinatorLayout mainRL;
   /* @BindView(R.id.btn1)
    Button btn1;*/
    private DailyListAdapter dailyListAdapter;
    private final int CAMERA_REQUEST_CODE = 1;
    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, R.menu.menu);
        AnimatorUtil.scaleHide(floatBtn, 0, null);
    }


    @Override
    protected void initData() {

        MobclickAgent.openActivityDurationTrack(false);

        if (dailyListAdapter == null) {
            mPresenter.getDailyInfo();
        } else {
            LogUtils.e("ZhFragment"+"----"+"setAdapter");
            recyclerView.setAdapter(dailyListAdapter);
        }
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (baseActivity.getSupportActionBar() != null) {
            if (baseActivity.menuLayout != 0) {
                menu.clear();
                baseActivity.getMenuInflater().inflate(baseActivity.menuLayout, menu);
            } else {
                baseActivity.getSupportActionBar().setHomeButtonEnabled(true);
                baseActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void requestPermission(){

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED) {
               /* if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("申请相机权限")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{
                                            Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                                }
                            }).show();
                } else {*/
                    //申请相机权限
                    requestPermissions(
                            new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                //}
            }else{
                IntentUtils.startActivityForResult(baseActivity, CaptureActivity.class, 0);
            }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                IntentUtils.startActivityForResult(baseActivity, CaptureActivity.class, 0);
            } else {
                //用户勾选了不再询问
                //提示用户手动打开权限
                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                    ToastUtils.show(getString(R.string.permission_refuse));
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_capture:
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    requestPermission();
                }else{
                    IntentUtils.startActivityForResult(baseActivity, CaptureActivity.class, 0);
                }
                break;
            case R.id.action_about_us:
                ToastUtils.show("action_about_us");
                break;
            case R.id.action_feedback:
                ToastUtils.show("action_feedback");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void createSlidingMenuView(List<String> data) {
        /*MenuAdapter adapter = new MenuAdapter(data, android.R.layout.simple_list_item_1, slidingPaneLayout);
        adapter.setMenuItemCallBack(this);
        menuList.setAdapter(adapter);*/
    }

    @Override
    public void menuItemOnClick(String s) {
       /* mPresenter.getDailyInfo();
        slidingPaneLayout.closePane();*/
    }

    @Override
    public void refreshPage(List<DailyNewspaper> dailyNewspapers) {
        dailyListAdapter = new DailyListAdapter(dailyNewspapers, R.layout.item_daily);
        recyclerView.setAdapter(dailyListAdapter);
    }

   /* @OnClick({R.id.btn, R.id.btn1, R.id.btn2, R.id.floatBtn})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn:
                IntentUtils.startActivity(this, BannerFragment.class);
                break;
            case R.id.btn1:
                IntentUtils.startActivity(this, DrawViewFragment.class);
                EventBus.getDefault().postSticky(new EventMessage<>("from mainPage"));
                break;
            case R.id.btn2:
                //IntentUtils.startActivity(this, DrawViewFragment.class);
                IntentUtils.startActivity(this, WatchListFragment.class);
                break;
            case R.id.floatBtn:
                recyclerView.getLayoutManager().scrollToPosition(0);
                break;
        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            //tv.setText(extras.getString("result"));
            ToastUtils.show(extras.getString("result"));
        }
    }
}
