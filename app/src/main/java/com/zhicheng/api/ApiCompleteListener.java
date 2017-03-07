package com.zhicheng.api;

import com.zhicheng.bean.http.BaseResponse;
import com.zhicheng.bean.http.LoginResponse;

/**
 * Created by Donson on 2016/12/30.
 */

public interface ApiCompleteListener {
    void onComplected(Object result);
    void onFailed(BaseResponse msg);
}
