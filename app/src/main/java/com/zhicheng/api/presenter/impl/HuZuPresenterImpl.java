package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.HuZuModelImpl;
import com.zhicheng.api.presenter.HuZuPresenter;
import com.zhicheng.api.view.HuZuView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.PersonMsgMaResponse;
import com.zhicheng.bean.http.PersonMsgResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by lwl on 2017/4/16.
 */

public class HuZuPresenterImpl implements HuZuPresenter,ApiCompleteListener {
    private HuZuView mHuZuView;
    private HuZuModelImpl mHuZuModelImpl;
    private int start;

    public HuZuPresenterImpl(HuZuView mHuZuView) {
        this.mHuZuView = mHuZuView;
        mHuZuModelImpl = new HuZuModelImpl();
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof PersonMsgMaResponse) {
            if (start == 1) {
                mHuZuView.refreshHuZuResponse(result);
            } else {
                mHuZuView.loadHuZuResponse(result);
            }
        }
        if (result instanceof PersonMsgResponse) {
            mHuZuView.refreshHuZuResponse(result);
        }
        mHuZuView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        mHuZuView.hideProgress();
        if (msg == null) {
            return;
        }
        mHuZuView.showMessage(msg.getMsg());
    }

    @Override
    public void queryHuZu(String s, int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mHuZuView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mHuZuView.hideProgress();
        }
        mHuZuView.showProgress();
        mHuZuModelImpl.queryHuZu(s, this);
    }

    @Override
    public void queryHuZuName(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mHuZuView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mHuZuModelImpl.queryHuZuName(s, this);
    }

    @Override
    public void fuzzyQueryHuZuName(String s, int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mHuZuView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mHuZuView.hideProgress();
        }
        mHuZuView.showProgress();
        mHuZuModelImpl.queryHuZu(s, this);
    }

}
