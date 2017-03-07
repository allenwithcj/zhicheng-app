package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.PersonalLogMaResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/2.
 */

public interface WorkNodeService {

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> sendWorkNode(@Field("json") String rBody);


    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> updateWorkNode(@Field("json") String rBody);



    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<PersonalLogMaResponse>> loadWorkNode(@Field("json") String rBody);


    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> deleteWorkNode(@Field("json") String rBody);

}

