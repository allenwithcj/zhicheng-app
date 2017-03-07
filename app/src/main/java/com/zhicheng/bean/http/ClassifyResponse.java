package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/2/13.
 */

public class ClassifyResponse extends BaseResponse {

    private String mTitleImage;
    private String mDesc;
    private String mOrigin;
    private String upTime;

    public ClassifyResponse(){}

    public ClassifyResponse(int code,String msg){
        super(code,msg);
    }

    public String getmTitleImage() {
        return mTitleImage;
    }

    public void setmTitleImage(String mTitleImage) {
        this.mTitleImage = mTitleImage;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmOrigin() {
        return mOrigin;
    }

    public void setmOrigin(String mOrigin) {
        this.mOrigin = mOrigin;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}
