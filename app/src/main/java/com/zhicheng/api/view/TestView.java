package com.zhicheng.api.view;

/**
 * Created by Donson on 2016/12/30.
 */

public interface TestView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void updateView(Object result);
}
