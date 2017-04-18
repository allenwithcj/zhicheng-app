package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialBaseGridQueryService;
import com.zhicheng.api.model.OfficialBaseGridQueryModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.bean.http.OfficialQueyResponse;
import com.zhicheng.bean.http.PersonQueryResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridQueryModelImpl implements OfficialBaseGridQueryModel {
    @Override
    public void query(String json, ApiCompleteListener listener) {
        OfficialBaseGridQueryService mOfficialBaseGridQueryService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialBaseGridQueryService.class);
        mOfficialBaseGridQueryService.query(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OfficialQueyResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                        BaseApplication.checkLogin();
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<OfficialQueyResponse> officialQueyResponseResponse) {
                        if (officialQueyResponseResponse.isSuccessful()) {
                            listener.onComplected(officialQueyResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(officialQueyResponseResponse.code(), officialQueyResponseResponse.message()));
                        }
                    }
                });

    }

    @Override
    public void loadDetail(String j, ApiCompleteListener listener) {
        OfficialBaseGridQueryService mOfficialBaseGridQueryService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialBaseGridQueryService.class);
        mOfficialBaseGridQueryService.queryDetail(j)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OfficialBaseGridDetailResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                        BaseApplication.checkLogin();
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<OfficialBaseGridDetailResponse> officialBaseGridDetailResponseResponse) {
                        if (officialBaseGridDetailResponseResponse.isSuccessful()) {
                            listener.onComplected(officialBaseGridDetailResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(officialBaseGridDetailResponseResponse.code(), officialBaseGridDetailResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void queryByCondition(String js, ApiCompleteListener listener) {
        OfficialBaseGridQueryService mOfficialBaseGridQueryService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialBaseGridQueryService.class);
        mOfficialBaseGridQueryService.queryByCondition(js)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PersonQueryResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                        BaseApplication.checkLogin();
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<PersonQueryResponse> mOfficialQueyResponse) {
                        if (mOfficialQueyResponse.isSuccessful()) {
                            listener.onComplected(mOfficialQueyResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mOfficialQueyResponse.code(), mOfficialQueyResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
