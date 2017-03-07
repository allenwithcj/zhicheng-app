package com.zhicheng.api.presenter;

/**
 * Created by hp on 2017/3/2.
 */

public interface WorkNodePresenter {
    void loadWorkNodes(String s);

    void sendWorkNodes(String s);

    void updateWorkNodes(String s);

    void deleteWorkNodes(String s);

    void cancelLoading();
}
