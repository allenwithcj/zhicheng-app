package com.zhicheng.api.presenter.impl;

import com.zhicheng.R;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.model.LoginModel;
import com.zhicheng.api.model.impl.LoginModelImpl;
import com.zhicheng.api.presenter.LoginPresenter;
import com.zhicheng.api.view.LoginView;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.LoginResponse;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import okhttp3.RequestBody;

/**
 * Created by Donson on 2017/2/14.
 */

public class LoginPresenterImpl implements LoginPresenter, ApiCompleteListener {

    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView lv) {
        mLoginModel = new LoginModelImpl();
        mLoginView = lv;
    }

    @Override
    public void login(String rb) {
        if (!NetworkUtils.isConnected(UIUtils.getContext())) {
            mLoginView.showMessage(UIUtils.getContext().getString(R.string.poor_network));
        }
        mLoginModel.login(rb, this);
    }

    @Override
    public void onComplected(Object result) {
        if (result instanceof LoginResponse) {
            mLoginView.loginResponse(result);
        }
    }

    @Override
    public void onFailed(BaseResponse msg) {
        if (msg == null) {
            return;
        }
        mLoginView.showMessage(msg.getMsg());
    }
}
