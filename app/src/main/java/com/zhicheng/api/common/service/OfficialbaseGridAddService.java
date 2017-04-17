package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.JudgementLocationResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hp on 2017/3/1.
 */

public interface OfficialbaseGridAddService {

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> addDate(@Field("json") String rBody);

    @GET("/cigservice/rest/services/4/intersectFeaturesByXY")
    Observable<Response<JudgementLocationResponse>> judgmentLocation(@Query("x") String x, @Query("y") String y);
}
