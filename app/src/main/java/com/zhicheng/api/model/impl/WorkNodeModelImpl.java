package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.WorkNodeService;
import com.zhicheng.api.model.WorkNodeModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hp on 2017/3/2.
 */

public class WorkNodeModelImpl implements WorkNodeModel {
    @Override
    public void loadWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,WorkNodeService.class);
        mWorkNodeService.loadWorkNode(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PersonalLogMaResponse>>() {
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
                    public void onNext(Response<PersonalLogMaResponse> personalLogMaResponseResponse) {
                        if (personalLogMaResponseResponse.isSuccessful()){
                            listener.onComplected(personalLogMaResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(personalLogMaResponseResponse.code(),personalLogMaResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void sendWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,WorkNodeService.class);
        mWorkNodeService.sendWorkNode(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CommonResponse>>() {
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
                    public void onNext(Response<CommonResponse> commonResponseResponse) {
                        if (commonResponseResponse.isSuccessful()){
                            listener.onComplected(commonResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(commonResponseResponse.code(),commonResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void updateWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,WorkNodeService.class);
        mWorkNodeService.updateWorkNode(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CommonResponse>>() {
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
                    public void onNext(Response<CommonResponse> commonResponseResponse) {
                        if (commonResponseResponse.isSuccessful()){
                            listener.onComplected(commonResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(commonResponseResponse.code(),commonResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void deleteWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,WorkNodeService.class);
        mWorkNodeService.deleteWorkNode(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<CommonResponse>>() {
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
                    public void onNext(Response<CommonResponse> commonResponseResponse) {
                        if (commonResponseResponse.isSuccessful()){
                            listener.onComplected(commonResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(commonResponseResponse.code(),commonResponseResponse.message()));
                        }
                    }
                });
    }


    @Override
    public void cancelLoading() {

    }
}
