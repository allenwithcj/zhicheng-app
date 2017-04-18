package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/1.
 */

public interface OfficialBaseGridQueryModel {

    void query(String json, ApiCompleteListener listener);

    void loadDetail(String j, ApiCompleteListener listener);

    void queryByCondition(String js, ApiCompleteListener listener);

    void cancelLoading();
}
