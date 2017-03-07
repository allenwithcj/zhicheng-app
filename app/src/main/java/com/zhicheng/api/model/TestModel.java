package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by Donson on 2016/12/30.
 */

public interface TestModel {
    /**
     * 获取测试数据
     */
    void loadTestList(String json, ApiCompleteListener listener);

    void cancelLoading();
}
