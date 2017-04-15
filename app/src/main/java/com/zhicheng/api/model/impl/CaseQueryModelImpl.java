package com.zhicheng.api.model.impl;

import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.CaseQueryService;
import com.zhicheng.api.model.CaseQueryModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CaseQueryResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/15.
 */

public class CaseQueryModelImpl implements CaseQueryModel {
    @Override
    public void caseQuery(String lq, ApiCompleteListener listener) {
        CaseQueryService caseQueryService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, CaseQueryService.class);
        caseQueryService.upLocation(lq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CaseQueryResponse>>() {
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
                    public void onNext(Response<CaseQueryResponse> mCaseQueryResponse) {
                        if (mCaseQueryResponse.isSuccessful()) {
                            listener.onComplected(mCaseQueryResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mCaseQueryResponse.code(), mCaseQueryResponse.message()));
                        }
                    }
                });
    }
}
