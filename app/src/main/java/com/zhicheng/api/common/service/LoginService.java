package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donson on 2017/2/14.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<LoginResponse>> loginRequest(@Field("json") String rBody);
}
