package com.zhicheng.api.presenter;

/**
 * Created by lwl on 2017/4/16.
 */

public interface HuZuPresenter {
    void queryHuZu(String s, int start);

    void queryHuZuName(String s);

    void fuzzySearchHuzu(String s, int start);
}
