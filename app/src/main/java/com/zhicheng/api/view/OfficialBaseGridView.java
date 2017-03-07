package com.zhicheng.api.view;

/**
 * Created by Donson on 2017/1/6.
 */

public interface OfficialBaseGridView {

    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);
}
