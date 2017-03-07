package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.ui.activity.OfficialWorkDynamic;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donson on 2016/12/30.
 */

public interface OfficialDynamicService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> getWorkDy(@Field("json") String work);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> upWorkDy(@Field("json") String work);
}
