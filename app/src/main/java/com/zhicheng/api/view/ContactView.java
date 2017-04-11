package com.zhicheng.api.view;

/**
 * Created by hp on 2017/3/11.
 */

public interface ContactView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);

    void addDate(Object result);
}
