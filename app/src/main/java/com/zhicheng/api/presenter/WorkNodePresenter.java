package com.zhicheng.api.presenter;

import com.zhicheng.api.ApiCompleteListener;

import java.util.List;

/**
 * Created by hp on 2017/3/2.
 */

public interface WorkNodePresenter {
    void loadWorkNodes(String s, int start);

    void sendWorkNodes(String jFile, List<String> imgs, String nodes, String attGUID, String GUID, ApiCompleteListener listener);

    void updateWorkNodes(String s);

    void deleteWorkNodes(String s);

    void cancelLoading();
}
