package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialbaseGridAddService;
import com.zhicheng.api.model.OfficialBaseGridAddModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.JudgementLocationResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/1.
 */

public class OfficialBaseGridAddModelImpl implements OfficialBaseGridAddModel {
    @Override
    public void addDate(String json, ApiCompleteListener listener) {
        OfficialbaseGridAddService mOfficialBaseGridAddService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialbaseGridAddService.class);
        mOfficialBaseGridAddService.addDate(json)
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
    public void judgmentLocation(String x, String y, ApiCompleteListener listener) {
        OfficialbaseGridAddService mOfficialBaseGridAddService = ServiceFactory.createService(URL.HOST_URL_SERVER_LOCATION, OfficialbaseGridAddService.class);
        mOfficialBaseGridAddService.judgmentLocation(x,y)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<JudgementLocationResponse>>() {
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
                    public void onNext(Response<JudgementLocationResponse> mJudgementLocationResponse) {
                        if (mJudgementLocationResponse.isSuccessful()) {
                            listener.onComplected(mJudgementLocationResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mJudgementLocationResponse.code(), mJudgementLocationResponse.message()));
                        }
                    }
                });
        }


}
