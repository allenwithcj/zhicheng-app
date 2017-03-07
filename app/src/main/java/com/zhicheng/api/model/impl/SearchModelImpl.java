package com.zhicheng.api.model.impl;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.api.common.ServiceFactory;
import com.zhicheng.api.common.service.SearchService;
import com.zhicheng.api.model.SearchModel;
import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.SearchBaoClassifyResponse;
import com.zhicheng.bean.http.SearchResponse;
import com.zhicheng.common.URL;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchModelImpl implements SearchModel {
    @Override
    public void SearchDataList(String request, ApiCompleteListener listener) {
        SearchService mSearchService = ServiceFactory.createService(URL.HOST_URL_SERVER,SearchService.class);
        Observable.just(request)
                .switchMap(new Func1<String, Observable<Response<SearchResponse>>>() {
                    @Override
                    public Observable<Response<SearchResponse>> call(String s) {
                        return mSearchService.getSearchList(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<SearchResponse>>() {
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
                    public void onNext(Response<SearchResponse> searchResponseResponse) {
                        if (searchResponseResponse.isSuccessful()){
                            listener.onComplected(searchResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(searchResponseResponse.code(),searchResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void SearchBaoClassifyList(String p1, ApiCompleteListener listener) {
        SearchService mSearchService = ServiceFactory.createService(URL.HOST_URL_SERVER_ZHICHENG,SearchService.class);
        mSearchService.getBaoClassifyList(p1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<SearchBaoClassifyResponse>>() {
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
                    public void onNext(Response<SearchBaoClassifyResponse> searchBaoClassifyResponseResponse) {
                        if (searchBaoClassifyResponseResponse.isSuccessful()){
                            listener.onComplected(searchBaoClassifyResponseResponse.body());
                        }else {
                            listener.onFailed(new BaseResponse(searchBaoClassifyResponseResponse.code(),searchBaoClassifyResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelSearch() {

    }
}
