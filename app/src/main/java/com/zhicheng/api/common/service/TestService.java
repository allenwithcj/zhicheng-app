package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donson on 2016/12/30.
 */

public interface TestService {
    @Multipart
    @POST("/servlet/mobileServlet")
    Observable<Response<String>> getTestData(@Part("json") RequestBody rBody);
}
