package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.CaseQueryResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/15.
 */

public interface CaseQueryService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CaseQueryResponse>> upLocation(@Field("json") String rBody);
}
