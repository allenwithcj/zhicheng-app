package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.OfficialBaseGridQueryModel;
import com.zhicheng.api.model.impl.OfficialBaseGridQueryModelImpl;
import com.zhicheng.api.presenter.OfficialBaseGridQueryPresenter;
import com.zhicheng.api.view.OfficialBaseGridQueryView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.OfficialQueyResponse;
import com.zhicheng.bean.http.PersonQueryResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridQueryPresenterImpl implements OfficialBaseGridQueryPresenter, ApiCompleteListener {
    private OfficialBaseGridQueryView mOfficialBaseGridQueryView;
    private OfficialBaseGridQueryModel mOfficialBaseGridQueryModel;
    private int start;

    public OfficialBaseGridQueryPresenterImpl(OfficialBaseGridQueryView mOfficialBaseGridQueryView) {
        this.mOfficialBaseGridQueryView = mOfficialBaseGridQueryView;
        mOfficialBaseGridQueryModel = new OfficialBaseGridQueryModelImpl();
    }

    @Override
    public void query(String rBody, int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mOfficialBaseGridQueryView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mOfficialBaseGridQueryView.hideProgress();
        }
        mOfficialBaseGridQueryView.showProgress();
        mOfficialBaseGridQueryModel.query(rBody, this);
    }

    @Override
    public void loadDetail(String js) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mOfficialBaseGridQueryView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialBaseGridQueryModel.loadDetail(js, this);
    }

    @Override
    public void queryByCondition(String js) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mOfficialBaseGridQueryView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialBaseGridQueryModel.queryByCondition(js, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof OfficialQueyResponse) {
            if (start == 1) {
                mOfficialBaseGridQueryView.refreshData(result);
            } else {
                mOfficialBaseGridQueryView.addData(result);
            }
        }
        if(result instanceof PersonQueryResponse){
            mOfficialBaseGridQueryView.refreshData(result);
        }
        mOfficialBaseGridQueryView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        mOfficialBaseGridQueryView.hideProgress();
        if (msg == null) {
            return;
        }
        mOfficialBaseGridQueryView.showMessage(msg.getMsg());
    }
}
