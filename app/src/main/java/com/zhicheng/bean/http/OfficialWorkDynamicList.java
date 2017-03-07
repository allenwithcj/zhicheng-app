package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialWorkDynamicList extends BaseResponse {

    private int start;
    private List<OfficialWorkDynamicDetail> details;

    public OfficialWorkDynamicList(){}

    public OfficialWorkDynamicList(int code,String msg){
        super(code,msg);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<OfficialWorkDynamicDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OfficialWorkDynamicDetail> details) {
        this.details = details;
    }

}
