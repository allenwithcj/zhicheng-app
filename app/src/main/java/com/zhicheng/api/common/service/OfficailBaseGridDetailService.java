package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/2.
 */

public interface OfficailBaseGridDetailService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialBaseGridDetailResponse>> queryDetail(@Field("json") String rBody);
}

