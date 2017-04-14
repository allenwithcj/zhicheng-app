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
import com.zhicheng.common.URL;
import com.zhicheng.ui.adapter.OfficialFinishDetailAdapter;

import java.util.ArrayList;

import cc.dagger.photopicker.PhotoPicker;

/**
 * Created by IBM on 2017/2/10.
 */

public class OfficialFinishDetail extends BaseActivity implements OfficialView {
    private RecyclerView mRecyclerView;
    private OfficialFinishDetailAdapter mAdapter;
    private OfficialPresenterImpl mOfficialDetail;
    private ViewGroup parentView;
    private ArrayList<String> mShowPhoto;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_selectimg);
        mShowPhoto = new ArrayList<>();
        parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialFinishDetailAdapter();
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
        odrIBQB.setRequestType("1");
        odrIB.setQuery(odrIBQB);
        odr.setIq(odrIB);
        Gson gson = new Gson();
        String strEntity = gson.toJson(odr);
        mOfficialDetail.loadDetail(strEntity);
        mAdapter.setShowPhoto(position -> {
            PhotoPicker.preview()
                    .paths(mShowPhoto)
                    .currentItem(position)
                    .start(OfficialFinishDetail.this);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("事件详情");
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
            if (((OfficialDetailResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                BaseApplication.log_say(TAG, ((OfficialDetailResponse) result).getIq().getQuery().getContent());
                for (int i = 0; i < ((OfficialDetailResponse) result).getIq().getQuery().getAttachments().size(); i++) {
                    mShowPhoto.add(URL.HOST_URL_SERVER_ZHICHENG + ((OfficialDetailResponse) result).getIq().getQuery().getAttachments().get(i).getHref());
                }
                mAdapter.setData((OfficialDetailResponse) result);
            } else {
                showMessage(((OfficialDetailResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void addData(Object result) {

    }


}
