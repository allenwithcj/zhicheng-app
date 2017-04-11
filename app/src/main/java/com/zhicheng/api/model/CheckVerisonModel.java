package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

import java.util.Map;

/**
 * Created by hp on 2017/3/24.
 */

public interface CheckVerisonModel {
    void getApps(Map<String, String> map, ApiCompleteListener apiCompleteListener);
}
