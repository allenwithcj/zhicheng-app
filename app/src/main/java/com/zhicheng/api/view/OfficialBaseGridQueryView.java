package com.zhicheng.api.view;

/**
 * Created by hp on 2017/3/2.
 */

public interface OfficialBaseGridQueryView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);

    void addData(Object result);
}
