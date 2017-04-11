package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.MainModel;
import com.zhicheng.api.model.impl.MainModelImpl;
import com.zhicheng.api.presenter.MainPresenter;
import com.zhicheng.api.view.MainView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2017/1/2.
 */

public class MainPresenterImpl implements MainPresenter, ApiCompleteListener {
    private MainModel mMainModel;
    private MainView mMainView;

    public MainPresenterImpl(MainView view) {
        this.mMainView = view;
        this.mMainModel = new MainModelImpl();
    }

    @Override
    public void loadMain(String json) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mMainView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mMainView.hideProgress();
        }
        mMainView.showProgress();
        mMainModel.loadMainList(json, this);
    }

    @Override
    public void loadPersonal(String personal) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mMainView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mMainView.hideProgress();
        }
        mMainView.showProgress();
        mMainModel.loadPersonal(personal, this);
    }

    @Override
    public void cancelLoading() {
        mMainModel.cancelLoading();
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse) {
            mMainView.refreshData(result);
        }
        mMainView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        mMainView.hideProgress();
        if (msg == null) {
            return;
        }
        mMainView.showMessage(msg.getMsg());
    }
}
