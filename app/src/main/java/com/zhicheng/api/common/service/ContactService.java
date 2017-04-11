package com.zhicheng.api.common.service;

import com.zhicheng.bean.http.AddressBookResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hp on 2017/3/11.
 */

public interface ContactService {
    @FormUrlEncoded
    @POST("/servlet/mobileServlet")
    Observable<Response<AddressBookResponse>> loadContacts(@Field("json") String rBody);
}
