package com.zhicheng.api.presenter;

/**
 * Created by Donson on 2016/12/30.
 */

public interface TestPresenter {
    void loadTest(String json);

    /**
     * 取消加载
     */
    void cancelLoading();
}
