package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.CheckVerisonService;
import com.zhicheng.api.model.CheckVerisonModel;
import com.zhicheng.bean.http.AllVersionResponse;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.VersionResponse;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/24.
 */

public class CheckVersionModelImpl implements CheckVerisonModel {
    @Override
    public void getApps(Map<String, String> map, ApiCompleteListener listener) {
        CheckVerisonService mCheckVerisonService = ServiceFactory.createService(URL.PGYURL, CheckVerisonService.class);
        mCheckVerisonService.getApps(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<AllVersionResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                    }

                    @Override
                    public void onNext(Response<AllVersionResponse> mAllVersionResponse) {
                        if (mAllVersionResponse.isSuccessful()) {
                            if (mAllVersionResponse.body().getCode().equals("0")) {
                                if (mAllVersionResponse.body().getData() != null) {
                                    getAppInfoRequest(mCheckVerisonService, mAllVersionResponse, listener);
                                }
                            }
                        } else {
                            listener.onFailed(new BaseResponse(mAllVersionResponse.code(), mAllVersionResponse.message()));
                        }
                    }
                });

    }

    private void getAppInfoRequest(CheckVerisonService mCheckVerisonService, Response<AllVersionResponse> mAllVersionResponse, ApiCompleteListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("aKey", mAllVersionResponse.body().getData().get(mAllVersionResponse.body().getData().size() - 1).getAppKey());
        map.put("_api_key", Constant._api_key);
        map.put("uKey", Constant.uKey);
        mCheckVerisonService.checkVersion(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<VersionResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                    }

                    @Override
                    public void onNext(Response<VersionResponse> mVersionResponse) {
                        if (mVersionResponse.isSuccessful()) {
                            listener.onComplected(mVersionResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mVersionResponse.code(), mVersionResponse.message()));
                        }
                    }
                });


    }
}
