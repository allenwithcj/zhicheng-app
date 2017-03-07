package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

import okhttp3.RequestBody;

/**
 * Created by Donson on 2017/2/14.
 */

public interface LoginModel {
    /**
     * 登陆
     *
     */
    void login(String lq, ApiCompleteListener listener);
}
