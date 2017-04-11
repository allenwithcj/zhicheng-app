package com.zhicheng.api.presenter.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.CheckVersionModelImpl;
import com.zhicheng.api.presenter.CheckVersionPresenter;
import com.zhicheng.api.view.CheckVerisonView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.VersionResponse;

import java.util.Map;

/**
 * Created by hp on 2017/3/24.
 */

public class CheckVersionPresenterImpl implements CheckVersionPresenter, ApiCompleteListener {
    private CheckVersionModelImpl mCheckVersionModelImpl;
    private CheckVerisonView mCheckVerisonView;

    public CheckVersionPresenterImpl(CheckVerisonView checkVerisonView) {
        this.mCheckVerisonView = checkVerisonView;
        mCheckVersionModelImpl = new CheckVersionModelImpl();
    }

    @Override
    public void getApps(Map<String, String> map) {
        mCheckVersionModelImpl.getApps(map, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof VersionResponse) {
            mCheckVerisonView.checkResponse(result);
        }

    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        mCheckVerisonView.showMessage(msg.getMsg());
    }
}
