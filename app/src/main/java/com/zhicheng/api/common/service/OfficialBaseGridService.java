package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.OfficialBaseGridResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donson on 2017/1/6.
 */

public interface OfficialBaseGridService {
    @GET("api.php")
    Observable<Response<OfficialBaseGridResponse>> getOfficialCommonData(@Query("street") String street, @Query("communicate") String communicate, @Query("grid") String grid);
}
