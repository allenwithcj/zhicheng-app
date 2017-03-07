package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;
import com.zhicheng.bean.http.OfficialDetailResponse;

import java.util.List;

/**
 * Created by Donson on 2017/1/4.
 */

public interface OfficialModel {

    void loadOfficial(String nofinish, ApiCompleteListener listener);

    void loadOfficialDetail(String j, ApiCompleteListener listener);

    void loadOfficialDynamic(String dyn, ApiCompleteListener listener);

    void upDeal(List<String> imgs, String jFile, String GUID, String suggest, OfficialDetailResponse officialDetailResponse, ApiCompleteListener listener);

    void cancelLoading();

    void loadNotice(String n, ApiCompleteListener listener);//通知公告
}
