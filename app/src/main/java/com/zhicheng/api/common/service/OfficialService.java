package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.AnnoucementDetailsResponse;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.NoticeResponse;
import com.zhicheng.bean.http.OfficialDealResponse;
import com.zhicheng.bean.http.OfficialDetailResponse;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.bean.http.PersonalDynamicResponse;
import com.zhicheng.bean.json.PersonalDynamicRequest;

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

    /**
     * 工作动态获取
     *
     * @param rBody 手动构建
     * @return OfficialWorkDynamicList
     */

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<OfficialWorkDynamicList>> getOfficialWorkDynamic(@Field("json") String rBody);

    /**
     * 新增工作动态
     *
     * @param rBody 手动构建
     * @return 通用响应
     */
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<CommonResponse>> upOfficialWorkDynamic(@Field("json") String rBody);

    //通知公告
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<NoticeResponse>> getNotice(@Field("json") String rBody);

    //通知详情
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<AnnoucementDetailsResponse>> queryNewsDetail(@Field("json") String rBody);


    //工作动态详情
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<PersonalDynamicResponse>> loadDynamicDetail(@Field("json") String rBody);

}
