package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialBaseGridService;
import com.zhicheng.api.model.OfficialBaseGridModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CaseGridResponse;
import com.zhicheng.bean.http.OfficialBaseGridResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/1/6.
 */

public class OfficialBaseGridModelImpl implements OfficialBaseGridModel {


    @Override
    public void loadOfficialGridNames(String s, ApiCompleteListener listener) {
        OfficialBaseGridService mOfficialBaseGridService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialBaseGridService.class);
        mOfficialBaseGridService.loadGridNames(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CaseGridResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
//                        BaseApplication.checkLogin();
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<CaseGridResponse> mCaseGridResponseResponse) {
                        if (mCaseGridResponseResponse.isSuccessful()) {
                            listener.onComplected(mCaseGridResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mCaseGridResponseResponse.code(), mCaseGridResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
