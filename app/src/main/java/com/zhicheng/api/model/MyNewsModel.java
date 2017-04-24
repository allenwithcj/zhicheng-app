package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.bean.http.OfficialDetailResponse;

import java.util.List;

/**
 * Created by Donson on 2017/1/2.
 */

public interface MyNewsModel {

    void readMyNews(String s,ApiCompleteListener listener);

    void cancelLoading();

}
