package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/1.
 */

public interface OfficialBaseGridAddModel {

    void addDate(String json, ApiCompleteListener listener);

    void judgmentLocation(String x,String y, ApiCompleteListener listener);
}
