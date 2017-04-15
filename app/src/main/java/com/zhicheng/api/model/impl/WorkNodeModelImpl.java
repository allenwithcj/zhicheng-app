package com.zhicheng.api.model.impl;

import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.OfficialService;
import com.zhicheng.api.common.service.WorkNodeService;
import com.zhicheng.api.model.WorkNodeModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;
import com.zhicheng.bean.json.PersonalLogMaRequest;
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
 * Created by hp on 2017/3/2.
 */

public class WorkNodeModelImpl implements WorkNodeModel {
    @Override
    public void loadWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, WorkNodeService.class);
        mWorkNodeService.loadWorkNode(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PersonalLogMaResponse>>() {
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
                    public void onNext(Response<PersonalLogMaResponse> personalLogMaResponseResponse) {
                        if (personalLogMaResponseResponse.isSuccessful()) {
                            listener.onComplected(personalLogMaResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(personalLogMaResponseResponse.code(), personalLogMaResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void sendWorkNodes(String jFile, List<String> imgs, String nodes, String attGUID, String GUID, ApiCompleteListener listener) {
        OfficialService mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, OfficialService.class);

        final MultipartBody.Builder builder = new MultipartBody.Builder();
        //图片压缩
        List<File> mFiles = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            mFiles.add(new File(imgs.get(i)));
        }
        if (mFiles.size() > 0) {
            Luban.get(UIUtils.getContext())
                    .load(mFiles)
                    .putGear(Luban.FIRST_GEAR)
                    .asList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    })
                    .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<File>>>() {
                        @Override
                        public Observable<? extends List<File>> call(Throwable throwable) {
                            return Observable.empty();
                        }
                    }).subscribe(new Action1<List<File>>() {
                @Override
                public void call(List<File> files) {
                    if (files.size() > 0) {
                        List<String> imgs = new ArrayList<String>();
                        for (int i = 0; i < files.size(); i++) {
                            imgs.add(files.get(i).getAbsolutePath());
                        }
                        if (imgs.size() > 0) {
                            Observable.from(imgs)
                                    .map(s -> {
                                        File file = new File(s);
                                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MultipartBody.FORM, file));
                                        return s;
                                    }).last()
                                    .flatMap(new Func1<String, Observable<Response<CommonResponse>>>() {
                                        @Override
                                        public Observable<Response<CommonResponse>> call(String s) {
                                            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jFile);
                                            return mOfficialService.UpDealFile(body, builder.build());
                                        }
                                    }).subscribeOn(Schedulers.io())
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
                                                if (commonResponseResponse.body().getIq().getQuery().getErrorCode() == 0) {
                                                    personalLogMaRequest(nodes, attGUID, GUID, listener);
                                                    BaseApplication.log_say("MainModelImpl", "UpThings");
                                                } else {
                                                    listener.onComplected(commonResponseResponse.body());
                                                    Toast.makeText(UIUtils.getContext(), commonResponseResponse.body().getIq().getQuery().getErrorMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                listener.onFailed(new BaseResponse(commonResponseResponse.code(), commonResponseResponse.message()));
                                            }
                                        }
                                    });
                        }
                    }
                }
            });
        }

    }

    private void personalLogMaRequest(String nodes, String attGuid, String guid, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, WorkNodeService.class);
        PersonalLogMaRequest personalLogMaRequest = new PersonalLogMaRequest();
        PersonalLogMaRequest.IqBean iq = new PersonalLogMaRequest.IqBean();
        PersonalLogMaRequest.IqBean.QueryBean qb = new PersonalLogMaRequest.IqBean.QueryBean();
        iq.setNamespace("PersonalLogMaRequest");
        qb.setType("1");
        qb.setCon(nodes);
        qb.setId(guid);
        qb.setAttguid(attGuid);
        iq.setQuery(qb);
        personalLogMaRequest.setIq(iq);
        Gson gson = new Gson();
        mWorkNodeService.sendWorkNode(gson.toJson(personalLogMaRequest))
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
                    public void onNext(Response<CommonResponse> mcCommonResponse) {
                        if (mcCommonResponse.isSuccessful()) {
                            listener.onComplected(mcCommonResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(mcCommonResponse.code(), mcCommonResponse.message()));
                        }
                    }
                });

    }


    @Override
    public void updateWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, WorkNodeService.class);
        mWorkNodeService.updateWorkNode(s)
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
    public void deleteWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, WorkNodeService.class);
        mWorkNodeService.deleteWorkNode(s)
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
    public void invalidWorkNodes(String s, ApiCompleteListener listener) {
        WorkNodeService mWorkNodeService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG, WorkNodeService.class);
        mWorkNodeService.invalidWorkNode(s)
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
