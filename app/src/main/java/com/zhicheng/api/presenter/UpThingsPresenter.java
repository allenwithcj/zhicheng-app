package com.zhicheng.api.presenter;

import com.zhicheng.api.ApiCompleteListener;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by Donson on 2017/2/15.
 */

public interface UpThingsPresenter {
    void UpThings(int requestType,String GUID,String j,String jFile,List<String> imgs);

    void UpSimpleFile(String guid,List<String> imgs,String json);
}
