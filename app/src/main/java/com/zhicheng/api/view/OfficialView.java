package com.zhicheng.api.view;

/**
 * Created by Donson on 2017/1/4.
 */

public interface OfficialView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);

    void addData(Object result);
}
