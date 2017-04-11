package com.zhicheng.api.view;

/**
 * Created by hp on 2017/3/15.
 */

public interface LocationView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);
}
