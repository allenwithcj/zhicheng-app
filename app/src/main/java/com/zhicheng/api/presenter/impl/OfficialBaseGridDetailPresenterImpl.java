package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.OfficialBaseGridDetailModel;
import com.zhicheng.api.model.impl.OfficialBaseGridDetailModelImpl;
import com.zhicheng.api.presenter.OfficialBaseGridDetailPresenter;
import com.zhicheng.api.view.OfficialBaseGridDetailView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridDetailPresenterImpl implements OfficialBaseGridDetailPresenter, ApiCompleteListener {
    private OfficialBaseGridDetailView mOfficialBaseGridDetailView;
    private OfficialBaseGridDetailModel mOfficialBaseGridDetailModel;

    public OfficialBaseGridDetailPresenterImpl(OfficialBaseGridDetailView mOfficialBaseGridDetailView) {
        this.mOfficialBaseGridDetailView = mOfficialBaseGridDetailView;
        mOfficialBaseGridDetailModel = new OfficialBaseGridDetailModelImpl();
    }

    @Override
    public void loadDetail(String rBody) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mOfficialBaseGridDetailView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialBaseGridDetailModel.loadDetail(rBody, this);
    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof OfficialBaseGridDetailResponse) {
            mOfficialBaseGridDetailView.refreshData(result);
        }
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        mOfficialBaseGridDetailView.showMessage(msg.getMsg());
    }
}
