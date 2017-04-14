package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.LocationUpService;
import com.zhicheng.api.model.LocationModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/15.
 */

public class LocationModelImpl implements LocationModel {
    @Override
    public void upLocation(String lq, ApiCompleteListener listener) {
        LocationUpService locationUpService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, LocationUpService.class);
        locationUpService.upLocation(lq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CommonResponse>>() {
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
                    public void onNext(Response<CommonResponse> mCommonResponse) {
                        if (mCommonResponse.isSuccessful()) {
                            listener.onComplected(mCommonResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mCommonResponse.code(), mCommonResponse.message()));
                        }
                    }
                });
    }
}
