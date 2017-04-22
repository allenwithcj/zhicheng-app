package com.zhicheng.api.Extend.service;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Donson on 2017/4/21.
 */

public interface BaseService {

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<String>> connect(@Field("json") String rBody);

    @Multipart
    @POST("/servlet/uploadAttachmentServlet")
    Observable<Response<String>> connectImage(@Part("json") RequestBody rBody, @Part("file") MultipartBody body);

}
