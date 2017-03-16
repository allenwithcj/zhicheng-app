package com.zhicheng.api.presenter;

import com.zhicheng.bean.http.OfficialDetailResponse;

import java.util.List;

/**
 * Created by Donson on 2017/1/4.
 */

public interface OfficialPresenter {
    void loadNoFinish(String j, int start);

    void loadDetail(String js);

    //工作动态
    void loadDynamic(String dyn,int start);
    //新增工作动态
    void upDynamic(String dyn,List<String> imgs, String jFile,String mLocationSite,String GUID);

    void upDeal(List<String> imgs, String jFile, String suggest, OfficialDetailResponse officialDetailResponse, String GUID);

    void cancelLoading();

    void LoadNotice(String n);
}
