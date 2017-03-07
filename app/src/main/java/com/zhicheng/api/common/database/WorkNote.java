package com.zhicheng.api.common.database;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Donson on 2017/2/9.
 */

public class WorkNote extends RealmObject implements Serializable {

    @PrimaryKey
    private long id;
    private String uID;
    private Date createTime;
    private String content;
    private String sendPeopel;
    private String sendWork;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getSendPeopel() {
        return sendPeopel;
    }

    public void setSendPeopel(String sendPeopel) {
        this.sendPeopel = sendPeopel;
    }

    public String getSendWork() {
        return sendWork;
    }

    public void setSendWork(String sendWork) {
        this.sendWork = sendWork;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
