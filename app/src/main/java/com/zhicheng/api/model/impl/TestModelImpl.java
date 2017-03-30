package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.TestService;
import com.zhicheng.api.model.TestModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2016/12/30.
 */

public class TestModelImpl implements TestModel {
    @Override
    public void loadTestList(String json, ApiCompleteListener listener) {
        TestService mTestService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,TestService.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),json);
        mTestService.getTestData(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException){
                            listener.onFailed(null);
                            return;
                        }
                        listener.onFailed(new BaseResponse(404,e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<String> json) {
                        if (json.isSuccessful()){
                            listener.onComplected(json.body());
                        }else {
                            listener.onFailed(new BaseResponse(json.code(),json.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
