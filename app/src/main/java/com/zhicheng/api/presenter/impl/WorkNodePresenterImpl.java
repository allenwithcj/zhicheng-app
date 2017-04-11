package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.impl.WorkNodeModelImpl;
import com.zhicheng.api.presenter.WorkNodePresenter;
import com.zhicheng.api.view.WorkNodeView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;

/**
 * Created by hp on 2017/3/2.
 */

public class WorkNodePresenterImpl implements WorkNodePresenter, ApiCompleteListener {
    private WorkNodeView mWorkNodeView;
    private WorkNodeModelImpl mWorkNodeModelImpl;
    private int start;

    public WorkNodePresenterImpl(WorkNodeView mWorkNodeView) {
        this.mWorkNodeView = mWorkNodeView;
        mWorkNodeModelImpl = new WorkNodeModelImpl();
    }

    @Override
    public void loadWorkNodes(String s, int start) {
        this.start = start;
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mWorkNodeView.hideProgress();
        }
        mWorkNodeView.showProgress();
        mWorkNodeModelImpl.loadWorkNodes(s, this);
    }

    @Override
    public void sendWorkNodes(String jFile, List<String> imgs, String nodes, String attGUID, String GUID, ApiCompleteListener listener) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mWorkNodeModelImpl.sendWorkNodes(jFile, imgs, nodes, attGUID, GUID, this);
    }

    @Override
    public void updateWorkNodes(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mWorkNodeModelImpl.updateWorkNodes(s, this);
    }

    @Override
    public void deleteWorkNodes(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mWorkNodeModelImpl.deleteWorkNodes(s, this);
    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse) {
            mWorkNodeView.refreshData(result);
        }
        if (result instanceof PersonalLogMaResponse) {
            if (start == 1) {
                mWorkNodeView.refreshData(result);
            } else {
                mWorkNodeView.addData(result);
            }
        }
        mWorkNodeView.hideProgress();

    }

    @Override
    public void onFailed(BaseResponse msg) {
        mWorkNodeView.hideProgress();
        if (msg == null) {
            return;
        }
        mWorkNodeView.showMessage(msg.getMsg());
    }
}
