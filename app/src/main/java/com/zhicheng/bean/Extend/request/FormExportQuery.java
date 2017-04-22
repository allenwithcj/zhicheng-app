package com.zhicheng.bean.Extend.request;

/**
 * Created by Donson on 2017/2/20.
 */

public class FormExportQuery {

    /**
     * iq : {"namespace":"FormExportRequest","query":{"requestType":3,"id":"AA4F2310-C7CF-6346-99BB-ED88CD3AF3EF"}}
     */

    private int requestType;
    private String id;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
