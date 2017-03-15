package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/15.
 */

public interface LocationModel {
    void upLocation(String lq, ApiCompleteListener listener);
}
