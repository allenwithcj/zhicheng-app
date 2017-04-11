package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialBaseGridUpdateService;
import com.zhicheng.api.model.OfficialBaseGridUpdateModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridUpdateModelImpl implements OfficialBaseGridUpdateModel {
    @Override
    public void updateDate(String json, ApiCompleteListener listener) {
        OfficialBaseGridUpdateService mOfficialBaseGridUpdateService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialBaseGridUpdateService.class);
        mOfficialBaseGridUpdateService.updateDate(json)
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
    public void cancelLoading() {

    }
}
