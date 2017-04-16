package com.zhicheng.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.MainPresenterImpl;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.MainView;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.bean.json.NewsCaseTotalRequest;
import com.zhicheng.bean.json.OfficialRequest;
import com.zhicheng.ui.adapter.HomeFragmentAdapter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Donson on 2017/1/2.
 */

public class HomeFragment extends BaseFragment implements MainView, OfficialView,
        SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainPresenterImpl mMainPresenterImpl;
    private HomeFragmentAdapter mHomeAdapter;

    private OfficialPresenterImpl mOfficialPresenterImpl;
    private int start;
    private TextView title_name;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        onRefresh();
        getNofinshCount();
        super.onResume();
    }

    //查询待办事项个数
    private void getNofinshCount() {
        start = 1;
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity, start);
        start++;
    }

    private String createObj(int page) {
        Gson gson = new Gson();
        OfficialRequest officialRequest = new OfficialRequest();
        OfficialRequest.IqBean iqb = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean iqbQB = new OfficialRequest.IqBean.QueryBean();
        iqbQB.setRequestType("0");
        iqbQB.setPage(String.valueOf(page));
        iqbQB.setPerPageNums("10");
        iqbQB.setOrderBy("");
        iqbQB.setOrderType("");
        iqbQB.setIsReadJian("0");
        iqb.setQuery(iqbQB);
        iqb.setModel("0");
        iqb.setNamespace("ListRequest");
        officialRequest.setIq(iqb);
        return gson.toJson(officialRequest);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.recycle_content, container, false);
    }

    @Override
    protected void initEvents() {
        mMainPresenterImpl = new MainPresenterImpl(this);
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        title_name = (TextView) mRootView.findViewById(R.id.title_name);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeFragmentAdapter();
        mRecyclerView.setAdapter(mHomeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        title_name.setText(getResources().getString(R.string.app_name));
    }


    @Override
    protected void initData(boolean isSavedNull) {

    }





    @Override
    public void onRefresh() {
        NewsCaseTotalRequest  mNewsCaseTotalRequest = new NewsCaseTotalRequest();
        NewsCaseTotalRequest.IqBean iqb = new NewsCaseTotalRequest.IqBean();
        NewsCaseTotalRequest.IqBean.QueryBean qb = new NewsCaseTotalRequest.IqBean.QueryBean();
        iqb.setNamespace("NewsCaseTotalRequest");
        iqb.setQuery(qb);
        mNewsCaseTotalRequest.setIq(iqb);
        Gson gson = new Gson();
        String json = gson.toJson(mNewsCaseTotalRequest);
        BaseApplication.log_say(TAG, json);
        mMainPresenterImpl.loadMain(json);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof CommonResponse) {
            if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0) {
                mHomeAdapter.setAdapterData((CommonResponse) result);
            } else {
                showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
            }
        } else if (result instanceof OfficialResponse) {
            if (((OfficialResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                int nofinish_count = ((OfficialResponse) result).getIq().getQuery().getTotalNums();
                mHomeAdapter.setCountDate(nofinish_count);
            }
        }
    }

    @Override
    public void addData(Object result) {

    }


}
