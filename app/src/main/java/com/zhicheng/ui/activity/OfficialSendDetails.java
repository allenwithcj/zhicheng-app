package com.zhicheng.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.json.OfficialDetailRequest;
import com.zhicheng.ui.adapter.OfficialSendedDetailAdapter;

public class OfficialSendDetails extends BaseActivity implements OfficialView {
    private RecyclerView mRecyclerView;
    private OfficialSendedDetailAdapter mAdapter;
    private OfficialPresenterImpl mOfficialDetail;
    private ViewGroup parentView;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_official_send_details);
        parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialSendedDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mOfficialDetail = new OfficialPresenterImpl(this);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_clear));
    }

    @Override
    protected void initData() {
        OfficialDetailRequest odr = new OfficialDetailRequest();
        OfficialDetailRequest.IqBean odrIB = new OfficialDetailRequest.IqBean();
        OfficialDetailRequest.IqBean.QueryBean odrIBQB = new OfficialDetailRequest.IqBean.QueryBean();
        odrIB.setNamespace("CollaborationDetailsRequest");
        odrIB.setModel("0");
        odrIBQB.setId(getIntent().getStringExtra("detailId"));
        odrIBQB.setRequestType("4");
        odrIB.setQuery(odrIBQB);
        odr.setIq(odrIB);
        Gson gson = new Gson();
        String strEntity = gson.toJson(odr);
        mOfficialDetail.loadDetail(strEntity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("爆料详情");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void refreshData(Object result) {
        if (result instanceof OfficialDetailResponse) {
            BaseApplication.log_say(TAG, ((OfficialDetailResponse) result).getIq().getQuery().getContent());
            mAdapter.setData((OfficialDetailResponse) result);
        }
    }

    @Override
    public void addData(Object result) {

    }
}
