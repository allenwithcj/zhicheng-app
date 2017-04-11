package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/11.
 */

public interface ContactModel {
    void loadContacts(String lq, ApiCompleteListener listener);
}
