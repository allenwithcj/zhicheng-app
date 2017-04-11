package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.AllVersionResponse;
import com.zhicheng.bean.http.VersionResponse;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/24.
 */

public interface CheckVerisonService {
    @FormUrlEncoded
    @POST("apiv1/app/viewGroup")
    Observable<Response<AllVersionResponse>> getApps(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("apiv1/app/view")
    Observable<Response<VersionResponse>> checkVersion(@FieldMap Map<String, String> map);

}
