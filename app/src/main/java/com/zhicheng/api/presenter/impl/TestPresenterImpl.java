package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.TestModel;
import com.zhicheng.api.model.impl.TestModelImpl;
import com.zhicheng.api.presenter.TestPresenter;
import com.zhicheng.api.view.TestView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by Donson on 2016/12/30.
 */

public class TestPresenterImpl implements TestPresenter, ApiCompleteListener {
    private TestModel mTestModel;
    private TestView mTestView;

    public TestPresenterImpl(TestView view) {
        mTestView = view;
        mTestModel = new TestModelImpl();
    }

    @Override
    public void loadTest(String json) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mTestView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
            mTestView.hideProgress();
        }
        mTestView.showProgress();
        mTestModel.loadTestList(json, this);
    }

    /**
     * 访问接口/网络成功
     *
     * @param result
     */
    @Override
    public void onComplected(Object result) {
        mTestView.updateView(result);
        mTestView.hideProgress();
    }

    /**
     * 访问接口/网络失败
     * 取消加载请求
     *
     * @param msg
     */
    @Override
    public void onFailed(BaseResponse msg) {
        mTestView.hideProgress();
        if (msg == null) {
            return;
        }
        mTestView.showMessage(msg.getMsg());
    }


    @Override
    public void cancelLoading() {
        mTestModel.cancelLoading();
    }
}
