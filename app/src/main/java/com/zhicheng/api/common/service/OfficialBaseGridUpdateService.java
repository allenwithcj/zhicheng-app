package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.CommonResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/2.
 */

public interface OfficialBaseGridUpdateService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> updateDate(@Field("json") String rBody);
}
