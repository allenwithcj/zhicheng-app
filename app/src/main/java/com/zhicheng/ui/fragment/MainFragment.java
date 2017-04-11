package com.zhicheng.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.api.presenter.impl.CheckVersionPresenterImpl;
import com.zhicheng.api.view.CheckVerisonView;
import com.zhicheng.bean.http.VersionResponse;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;
import com.zhicheng.ui.activity.LoginActivity;
import com.zhicheng.ui.adapter.InfoAdapter;
import com.zhicheng.utils.CircleImageView;
import com.zhicheng.utils.common.UIUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Donson on 2017/1/2.
 */

public class MainFragment extends BaseFragment implements CheckVerisonView {

    private CircleImageView mCircleImageView;
    private RecyclerView mRecyclerView;
    private InfoAdapter mInfoAdapter;
    private TextView mName;
    private TextView mOccupation;
    private TextView userpost;
    private CheckVersionPresenterImpl mCheckVersionPresenterImpl;
    private VersionResponse.DataBean mDataBean;
    private String[] str = {"版本:", "更新内容:"};
    private TextView title_name;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkLogin();
        }
    };

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_info, container, false);
    }

    @Override
    protected void initEvents() {
        mCheckVersionPresenterImpl = new CheckVersionPresenterImpl(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mainFragment.update");
        getActivity().registerReceiver(receiver, filter);
        title_name = (TextView)mRootView.findViewById(R.id.title_name);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mCircleImageView = (CircleImageView) mRootView.findViewById(R.id.circleImg);
        mName = (TextView) mRootView.findViewById(R.id.name);
        mOccupation = (TextView) mRootView.findViewById(R.id.occupation);
        userpost = (TextView) mRootView.findViewById(R.id.userpost);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mInfoAdapter = new InfoAdapter();
        mRecyclerView.setAdapter(mInfoAdapter);
        title_name.setText(getResources().getString(R.string.mine));
        checkLogin();
    }

    private void checkLogin() {
        DatabaseHelper mDataBase = new DatabaseHelper();
        LocalConfig lc = mDataBase.getLocalConfig();
        if (lc != null && lc.getUserName() != null && !lc.getUserName().isEmpty()) {
            Glide.with(this)
                    .load(URL.HOST_URL_SERVER_ZHICHENG + mDataBase.getLocalConfig().getHeadUrl())
                    .into(mCircleImageView);
            mName.setText(lc.getUserName());
            mOccupation.setText(lc.getDepartment()+":");
            userpost.setText(lc.getUserPost());
        } else {
            mName.setText("未登录");
            mOccupation.setText("无权限");
            userpost.setText("无权限");
        }
    }

    @Override
    protected void initData(boolean isSavedNull) {
        Map<String, String> map = new HashMap<>();
        map.put("aId", Constant.aId);
        map.put("_api_key", Constant._api_key);
        mCheckVersionPresenterImpl.getApps(map);

        mInfoAdapter.setButtonClick(() -> {
            PgyUpdateManager.register(getActivity(), getString(R.string.file_provider), new UpdateManagerListener() {
                @Override
                public void onNoUpdateAvailable() {
                    Toast.makeText(getActivity(), "当前版本:" + getCurrentVersion().versionName, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onUpdateAvailable(String s) {
                    // 将新版本信息封装到AppBean中
                    final AppBean appBean = getAppBeanFromString(s);
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_version_update, null);
                    TextView update_version = (TextView) view.findViewById(R.id.update_version);
                    TextView update_content = (TextView) view.findViewById(R.id.update_content);
                    if (mDataBean != null) {
                        update_version.setText(str[0] + mDataBean.getAppVersion());
                        update_content.setText(str[1] + mDataBean.getAppUpdateDescription());
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(view);
                    builder.setPositiveButton(getActivity().getResources().getString(R.string.download), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startDownloadTask(getActivity(),
                                    appBean.getDownloadURL());
                        }
                    }).setNegativeButton(getActivity().getResources().getString(R.string.update_cancel), null);
                    builder.show();

                }
            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void checkResponse(Object result) {
        if (result instanceof VersionResponse) {
            if (((VersionResponse) result).getCode() == 0) {
                if (((VersionResponse) result).getData() != null) {
                    mDataBean = ((VersionResponse) result).getData();
                    if (Integer.parseInt(((VersionResponse) result).getData().getAppVersionNo())
                            > getCurrentVersion().versionCode) {
                        mInfoAdapter.setVersion(true);
                    } else {
                        mInfoAdapter.setVersion(false);
                    }
                }
            }
        }

    }

    private PackageInfo getCurrentVersion() {
        PackageInfo pi = null;
        PackageManager pm = UIUtils.getContext().getPackageManager();
        try {
            pi = pm.getPackageInfo(UIUtils.getContext().getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }
}
