package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by Donson on 2017/1/6.
 */

public interface OfficialBaseGridModel {

    void loadOfficialGridNames(String s, ApiCompleteListener listener);

    void cancelLoading();


}
