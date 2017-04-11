package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/15.
 */

public interface CaseQueryModel {
    void caseQuery(String lq, ApiCompleteListener listener);
}
