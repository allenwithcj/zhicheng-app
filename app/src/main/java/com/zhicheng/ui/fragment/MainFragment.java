package com.zhicheng.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.common.URL;
import com.zhicheng.ui.activity.LoginActivity;
import com.zhicheng.ui.activity.VersionUpdateActivity;
import com.zhicheng.ui.adapter.InfoAdapter;
import com.zhicheng.utils.CircleImageView;
import com.zhicheng.utils.common.UIUtils;


/**
 * Created by Donson on 2017/1/2.
 */

public class MainFragment extends BaseFragment{

    private FloatingActionButton mFab;
    private CircleImageView mCircleImageView;
    private RecyclerView mRecyclerView;
    private InfoAdapter mInfoAdapter;
    private TextView mName;
    private TextView mOccupation;
    private TextView userpost;
    private PopupWindow mPopupWindow;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkLogin();
        }
    };

    public static MainFragment newInstance(){
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_info,container,false);
    }

    @Override
    protected void initEvents() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mainFragment.update");
        getActivity().registerReceiver(receiver,filter);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mCircleImageView = (CircleImageView) mRootView.findViewById(R.id.circleImg);
        mFab = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        mName = (TextView) mRootView.findViewById(R.id.name);
        mOccupation = (TextView) mRootView.findViewById(R.id.occupation);
        userpost = (TextView) mRootView.findViewById(R.id.userpost);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mInfoAdapter = new InfoAdapter();
        mRecyclerView.setAdapter(mInfoAdapter);
        mFab.setOnClickListener(v -> {
            DatabaseHelper mDatabase = new DatabaseHelper();
            LocalConfig config = mDatabase.getLocalConfig();
            if (config != null && config.getUserName() != null && config.getPwd() != null){
                //详细个人信息
                mDatabase.deleteLocalConfig();
                SharedPreferences.Editor sp = getContext().getSharedPreferences("cookies",0).edit();
                sp.clear();
                sp.apply();
                mName.setText("未登录");
                mOccupation.setText("无权限");
                userpost.setText("无权限");
                Toast.makeText(getContext(),"账号注销成功",Toast.LENGTH_SHORT).show();
                BaseApplication.checkLogin();
            }else {
                UIUtils.startActivity(new Intent(UIUtils.getContext(), LoginActivity.class));
            }
        });
        checkLogin();
    }

    private void checkLogin(){
        DatabaseHelper mDataBase = new DatabaseHelper();
        LocalConfig lc = mDataBase.getLocalConfig();
        if (lc != null && lc.getUserName() != null && !lc.getUserName().isEmpty()){
            Glide.with(this)
                    .load(URL.HOST_URL_SERVER_ZHICHENG + mDataBase.getLocalConfig().getHeadUrl())
                    .into(mCircleImageView);
            mName.setText(lc.getUserName());
            mOccupation.setText(lc.getDepartment());
            userpost.setText(lc.getUserPost());
        }else{
            mName.setText("未登录");
            mOccupation.setText("无权限");
            userpost.setText("无权限");
        }
    }

    @Override
    protected void initData(boolean isSavedNull) {
        mInfoAdapter.setButtonClick(() ->{
            Intent intent = new Intent(getActivity(),VersionUpdateActivity.class);
            startActivity(intent);

//            View pop_view = LayoutInflater.from(getContext()).inflate(R.layout.activity_version_dialog,null);
//            Button btn_update = (Button)pop_view.findViewById(R.id.btn_update);
//            Button btn_cancel = (Button)pop_view.findViewById(R.id.btn_cancel);
//            if (null != mPopupWindow){
//                mPopupWindow.dismiss();
//            }else {
//                mPopupWindow = new PopupWindow(pop_view, WindowManager.LayoutParams.WRAP_CONTENT,
//                                WindowManager.LayoutParams.WRAP_CONTENT,true);
//                mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//                mPopupWindow.setOutsideTouchable(false);
//                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//                mPopupWindow.setOnDismissListener(() -> {
//                    AnimationUtils.darkBackgroundColor(getActivity().getWindow(),1f);
//                });
//            }
//            mPopupWindow.showAtLocation(mRootView,Gravity.CENTER,0,0);
//            AnimationUtils.darkBackgroundColor(getActivity().getWindow(),0.4f);
//
//            btn_update.setOnClickListener(view -> {
//
//            });
//
//            btn_cancel.setOnClickListener(view -> {
//                if(mPopupWindow.isShowing()){
//                    mPopupWindow.dismiss();
//                }
//            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
