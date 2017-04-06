package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficailBaseGridDetailService;
import com.zhicheng.api.model.OfficialBaseGridDetailModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridDetailModelImpl implements OfficialBaseGridDetailModel {
    @Override
    public void loadDetail(String json, ApiCompleteListener listener) {
        OfficailBaseGridDetailService mOfficailBaseGridDetailService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficailBaseGridDetailService.class);
        mOfficailBaseGridDetailService.queryDetail(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OfficialBaseGridDetailResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException){
                            listener.onFailed(null);
                            return;
                        }
                        BaseApplication.checkLogin();
                        listener.onFailed(new BaseResponse(404,e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<OfficialBaseGridDetailResponse> officialBaseGridDetailResponse) {
                        if (officialBaseGridDetailResponse.isSuccessful()){
                            listener.onComplected(officialBaseGridDetailResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(officialBaseGridDetailResponse.code(),officialBaseGridDetailResponse.message()));
                        }
                    }
                });
    }


    @Override
    public void cancelLoading() {

    }
}
