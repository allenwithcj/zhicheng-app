package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

import java.util.List;

/**
 * Created by Donson on 2017/1/2.
 */

public interface ExperienceModel {

    /**
     * 经验交流
     *
     * @param imgs  图片数据
     * @param jFile 文件附带json
     */
    void UpThings( String GUID,String jFile, List<String> imgs, ApiCompleteListener listener);

    void upExperience(int requestType, String json, ApiCompleteListener listener);

    void queryExp(String json, ApiCompleteListener listener);

    void queryExpDetail(String json, ApiCompleteListener listener);

    void cancelLoading();

}
