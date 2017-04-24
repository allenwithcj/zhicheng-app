package com.zhicheng.api.view;

/**
 * Created by lwl on 2017/4/16.
 */

public interface HuZuView {
    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void refreshHuZuResponse(Object result);

    void loadHuZuResponse(Object result);
}
