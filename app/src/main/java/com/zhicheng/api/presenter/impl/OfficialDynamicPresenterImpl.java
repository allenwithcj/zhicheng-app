package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.TestModel;
import com.zhicheng.api.model.impl.OfficialWorkModelImpl;
import com.zhicheng.api.model.impl.TestModelImpl;
import com.zhicheng.api.presenter.OfficialDynamicPresenter;
import com.zhicheng.api.presenter.TestPresenter;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.api.view.TestView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2016/12/30.
 */

public class OfficialDynamicPresenterImpl implements OfficialDynamicPresenter,ApiCompleteListener {
    private OfficialWorkModelImpl mModel;
    private OfficialView mView;

    public OfficialDynamicPresenterImpl(OfficialView view){
        mView = view;
        mModel = new OfficialWorkModelImpl();
    }

    @Override
    public void loadWork(String s) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())){
            mView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mView.hideProgress();
        }
        mView.showProgress();
        mModel.loadWorkDynamic(s,this);
    }

    /**
     * 访问接口/网络成功
     *
     * @param result
     */
    @Override
    public void onComplected(Object result) {
        mView.hideProgress();
    }

    /**
     * 访问接口/网络失败
     * 取消加载请求
     *
     * @param msg
     */
    @Override
    public void onFailed(BaseResponse msg) {
        mView.hideProgress();
        if (msg == null){
            return;
        }
        mView.showMessage(msg.getMsg());
    }


    @Override
    public void cancelLoading() {
        mModel.cancelLoading();
    }
}
