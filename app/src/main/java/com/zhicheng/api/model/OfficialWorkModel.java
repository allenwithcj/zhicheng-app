package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by Donson on 2016/12/30.
 */

public interface OfficialWorkModel {
    /**
     * 获取测试数据
     */
    void loadWorkDynamic(String work, ApiCompleteListener listener);

    void upWorkDynamic(String work,ApiCompleteListener listener);

    void cancelLoading();
}
