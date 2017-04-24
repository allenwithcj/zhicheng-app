package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.HuZuService;
import com.zhicheng.api.model.HuZuModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.PersonMsgMaResponse;
import com.zhicheng.bean.http.PersonMsgResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lwl on 2017/4/16.
 */

public class HuZuModelImpl implements HuZuModel {
    @Override
    public void queryHuZu(String lq, ApiCompleteListener listener) {
        HuZuService mHuZuService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, HuZuService.class);
        mHuZuService.queryHuzu(lq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PersonMsgMaResponse>>() {
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
                    public void onNext(Response<PersonMsgMaResponse> mPersonMsgMaResponse) {
                        if (mPersonMsgMaResponse.isSuccessful()) {
                            listener.onComplected(mPersonMsgMaResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mPersonMsgMaResponse.code(), mPersonMsgMaResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void queryHuZuName(String lq, ApiCompleteListener listener) {
        HuZuService mHuZuService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, HuZuService.class);
        mHuZuService.queryHuzuName(lq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PersonMsgResponse>>() {
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
                    public void onNext(Response<PersonMsgResponse> mPersonMsgMaResponse) {
                        if (mPersonMsgMaResponse.isSuccessful()) {
                            listener.onComplected(mPersonMsgMaResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mPersonMsgMaResponse.code(), mPersonMsgMaResponse.message()));
                        }
                    }
                });
    }


}
