package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.OfficialBaseGridUpdateModel;
import com.zhicheng.api.model.impl.OfficialBaseGridUpdateModelImpl;
import com.zhicheng.api.presenter.OfficialBaseGridUpdatePresenter;
import com.zhicheng.api.view.OfficialBaseGridUpdateView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridUpdatePresenterImpl implements OfficialBaseGridUpdatePresenter, ApiCompleteListener {
    private OfficialBaseGridUpdateView mOfficialBaseGridUpdateView;
    private OfficialBaseGridUpdateModel mOfficialBaseGridUpdateModel;

    public OfficialBaseGridUpdatePresenterImpl(OfficialBaseGridUpdateView mOfficialBaseGridUpdateView) {
        this.mOfficialBaseGridUpdateView = mOfficialBaseGridUpdateView;
        mOfficialBaseGridUpdateModel = new OfficialBaseGridUpdateModelImpl();
    }

    @Override
    public void updateDate(String rBody) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mOfficialBaseGridUpdateView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mOfficialBaseGridUpdateModel.updateDate(rBody, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse) {
            mOfficialBaseGridUpdateView.updateDateResponse(result);
        }

    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        mOfficialBaseGridUpdateView.showMessage(msg.getMsg());
    }
}
