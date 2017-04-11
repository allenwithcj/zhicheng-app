package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.CaseQueryModelImpl;
import com.zhicheng.api.presenter.CaseQueryPresenter;
import com.zhicheng.api.view.CaseQueryView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CaseQueryResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/11.
 */

public class CaseQueryPresenterImpl implements CaseQueryPresenter, ApiCompleteListener {
    private CaseQueryModelImpl caseQueryModelImpl;
    private CaseQueryView caseQueryView;
    private int start;

    public CaseQueryPresenterImpl(CaseQueryView caseQueryView) {
        this.caseQueryView = caseQueryView;
        caseQueryModelImpl = new CaseQueryModelImpl();
    }

    @Override
    public void caseQuery(String s, int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            caseQueryView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            caseQueryView.hideProgress();
        }
        caseQueryView.showProgress();
        caseQueryModelImpl.caseQuery(s, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CaseQueryResponse) {
            if (start == 1) {
                caseQueryView.refreshData(result);
            } else {
                caseQueryView.addData(result);
            }
        }
        caseQueryView.hideProgress();
    }

    @Override
    public void onFailed(BaseResponse msg) {
        caseQueryView.hideProgress();
        if (msg == null) {
            return;
        }
        caseQueryView.showMessage(msg.getMsg());
    }
}
