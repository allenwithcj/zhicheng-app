package com.zhicheng.api.view;

/**
 * Created by Donson on 2017/1/4.
 */

public interface MyNewsView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void readMyNewsResponse(Object result);

}
