package com.zhicheng.api.model;

import com.zhicheng.api.ApiCompleteListener;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Donson on 2017/1/2.
 */

public interface MainModel {
    /**
     * 获取主页各节点数据
     *
     * @param json
     * @param listener
     */
    void loadMainList(String json, ApiCompleteListener listener);

    /**
     * 获取个人信息
     *
     * @param personal
     * @param listener
     */
    void loadPersonal(String personal,ApiCompleteListener listener);

    /**
     * 事项上报
     *
     * @param j json数据
     * @param imgs 图片数据
     * @param jFile 文件附带json
     */
    void UpThings(int requestType,String GUID,String j,String jFile,List<String> imgs, ApiCompleteListener listener);

    void upSimpleFile(String guid,List<String> imgs,String json,ApiCompleteListener listener);
    void cancelLoading();

}
