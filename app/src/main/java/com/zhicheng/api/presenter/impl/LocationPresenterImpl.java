package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.LocationModelImpl;
import com.zhicheng.api.presenter.LocationPresenter;
import com.zhicheng.api.view.LocationView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.json.CaseQueryRequest;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/15.
 */

public class LocationPresenterImpl implements LocationPresenter, ApiCompleteListener {
    private LocationModelImpl locationModelImpl;
    private LocationView locationView;

    public LocationPresenterImpl(LocationView locationView) {
        this.locationView = locationView;
        locationModelImpl = new LocationModelImpl();
    }

    @Override
    public void upLocation(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            locationView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        locationModelImpl.upLocation(s, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CaseQueryRequest) {
            locationView.refreshData(result);
        }
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        locationView.showMessage(msg.getMsg());
    }
}
