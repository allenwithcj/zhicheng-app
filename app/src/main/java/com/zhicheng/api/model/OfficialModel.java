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

    //工作动态获取
    void loadOfficialDynamic(String dyn,ApiCompleteListener listener);
    //新增工作动态
    void upOfficialDynamic(String dyn,List<String> imgs,String jFile,String mLocationSite,String GUID,ApiCompleteListener listener);

    void upDeal(List<String> imgs, String jFile, String GUID, String suggest, OfficialDetailResponse officialDetailResponse,String type, ApiCompleteListener listener);

    void cancelLoading();

    void loadNotice(String n, ApiCompleteListener listener);//通知公告
    //查询通知公告详情
    void queryNewsDetail(String j, ApiCompleteListener listener);
}
