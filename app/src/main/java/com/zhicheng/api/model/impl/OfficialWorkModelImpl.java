package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialDynamicService;
import com.zhicheng.api.model.OfficialWorkModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2016/12/30.
 */

public class OfficialWorkModelImpl implements OfficialWorkModel {

    @Override
    public void loadWorkDynamic(String work, ApiCompleteListener listener) {
        OfficialDynamicService officialDynamicService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialDynamicService.class);
        officialDynamicService.getWorkDy(work)
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
                    public void onNext(Response<CommonResponse> commonResponseResponse) {
                        if (commonResponseResponse.isSuccessful()) {
                            listener.onComplected(commonResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(commonResponseResponse.code(), commonResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void upWorkDynamic(String work, ApiCompleteListener listener) {
        OfficialDynamicService officialDynamicService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialDynamicService.class);
        officialDynamicService.upWorkDy(work)
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
                    public void onNext(Response<CommonResponse> commonResponseResponse) {
                        if (commonResponseResponse.isSuccessful()) {
                            listener.onComplected(commonResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(commonResponseResponse.code(), commonResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
