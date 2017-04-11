package com.zhicheng.api.common.service;

import okhttp3.ResponseBody;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by hp on 2017/3/20.
 */

public interface DownService {
    @Streaming
    @POST("/servlet/mobileServlet")
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);
}
