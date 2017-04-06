package com.zhicheng.api.model.impl;


import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.MainService;
import com.zhicheng.api.common.service.OfficialService;
import com.zhicheng.api.model.OfficialModel;
import com.zhicheng.bean.http.AnnoucementDetailsResponse;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.IneedResponse;
import com.zhicheng.bean.http.NoticeResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.bean.json.FormExportRequest;
import com.zhicheng.bean.json.FormNodeRequest;
import com.zhicheng.bean.json.FormSendDoRequest;
import com.zhicheng.bean.json.FormSubnodeRequest;
import com.zhicheng.bean.json.PersonalDynamicRequest;
import com.zhicheng.common.URL;
import com.zhicheng.luban.Luban;
import com.zhicheng.utils.common.UIUtils;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
 * Created by Donson on 2017/1/4.
 */

public class OfficialModelImpl implements OfficialModel{
    OfficialService mOfficialService;
    private Gson gson = new Gson();
    private String GUID;
    private String suggestion;
    private OfficialDetailResponse OfficialDeatail;

    @Override
    public void loadOfficial(String nofinish, ApiCompleteListener listener) {
        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }
        mOfficialService.getOfficial(nofinish)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OfficialResponse>>() {
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
                    public void onNext(Response<OfficialResponse> officialResponseResponse) {
                        if (officialResponseResponse.isSuccessful()){
                            listener.onComplected(officialResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(officialResponseResponse.code(),officialResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void loadOfficialDetail(String j, ApiCompleteListener listener) {
        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }
        mOfficialService.getOfficialDetail(j)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OfficialDetailResponse>>() {
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
                    public void onNext(Response<OfficialDetailResponse> officialDetailResponseResponse) {
                        if (officialDetailResponseResponse.isSuccessful()){
                            listener.onComplected(officialDetailResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(officialDetailResponseResponse.code(),officialDetailResponseResponse.message()));
                        }
                    }
                });
    }

    //工作动态获取
    @Override
    public void loadOfficialDynamic(String dyn, ApiCompleteListener listener) {
        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }
        mOfficialService.getOfficialWorkDynamic(dyn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OfficialWorkDynamicList>>() {
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
                    public void onNext(Response<OfficialWorkDynamicList> officialWorkDynamicListResponse) {
                        if (officialWorkDynamicListResponse.isSuccessful()){
                            listener.onComplected(officialWorkDynamicListResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(officialWorkDynamicListResponse.code(),officialWorkDynamicListResponse.message()));
                        }
                    }
                });
    }
    //新增工作动态
    @Override
    public void upOfficialDynamic(String jFile,List<String> imgs,String content,String mLocationSite,String GUID, ApiCompleteListener listener) {
        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }

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
                                        builder.addFormDataPart("file",file.getName(), RequestBody.create(MultipartBody.FORM,file));
                                        return s;
                                    }).last()
                                    .flatMap(new Func1<String, Observable<Response<CommonResponse>>>() {
                                        @Override
                                        public Observable<Response<CommonResponse>> call(String s) {
                                            RequestBody body = RequestBody.create(MediaType.parse("application/json"),jFile);
                                            return mOfficialService.UpDealFile(body,builder.build());
                                        }
                                    }).subscribeOn(Schedulers.io())
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
                                                if (commonResponseResponse.body().getIq().getQuery().getErrorCode() == 0){
                                                    personalDynamicRequest(mOfficialService,content,mLocationSite,GUID,listener);
                                                    BaseApplication.log_say("MainModelImpl","UpThings");
                                                }else {
                                                    listener.onComplected(commonResponseResponse.body());
                                                    Toast.makeText(UIUtils.getContext(),commonResponseResponse.body().getIq().getQuery().getErrorMessage(),Toast.LENGTH_LONG).show();
                                                }
                                            }else {
                                                listener.onFailed(new BaseResponse(commonResponseResponse.code(),commonResponseResponse.message()));
                                            }
                                        }
                                    });

                        }
                    }
                }
            });
        }

    }

    private void personalDynamicRequest(OfficialService mOfficialService, String content,String mLocationSite, String guid, ApiCompleteListener listener) {
        PersonalDynamicRequest mPersonalDynamicRequest  = new PersonalDynamicRequest();
        PersonalDynamicRequest.IqBean iqb = new PersonalDynamicRequest.IqBean();
        PersonalDynamicRequest.IqBean.QueryBean qb = new PersonalDynamicRequest.IqBean.QueryBean();
        iqb.setNamespace("PersonalDynamicRequest");
        qb.setType("1");
        qb.setId(UUID.randomUUID().toString());
        qb.setCont(content);
        qb.setAttguid(guid);
        qb.setLocation(mLocationSite);
        iqb.setQuery(qb);
        mPersonalDynamicRequest.setIq(iqb);
        Gson gson = new Gson();

        mOfficialService.upOfficialWorkDynamic(gson.toJson(mPersonalDynamicRequest))
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
                        BaseApplication.checkLogin();
                        listener.onFailed(new BaseResponse(404,e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<CommonResponse> mcCommonResponse) {
                        if (mcCommonResponse.isSuccessful()){
                            listener.onComplected(mcCommonResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(mcCommonResponse.code(),mcCommonResponse.message()));
                        }
                    }
                });


    }

    @Override
    public void upDeal(List<String> imgs,String jFile,String suggest,String GUID,OfficialDetailResponse officialDetailResponse,String type,ApiCompleteListener listener) {
        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }
        this.OfficialDeatail = officialDetailResponse;
        this.suggestion = suggest;
        this.GUID = GUID;
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        if(imgs.size() != 0){
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
                                            builder.addFormDataPart("file",file.getName(), RequestBody.create(MultipartBody.FORM,file));
                                            return s;
                                        }).last()
                                        .flatMap(new Func1<String, Observable<Response<CommonResponse>>>() {
                                            @Override
                                            public Observable<Response<CommonResponse>> call(String s) {
                                                RequestBody body = RequestBody.create(MediaType.parse("application/json"),jFile);
                                                return mOfficialService.UpDealFile(body,builder.build());
                                            }
                                        }).subscribeOn(Schedulers.io())
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
                                                    if (commonResponseResponse.body().getIq().getQuery().getErrorCode() == 0){
                                                        formExportRequest(0,type,listener);
                                                        BaseApplication.log_say("MainModelImpl","UpThings");
                                                    }else {
                                                        listener.onComplected(commonResponseResponse.body());
                                                        Toast.makeText(UIUtils.getContext(),commonResponseResponse.body().getIq().getQuery().getErrorMessage(),Toast.LENGTH_LONG).show();
                                                    }
                                                }else {
                                                    listener.onFailed(new BaseResponse(commonResponseResponse.code(),commonResponseResponse.message()));
                                                }
                                            }
                                        });

                            }
                        }
                    }
                });
            }
        }else{
            formExportRequest(0,type,listener);
        }
    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void loadNotice(String n, ApiCompleteListener listener) {

        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }
        mOfficialService.getNotice(n)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<NoticeResponse>>() {
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
                    public void onNext(Response<NoticeResponse> noticeResponseResponse) {
                        if (noticeResponseResponse.isSuccessful()){
                            listener.onComplected(noticeResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(noticeResponseResponse.code(),noticeResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void queryNewsDetail(String j, ApiCompleteListener listener) {
        if (mOfficialService == null){
            mOfficialService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,OfficialService.class);
        }
        mOfficialService.queryNewsDetail(j)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<AnnoucementDetailsResponse>>() {
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
                    public void onNext(Response<AnnoucementDetailsResponse> mAnnoucementDetailsResponseResponse) {
                        if (mAnnoucementDetailsResponseResponse.isSuccessful()){
                            listener.onComplected(mAnnoucementDetailsResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(mAnnoucementDetailsResponseResponse.code(),mAnnoucementDetailsResponseResponse.message()));
                        }
                    }
                });
    }

    public void formExportRequest(int requestType, String type, ApiCompleteListener listener){
        MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,MainService.class);
        FormExportRequest feq = new FormExportRequest();
        FormExportRequest.IqBean iq = new FormExportRequest.IqBean();
        FormExportRequest.IqBean.QueryBean query = new FormExportRequest.IqBean.QueryBean();
        iq.setNamespace("FormExportRequest");
        query.setId(OfficialDeatail.getIq().getQuery().getId());
        query.setRequestType(requestType);
        iq.setQuery(query);
        feq.setIq(iq);
        mMainService.FormExportRequest(gson.toJson(feq))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<IneedResponse>>() {
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
                    public void onNext(Response<IneedResponse> ineedResponseResponse) {
                        if (ineedResponseResponse.isSuccessful()){
                            if (ineedResponseResponse.body().getIq().getQuery().getErrorCode().equals("0")){
                                FormNodeRequest formNode = new FormNodeRequest();
                                FormNodeRequest.IqBean iq = new FormNodeRequest.IqBean();
                                FormNodeRequest.IqBean.QueryBean query = new FormNodeRequest.IqBean.QueryBean();
                                query.setRequestType("0");
                                query.setId(OfficialDeatail.getIq().getQuery().getId());
                                List<IneedResponse.IqBean.QueryBean.ItemsBean> itemsBeens = ineedResponseResponse.body().getIq().getQuery().getItems();
                                if(itemsBeens != null){
                                    for(IneedResponse.IqBean.QueryBean.ItemsBean itemsBean:itemsBeens){
                                        if(type.equals("0")){
                                            if(itemsBean.getValue().equals("提交处置结果")){
                                                query.setChukouID(itemsBean.getKey());
                                            }
                                        }else if(type.equals("4")){
                                            if(itemsBean.getValue().equals("申请重新分配")){
                                                query.setChukouID(itemsBean.getKey());
                                            }
                                        }
                                    }
                                }
                                iq.setNamespace("FormNodeRequest");
                                iq.setQuery(query);
                                formNode.setIq(iq);
                                formNodeRequest(mMainService,gson.toJson(formNode),ineedResponseResponse,requestType,listener);
                                BaseApplication.log_say("MainModelImpl","FormNodeRequest");
                            }else {
                                listener.onComplected(ineedResponseResponse.body());
                                Toast.makeText(UIUtils.getContext(),ineedResponseResponse.body().getIq().getQuery().getErrorMessage(),Toast.LENGTH_LONG).show();
                            }
                        }else {
                            listener.onFailed(new BaseResponse(ineedResponseResponse.code(),ineedResponseResponse.message()));
                        }
                    }
                });
    }

    public void formNodeRequest(MainService s, String j, Response<IneedResponse> ineedResponseResponse, int requestType, ApiCompleteListener listener){
        s.FormNodeRequest(j)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<IneedResponse>>() {
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
                    public void onNext(Response<IneedResponse> mIneedResponse) {
                        if (mIneedResponse.isSuccessful()){
                            if (mIneedResponse.body().getIq().getQuery().getErrorCode().equals("0")){
                                FormSubnodeRequest formSubnode = new FormSubnodeRequest();
                                FormSubnodeRequest.IqBean iq = new FormSubnodeRequest.IqBean();
                                FormSubnodeRequest.IqBean.QueryBean query = new FormSubnodeRequest.IqBean.QueryBean();
                                query.setRequestType(0);
                                query.setType(3);
                                query.setId(mIneedResponse.body().getIq().getQuery().getNodes().get(0).getId());
                                query.setTableName("");
                                query.setTableID("");
                                query.setWfInfoID(OfficialDeatail.getIq().getQuery().getId());
                                query.setGrid("");
                                iq.setNamespace("FormSubnodeRequest");
                                iq.setQuery(query);
                                formSubnode.setIq(iq);
                                formSubnodeRequest(s,gson.toJson(formSubnode),mIneedResponse,requestType,listener);
                                BaseApplication.log_say("MainModelImpl","FormSubnodeRequest");
                            }else {
                                listener.onComplected(mIneedResponse.body());
                                Toast.makeText(UIUtils.getContext(),mIneedResponse.body().getIq().getQuery().getErrorMessage(),Toast.LENGTH_LONG).show();
                            }
                        }else {
                            listener.onFailed(new BaseResponse(mIneedResponse.code(),mIneedResponse.message()));
                        }
                    }
                });
    }

    public void formSubnodeRequest(MainService s, String j, Response<IneedResponse> mIneedResponse, int requestType, ApiCompleteListener listener){
        s.FormSubnodeRequest(j)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<IneedResponse>>() {
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
                    public void onNext(Response<IneedResponse> ineedResponseResponse) {
                        if (ineedResponseResponse.body().getIq().getQuery().getErrorCode().equals("0")){
                            FormSendDoRequest formSend = new FormSendDoRequest();
                            FormSendDoRequest.IqBean iq = new FormSendDoRequest.IqBean();
                            FormSendDoRequest.IqBean.QueryBean query = new FormSendDoRequest.IqBean.QueryBean();
                            List<FormSendDoRequest.IqBean.QueryBean.NodesBean> nodes = new ArrayList<FormSendDoRequest.IqBean.QueryBean.NodesBean>();
                            FormSendDoRequest.IqBean.QueryBean.NodesBean node = new FormSendDoRequest.IqBean.QueryBean.NodesBean();
                            query.setRequestType(requestType);
                            query.setTerm(OfficialDeatail.getIq().getQuery().getFormData().getFormData().getZC21().getViewValue());
                            query.setTermUnit(OfficialDeatail.getIq().getQuery().getFormData().getFormData().getZC28().getViewValue());
                            query.setId(OfficialDeatail.getIq().getQuery().getId());
                            query.setDealType(0);
                            query.setSuggestion(suggestion+"(来自Android)");
                            query.setIsTrace(0);
                            query.setIsWait(0);
                            query.setIsReturnCurrentNode(0);
                            query.setType(OfficialDeatail.getIq().getQuery().getMap().getType());
                            query.setAttGUID(GUID);
                            node.setGUID(mIneedResponse.body().getIq().getQuery().getNodes().get(0).getGUID());
                            node.setId(mIneedResponse.body().getIq().getQuery().getNodes().get(0).getId());
                            node.setType(Integer.parseInt(mIneedResponse.body().getIq().getQuery().getNodes().get(0).getType()));
                            node.setName(ineedResponseResponse.body().getIq().getQuery().getItems().get(0).getValue());
                            node.setValue("Y"+ineedResponseResponse.body().getIq().getQuery().getItems().get(0).getKey());
                            node.setFigureID("");
                            node.setFigureName("");
                            node.setFigureType("");
                            node.setIsDefaultNode(false);
                            nodes.add(node);
                            query.setNodes(nodes);
                            iq.setNamespace("FormSendDoRequest");
                            iq.setQuery(query);
                            formSend.setIq(iq);
                            formSendDoRequest(s,gson.toJson(formSend),listener);
                            BaseApplication.log_say("MainModelImpl","FormSendDoRequest");
                        }else {
                            listener.onComplected(ineedResponseResponse.body());
                            Toast.makeText(UIUtils.getContext(),ineedResponseResponse.body().getIq().getQuery().getErrorMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void formSendDoRequest(MainService s,String j,ApiCompleteListener listener){
        s.FormSendDoRequest(j)
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
}
