package com.zhicheng.api.presenter;

import com.zhicheng.bean.http.OfficialDetailResponse;

import java.util.List;

/**
 * Created by Donson on 2017/1/4.
 */

public interface OfficialPresenter {
    void loadNoFinish(String j, int start);

    void loadDetail(String js);

    void loadDynamic(String dyn);

    void upDeal(List<String> imgs, String jFile, String suggest, OfficialDetailResponse officialDetailResponse, String GUID);

    void cancelLoading();

    void LoadNotice(String n);
}
