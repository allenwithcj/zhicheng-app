package com.zhicheng.api.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.zhicheng.BaseApplication;
import com.zhicheng.utils.common.NetworkUtils;
import com.zhicheng.utils.common.UIUtils;

import java.io.File;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceFactory {
    private volatile static OkHttpClient okHttpClient;
    private volatile static Retrofit mRetrofit;
    private static final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 20;
    private static final int DEFAULT_MAX_AGE = 60 * 60;
    private static final int DEFAULT_MAX_STALE_ONLINE = DEFAULT_MAX_AGE * 24;
    private static final int DEFAULT_MAX_STALE_OFFLINE = DEFAULT_MAX_AGE * 24 * 7;

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    File cacheFile = new File(BaseApplication.getApplication().getCacheDir(), "responses");
                    Cache cache = new Cache(cacheFile, DEFAULT_CACHE_SIZE);
                    okHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(REQUEST_INTERCEPTOR)
                            .addNetworkInterceptor(RESPONSE_INTERCEPTOR)
                            .addInterceptor(LoggingInterceptor)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static Retrofit getRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .client(getOkHttpClient())
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    private static final Interceptor REQUEST_INTERCEPTOR = chain -> {
        Request.Builder request = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) UIUtils.getContext().getSharedPreferences("cookies", Context.MODE_PRIVATE).getStringSet("cookie", new HashSet<>());
        int maxStale = DEFAULT_MAX_STALE_ONLINE;
        //向服务期请求数据缓存1个小时
        CacheControl tempCacheControl = new CacheControl.Builder()
                .noCache()
                .build();
        request.cacheControl(tempCacheControl);
        for (String cookie : preferences) {
            if (!cookie.equals("")){
                request.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(request.build());
    };

    private static final Interceptor RESPONSE_INTERCEPTOR = chain -> {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        if (originalResponse.header("Set-Cookie") != null && !originalResponse.header("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SharedPreferences sp = UIUtils.getContext().getSharedPreferences("cookies", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putStringSet("cookie", cookies);
            editor.commit();
        }
        int maxAge;
        // 缓存的数据
        if (!NetworkUtils.isConnected(BaseApplication.getApplication())) {
            maxAge = DEFAULT_MAX_STALE_OFFLINE;
        } else {
            maxAge = DEFAULT_MAX_STALE_ONLINE;
        }

        return originalResponse.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=" + maxAge)
                .build();
    };

    private static final Interceptor LoggingInterceptor = chain -> {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Log.i("okhttp:", String.format("Sending request %s on %s%n%s%s", request.url(), chain.connection(), request.headers(), request.body()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Log.i("okhttp:", String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    };

    public static <T> T createService(String baseUrl, Class<T> serviceClazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClazz);
    }

    public static <T> T createServiceString(String baseUrl, Class<T> serviceClazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClazz);
    }
}