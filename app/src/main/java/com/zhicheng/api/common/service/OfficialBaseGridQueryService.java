package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.bean.http.OfficialQueyResponse;
import com.zhicheng.bean.http.PersonQueryResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/2.
 */

public interface OfficialBaseGridQueryService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialQueyResponse>> query(@Field("json") String rBody);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialBaseGridDetailResponse>> queryDetail(@Field("json") String rBody);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<PersonQueryResponse>> queryByCondition(@Field("json") String rBody);
}
