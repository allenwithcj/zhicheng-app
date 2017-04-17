package com.zhicheng.bean.http;

/**
 * Created by lwl on 2017/4/17.
 */

public class JudgementLocationResponse {

    /**
     * zj : 雉城街道
     * cj : 高阳桥社区
     * wg : 古苑新村（第三网格）
     * str : 雉城街道/高阳桥社区/古苑新村（第三网格）
     * success : true
     */

    private String zj;
    private String cj;
    private String wg;
    private String str;
    private boolean success;

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj;
    }

    public String getWg() {
        return wg;
    }

    public void setWg(String wg) {
        this.wg = wg;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
