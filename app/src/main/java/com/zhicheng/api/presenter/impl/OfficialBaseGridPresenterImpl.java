package com.zhicheng.api.presenter.impl;

import android.util.Log;

import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.OfficialBaseGridModel;
import com.zhicheng.api.model.impl.OfficialBaseGridModelImpl;
import com.zhicheng.api.presenter.OfficialBaseGridPresenter;
import com.zhicheng.api.view.OfficialBaseGridView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.OfficialBaseGridResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/1/6.
 */

public class OfficialBaseGridPresenterImpl implements OfficialBaseGridPresenter,ApiCompleteListener {

    private OfficialBaseGridView mOfficialBaseGridView;
    private OfficialBaseGridModel mOfficialBaseGridModel;

    public OfficialBaseGridPresenterImpl(OfficialBaseGridView view){
        this.mOfficialBaseGridView = view;
        mOfficialBaseGridModel = new OfficialBaseGridModelImpl();
    }


    @Override
    public void onComplected(Object result) {
        if (result instanceof OfficialBaseGridResponse){
            mOfficialBaseGridView.refreshData(result);
        }
        mOfficialBaseGridView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        mOfficialBaseGridView.hideProgress();
        if (msg == null){
            return;
        }
        mOfficialBaseGridView.showMessage(msg.getMsg());
    }

    @Override
    public void loadOfficialBaseGrid(String street,String communicate,String grid) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialBaseGridView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialBaseGridView.hideProgress();
        }
        mOfficialBaseGridView.showProgress();
        mOfficialBaseGridModel.loadOfficial(street,communicate,grid,this);
    }

    public void loadOfficialBaseGrid(String street,String communicate) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialBaseGridView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialBaseGridView.hideProgress();
        }
        mOfficialBaseGridView.showProgress();
        mOfficialBaseGridModel.loadOfficial(street,communicate,this);
    }

    public void loadOfficialBaseGrid(String street) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mOfficialBaseGridView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialBaseGridView.hideProgress();
        }
        mOfficialBaseGridView.showProgress();
        mOfficialBaseGridModel.loadOfficial(street,this);
    }

    @Override
    public void cancelLoading() {

    }
}
