package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.NoticeResponse;
import com.zhicheng.bean.http.OfficialDealResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Donson on 2017/1/4.
 */

public interface OfficialService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialResponse>> getOfficial(@Field("json") String rBody);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialDetailResponse>> getOfficialDetail(@Field("json") String rBody);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialDealResponse>> upDealRequest(@Field("json") String rBody);

    @Multipart
    @POST("/servlet/uploadAttachmentServlet")
    Observable<Response<CommonResponse>> UpDealFile(@Part("json") RequestBody rBody, @Part("file") MultipartBody body);

    @GET("api.php")
    Observable<Response<OfficialWorkDynamicList>> getOfficialWorkDynamic(String j);

    //通知公告
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<NoticeResponse>> getNotice(@Field("json") String rBody);
}
