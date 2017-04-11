package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.LoginService;
import com.zhicheng.api.model.LoginModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.LoginResponse;
import com.zhicheng.common.URL;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/2/14.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(String lq, ApiCompleteListener listener) {
        LoginService mLoginService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, LoginService.class);
        mLoginService.loginRequest(lq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<LoginResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<LoginResponse> loginResponseResponse) {
                        if (loginResponseResponse.isSuccessful()) {
                            listener.onComplected(loginResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(loginResponseResponse.code(), loginResponseResponse.message()));
                        }
                    }
                });
    }
}
