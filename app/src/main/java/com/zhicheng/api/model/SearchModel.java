package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by Donson on 2017/1/17.
 */

public interface SearchModel {

    void SearchDataList(String request, ApiCompleteListener listener);

    void SearchBaoClassifyList(String p1, ApiCompleteListener listener);

    void cancelSearch();
}
