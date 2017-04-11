package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchResponse extends BaseResponse {

    private List<String> DataList;
    private List<ClassifyResponse> classifyResponses;

    public SearchResponse() {
    }

    public SearchResponse(int code, String msg) {
        super(code, msg);
    }

    public List<String> getDataList() {
        return DataList;
    }

    public void setDataList(List<String> dataList) {
        DataList = dataList;
    }

    public List<ClassifyResponse> getClassifyResponses() {
        return classifyResponses;
    }

    public void setClassifyResponses(List<ClassifyResponse> classifyResponses) {
        this.classifyResponses = classifyResponses;
    }
}
