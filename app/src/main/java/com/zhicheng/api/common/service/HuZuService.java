package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.PersonMsgMaResponse;
import com.zhicheng.bean.http.PersonMsgResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lwl on 2017/4/16.
 */

public interface HuZuService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<PersonMsgMaResponse>> queryHuzu(@Field("json") String rBody);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<PersonMsgResponse>> queryHuzuName(@Field("json") String rBody);

}
