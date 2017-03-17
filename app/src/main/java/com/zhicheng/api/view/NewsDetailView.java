package com.zhicheng.api.view;

/**
 * Created by hp on 2017/3/17.
 */

public interface NewsDetailView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshData(Object result);
}
