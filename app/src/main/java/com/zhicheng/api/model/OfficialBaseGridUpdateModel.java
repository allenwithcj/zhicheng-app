package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/1.
 */

public interface OfficialBaseGridUpdateModel {
    void updateDate(String json, ApiCompleteListener listener);

    void cancelLoading();
}
