package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.SearchBaoClassifyResponse;
import com.zhicheng.bean.http.SearchResponse;

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
 * Created by Donson on 2017/1/17.
 */

public interface SearchService {
    @GET("api.php")
    Observable<Response<SearchResponse>> getSearchList(@Query("search") String request);

    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<SearchBaoClassifyResponse>> getBaoClassifyList(@Field("json") String rBody);
}
