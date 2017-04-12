package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.CaseGridResponse;
import com.zhicheng.bean.http.OfficialBaseGridResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donson on 2017/1/6.
 */

public interface OfficialBaseGridService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CaseGridResponse>> loadGridNames(@Field("json") String rBody);

}
