package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.WorkNodeModel;
import com.zhicheng.api.model.impl.WorkNodeModelImpl;
import com.zhicheng.api.presenter.WorkNodePresenter;
import com.zhicheng.api.view.WorkNodeView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/2.
 */

public class WorkNodePresenterImpl implements WorkNodePresenter,ApiCompleteListener {
    private WorkNodeView mWorkNodeView;
    private WorkNodeModel mWorkNodeModel;

    public WorkNodePresenterImpl(WorkNodeView mWorkNodeView) {
        this.mWorkNodeView = mWorkNodeView;
        mWorkNodeModel = new WorkNodeModelImpl();
    }

    @Override
    public void loadWorkNodes(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mWorkNodeView.hideProgress();
        }
        mWorkNodeView.showProgress();
        mWorkNodeModel.loadWorkNodes(s,this);
    }

    @Override
    public void sendWorkNodes(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mWorkNodeModel.sendWorkNodes(s,this);
    }

    @Override
    public void updateWorkNodes(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mWorkNodeModel.updateWorkNodes(s,this);
    }

    @Override
    public void deleteWorkNodes(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mWorkNodeView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mWorkNodeModel.deleteWorkNodes(s,this);
    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof CommonResponse){
            mWorkNodeView.refreshData(result);
        }
        if (result instanceof PersonalLogMaResponse){
            mWorkNodeView.refreshData(result);

        }
        mWorkNodeView.hideProgress();

    }

    @Override
    public void onFailed(BaseResponse msg) {
        mWorkNodeView.hideProgress();
        if (msg == null){
            return;
        }
        mWorkNodeView.showMessage(msg.getMsg());
    }
}
