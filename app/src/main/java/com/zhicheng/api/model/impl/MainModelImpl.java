package com.zhicheng.api.model.impl;

import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.MainService;
import com.zhicheng.api.model.MainModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.IneedResponse;
import com.zhicheng.bean.json.FormExportRequest;
import com.zhicheng.bean.json.FormNodeRequest;
import com.zhicheng.bean.json.FormSendDoRequest;
import com.zhicheng.bean.json.FormSubnodeRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.common.URL;
import com.zhicheng.utils.common.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.net.URLEncoder;
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
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/1/2.
 */

public class MainModelImpl implements MainModel {

    private Gson gson = new Gson();
    private String GUID = "";

    @Override
    public void loadMainList(String json, ApiCompleteListener listener) {
        MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,MainService.class);
        mMainService.getMainData(json)
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
    public void loadPersonal(String personal, ApiCompleteListener listener) {
        MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER,MainService.class);
        mMainService.getPersonal(personal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<BaseResponse>>() {

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
                    public void onNext(Response<BaseResponse> mainResponseResponse) {
                        if (mainResponseResponse.isSuccessful()){
                            listener.onComplected(mainResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(mainResponseResponse.code(),mainResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void UpThings(int requestType,String GUID,String j,String jFile,List<String> imgs, ApiCompleteListener listener) {
        MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,MainService.class);
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        this.GUID = GUID;
        Observable.from(imgs)
                .map(s -> {
                    File file = new File(s);
                    builder.addFormDataPart("file",file.getName(),RequestBody.create(MultipartBody.FORM,file));
                    return s;
                }).last()
                .flatMap(new Func1<String, Observable<Response<CommonResponse>>>() {
                    @Override
                    public Observable<Response<CommonResponse>> call(String s) {
                        RequestBody body = RequestBody.create(MediaType.parse("application/json"),jFile);
                        return mMainService.UpFile(body,builder.build());
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
//                            listener.onComplected(commonResponseResponse.body());
//                            RequestBody json = RequestBody.create(MediaType.parse("application/json"),j);
                            if (commonResponseResponse.body().getIq().getQuery().getErrorCode() == (0)){
                                upThings(mMainService,j,requestType,listener);
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

    @Override
    public void upSimpleFile(String guid,List<String> imgs,String json, ApiCompleteListener listener) {
        MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,MainService.class);
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        Observable.from(imgs)
                .map(s -> {
                    File file = new File(s);
                    builder.addFormDataPart("file",file.getName(),RequestBody.create(MultipartBody.FORM,file));
                    return s;
                }).last()
                .flatMap(new Func1<String, Observable<Response<CommonResponse>>>() {
                    @Override
                    public Observable<Response<CommonResponse>> call(String s) {
                        RequestBody body = RequestBody.create(MediaType.parse("application/json"),json);
                        return mMainService.UpFile(body,builder.build());
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
                                listener.onComplected(commonResponseResponse.body());
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

    @Override
    public void cancelLoading() {

    }

    private void upThings(MainService s,String j,int requestType,ApiCompleteListener listener){
        s.UpThings(j)
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
//                            listener.onComplected(commonResponseResponse.body());
                            if (commonResponseResponse.body().getIq().getQuery().getErrorCode() == 0){
                                formExportRequest(requestType,listener);
                                BaseApplication.log_say("MainModelImpl","FormExportRequest");
                            }else if (commonResponseResponse.body().getIq().getQuery().getErrorCode() == -1){
                                BaseApplication.checkLogin();
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

    public void formExportRequest(int requestType,ApiCompleteListener listener){
        MainService mMainService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,MainService.class);
        FormExportRequest feq = new FormExportRequest();
        FormExportRequest.IqBean iq = new FormExportRequest.IqBean();
        FormExportRequest.IqBean.QueryBean query = new FormExportRequest.IqBean.QueryBean();
        iq.setNamespace("FormExportRequest");
        query.setId(Constant.SID);
        query.setRequestType(3);
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
                                query.setId(Constant.SID);
                                query.setChukouID(ineedResponseResponse.body().getIq().getQuery().getItems().get(0).getKey());
                                iq.setNamespace("FormNodeRequest");
                                iq.setQuery(query);
                                formNode.setIq(iq);
                                formNodeRequest(mMainService,gson.toJson(formNode),requestType,listener);
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

    public void formNodeRequest(MainService s,String j,int requestType,ApiCompleteListener listener){
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
                    public void onNext(Response<IneedResponse> ineedResponseResponse) {
                        if (ineedResponseResponse.isSuccessful()){
                            if (ineedResponseResponse.body().getIq().getQuery().getErrorCode().equals("0")){
                                FormSubnodeRequest formSubnode = new FormSubnodeRequest();
                                FormSubnodeRequest.IqBean iq = new FormSubnodeRequest.IqBean();
                                FormSubnodeRequest.IqBean.QueryBean query = new FormSubnodeRequest.IqBean.QueryBean();
                                query.setRequestType(0);
                                query.setType(3);
                                query.setId(ineedResponseResponse.body().getIq().getQuery().getNodes().get(0).getId());
                                query.setTableName("");
                                query.setTableID("");
                                query.setWfInfoID(Constant.SID);
                                query.setGrid("");
                                iq.setNamespace("FormSubnodeRequest");
                                iq.setQuery(query);
                                formSubnode.setIq(iq);
                                if (ineedResponseResponse.body().getIq().getQuery().getNodes().get(0).getType().equals("4")){
                                    FormSendDoRequest formSend = new FormSendDoRequest();
                                    FormSendDoRequest.IqBean iq2 = new FormSendDoRequest.IqBean();
                                    FormSendDoRequest.IqBean.QueryBean query2 = new FormSendDoRequest.IqBean.QueryBean();
                                    List<FormSendDoRequest.IqBean.QueryBean.NodesBean> nodes = new ArrayList<FormSendDoRequest.IqBean.QueryBean.NodesBean>();
                                    FormSendDoRequest.IqBean.QueryBean.NodesBean node = new FormSendDoRequest.IqBean.QueryBean.NodesBean();
                                    query2.setRequestType(5);
                                    query2.setTerm("");
                                    query2.setTermUnit("");
                                    query2.setId(Constant.SID);
                                    query2.setDealType(0);
                                    query2.setSuggestion("(来自Android)");
                                    query2.setIsTrace(0);
                                    query2.setIsWait(0);
                                    query2.setIsReturnCurrentNode(0);
                                    node.setGUID(ineedResponseResponse.body().getIq().getQuery().getNodes().get(0).getGUID());
                                    node.setId(ineedResponseResponse.body().getIq().getQuery().getNodes().get(0).getId());
                                    node.setType(Integer.parseInt(ineedResponseResponse.body().getIq().getQuery().getNodes().get(0).getType()));
                                    node.setName(ineedResponseResponse.body().getIq().getQuery().getNodes().get(0).getName());
                                    node.setFigureType("");
                                    node.setFigureID("");
                                    node.setFigureName("");
                                    node.setIsSendPost("0");
                                    nodes.add(node);
                                    query2.setNodes(nodes);
                                    iq2.setNamespace("FormSendDoRequest");
                                    iq2.setQuery(query2);
                                    formSend.setIq(iq2);
                                    formSendDoRequest(s,gson.toJson(formSend),listener);
                                }else {
                                    formSubnodeRequest(s,gson.toJson(formSubnode),requestType,listener);
                                }
                                BaseApplication.log_say("MainModelImpl","FormSubnodeRequest");
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

    public void formSubnodeRequest(MainService s,String j,int requestType,ApiCompleteListener listener){
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
                            query.setId(Constant.SID);
                            query.setDealType(0);
                            query.setSuggestion("null(Android)");
                            query.setIsTrace(0);
                            query.setIsWait(0);
                            query.setIsReturnCurrentNode(0);
                            node.setGUID(GUID);
                            node.setId(ineedResponseResponse.body().getIq().getQuery().getId());
                            node.setType(0);
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
