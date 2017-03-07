package com.zhicheng.api.presenter;

/**
 * Created by Donson on 2016/12/30.
 */

public interface OfficialDynamicPresenter {


    void loadWork(String s);

    /**
     * 取消加载
     */
    void cancelLoading();
}
