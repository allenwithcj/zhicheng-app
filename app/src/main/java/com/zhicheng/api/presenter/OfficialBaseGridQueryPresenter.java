package com.zhicheng.api.presenter;

/**
 * Created by hp on 2017/3/2.
 */

public interface OfficialBaseGridQueryPresenter {
    void query(String rBody, int start);

    void loadDetail(String js);
}
