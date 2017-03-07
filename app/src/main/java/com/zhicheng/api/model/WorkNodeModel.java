package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

/**
 * Created by hp on 2017/3/2.
 */

public interface WorkNodeModel {
    void loadWorkNodes(String s, ApiCompleteListener listener);
    void sendWorkNodes(String s, ApiCompleteListener listener);
    void updateWorkNodes(String s, ApiCompleteListener listener);
    void deleteWorkNodes(String s, ApiCompleteListener listener);
    void cancelLoading();
}
