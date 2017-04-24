package com.zhicheng.api.model.impl;

import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.MainService;
import com.zhicheng.api.model.ExperienceModel;
import com.zhicheng.api.model.MyNewsModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.ExperienceCommonResponse;
import com.zhicheng.bean.http.ExperienceDetailResponse;
import com.zhicheng.bean.http.ExperienceResponse;
import com.zhicheng.bean.http.IneedResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.json.FormExportRequest;
import com.zhicheng.bean.json.FormNodeRequest;
import com.zhicheng.bean.json.FormSendDoRequest;
import com.zhicheng.bean.json.FormSubnodeRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;
import com.zhicheng.luban.Luban;
import com.zhicheng.utils.common.UIUtils;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/1/2.
 */

public class MyNewsModelImpl implements MyNewsModel {


    @Override
    public void readMyNews(String s,ApiCompleteListener listener) {
            MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, MainService.class);
            mMainService.FormSendDoRequest(s)
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
