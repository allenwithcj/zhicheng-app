package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by Donson on 2017/1/6.
 */

public interface OfficialBaseGridModel {

    void loadOfficial(String street, String communicate, String grid, ApiCompleteListener listener);

    void loadOfficial(String street, String communicate, ApiCompleteListener listener);

    void loadOfficial(String street, ApiCompleteListener listener);

    void cancelLoading();


}
