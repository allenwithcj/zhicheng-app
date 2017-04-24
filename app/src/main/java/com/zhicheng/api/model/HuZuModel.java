package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by lwl on 2017/4/16.
 */

public interface HuZuModel {
    void queryHuZu(String lq, ApiCompleteListener listener);
    void queryHuZuName(String lq, ApiCompleteListener listener);
}
