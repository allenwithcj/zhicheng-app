package com.zhicheng.api.presenter.impl;


import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.OfficialModelImpl;
import com.zhicheng.api.presenter.OfficialPresenter;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.NoticeResponse;
import com.zhicheng.bean.http.OfficialDealResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;

/**
 * Created by Donson on 2017/1/4.
 */

public class OfficialPresenterImpl implements OfficialPresenter,ApiCompleteListener {

    private OfficialModelImpl mOfficialModelImpl;
    private OfficialView mOfficialView;
    private int start;

    public OfficialPresenterImpl(OfficialView view){
        this.mOfficialView = view;
        this.mOfficialModelImpl = new OfficialModelImpl();
    }

    @Override
    public void loadNoFinish(String j,int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialView.hideProgress();
        }
        mOfficialView.showProgress();
        mOfficialModelImpl.loadOfficial(j,this);
    }

    @Override
    public void loadDetail(String js) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialModelImpl.loadOfficialDetail(js,this);
    }

    @Override
    public void loadDynamic(String dyn,int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialView.hideProgress();
        }
        mOfficialView.showProgress();
        mOfficialModelImpl.loadOfficialDynamic(dyn,this);
    }

    @Override
    public void upDynamic(String dyn, List<String> imgs, String jFile, String GUID) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialView.hideProgress();
        }
        mOfficialView.showProgress();
        mOfficialModelImpl.upOfficialDynamic(dyn,imgs,jFile,GUID,this);
    }

    @Override
    public void upDeal(List<String> imgs, String jFile, String suggest,OfficialDetailResponse officialDetailResponse,String GUID) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialModelImpl.upDeal(imgs,jFile,suggest,GUID,officialDetailResponse,this);
    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void LoadNotice(String n) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialView.hideProgress();
        }
        mOfficialView.showProgress();
        mOfficialModelImpl.loadNotice(n,this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof OfficialResponse){
            if (start == 1){
                mOfficialView.refreshData(result);
            }else {
                mOfficialView.addData(result);
            }
        }
        if (result instanceof OfficialWorkDynamicList){
            mOfficialView.refreshData(result);
        }
        if (result instanceof OfficialDetailResponse){
            mOfficialView.refreshData(result);
        }
        if (result instanceof OfficialDealResponse){
            mOfficialView.refreshData(result);
        }
        if (result instanceof CommonResponse){
            mOfficialView.refreshData(result);
        }
        if (result instanceof NoticeResponse){
            mOfficialView.refreshData(result);
        }
        mOfficialView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        mOfficialView.hideProgress();
        if (msg == null){
            return;
        }
        mOfficialView.showMessage(msg.getMsg());
    }
}
