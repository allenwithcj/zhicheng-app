package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.OfficialBaseGridAddModel;
import com.zhicheng.api.model.impl.OfficialBaseGridAddModelImpl;
import com.zhicheng.api.presenter.OfficialBaseGridAddPresenter;
import com.zhicheng.api.view.OfficialBaseGridAddView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/1.
 */

public class OfficialBaseGridAddPresenterImpl implements OfficialBaseGridAddPresenter, ApiCompleteListener {
    private OfficialBaseGridAddView mOfficialBaseGridAddView;
    private OfficialBaseGridAddModel mOfficialBaseGridAddModel;

    public OfficialBaseGridAddPresenterImpl(OfficialBaseGridAddView mOfficialBaseGridAddView) {
        this.mOfficialBaseGridAddView = mOfficialBaseGridAddView;
        mOfficialBaseGridAddModel = new OfficialBaseGridAddModelImpl();
    }


    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse) {
            mOfficialBaseGridAddView.addDateResponse(result);
        }
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        mOfficialBaseGridAddView.showMessage(msg.getMsg());

    }

    @Override
    public void addDate(String rBody) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mOfficialBaseGridAddView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialBaseGridAddModel.addDate(rBody, this);
    }
}
