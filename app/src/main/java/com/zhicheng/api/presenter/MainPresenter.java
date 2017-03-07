package com.zhicheng.api.presenter;

/**
 * Created by Donson on 2017/1/2.
 */

public interface MainPresenter {

    void loadMain(String json);

    void loadPersonal(String personal);

    void cancelLoading();
}
