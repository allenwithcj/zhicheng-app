package com.zhicheng.api.view;

/**
 * Created by Donson on 2017/1/2.
 */

public interface MainView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);

}
