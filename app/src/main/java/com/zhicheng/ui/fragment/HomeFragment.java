package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.MainPresenterImpl;
import com.zhicheng.api.view.MainView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.adapter.HomeFragmentAdapter;
import com.zhicheng.utils.VpSwipeRefreshLayout;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Donson on 2017/1/2.
 */

public class HomeFragment extends BaseFragment implements MainView
        ,VpSwipeRefreshLayout.OnRefreshListener{

    private VpSwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainPresenterImpl mMainPresenterImpl;
    private HomeFragmentAdapter mHomeAdapter;
//    private Main parentActivity;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onRefresh();
            mHandler.sendEmptyMessageDelayed(0, Constant.TIME);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.sendEmptyMessage(0);

    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.recycle_content,container,false);
    }

    @Override
    protected void initEvents() {
        mMainPresenterImpl = new MainPresenterImpl(this);
        mSwipeRefreshLayout = (VpSwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeFragmentAdapter();
        mRecyclerView.setAdapter(mHomeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    protected void initData(boolean isSavedNull) {
//        if (isSavedNull){
//            onRefresh();
//        }
    }

    @Override
    public void onRefresh() {
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("namespace","NewsCaseTotalRequest");
        map1.put("query",new HashMap<>());
        map.put("iq",map1);
        String json = gson.toJson(map);
        BaseApplication.log_say(TAG,json);
        mMainPresenterImpl.loadMain(json);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
//        if (parentActivity == null){
//            parentActivity = (Main) getActivity();
//        }
//        parentActivity.isTouch(false);
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof CommonResponse){
            if(((CommonResponse) result).getIq().getQuery().getErrorCode() == 0){
                mHomeAdapter.setAdapterData((CommonResponse) result);
            }
//            parentActivity.isTouch(true);
        }
    }




}
