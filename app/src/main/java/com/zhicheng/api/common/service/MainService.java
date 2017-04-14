package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.ExperienceCommonResponse;
import com.zhicheng.bean.http.IneedResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donson on 2017/1/2.
 */

public interface MainService {
    @GET("api.php")
    Observable<Response<BaseResponse>> getPersonal(@Query("personal") String personal);

    //首页
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> getMainData(@Field("json") String rBody);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> UpThings(@Field("json") String rBody);

    @Multipart
    @POST("/servlet/uploadAttachmentServlet")
    Observable<Response<CommonResponse>> UpSimpleFile(@Part("json") RequestBody rBody, @Part("file") MultipartBody body);

    @Multipart
    @POST("/servlet/uploadAttachmentServlet")
    Observable<Response<CommonResponse>> UpFile(@Part("json") RequestBody rBody, @Part("file") MultipartBody body);

    //表单处理出口列表请求
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<IneedResponse>> FormExportRequest(@Field("json") String rBody);

    //表单节点处理请求
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<IneedResponse>> FormNodeRequest(@Field("json") String rBody);

    //表单处理人/岗位列表请求
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<IneedResponse>> FormSubnodeRequest(@Field("json") String rBody);

    //表单处理请求
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> FormSendDoRequest(@Field("json") String rBody);

    //表单处理请求
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<ExperienceCommonResponse>> sFormSendDoRequest(@Field("json") String rBody);
}
