package com.zhicheng.api.presenter;

/**
 * Created by Donson on 2017/1/6.
 */

public interface OfficialBaseGridPresenter {

    void loadOfficialBaseGrid(String street, String communicate, String grid);

    void cancelLoading();
}
