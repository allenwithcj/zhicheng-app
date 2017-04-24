package com.zhicheng.api.view;

/**
 * Created by Donson on 2017/2/14.
 */

public interface ExperienceView {
    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void queryExpResponse(Object result);

    void loadExpResponse(Object result);

    void upExpResponse(Object result);
}
