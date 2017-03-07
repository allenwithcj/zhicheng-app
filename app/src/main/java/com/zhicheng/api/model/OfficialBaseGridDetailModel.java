package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/2.
 */

public interface OfficialBaseGridDetailModel {

    void loadDetail(String json, ApiCompleteListener listener);

    void cancelLoading();
}
