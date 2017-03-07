package com.zhicheng.bean.http;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Donson on 2017/1/2.
 */

public class MainBaoGroundResponse implements Serializable {
    private long NumberId;
    private String Name;
    private String Time;
    private List<String> img_url;
    private String Content;
    private String OfficialBack;
    private int Comment;
    private int agree;

    public long getNumberId() {
        return NumberId;
    }

    public void setNumberId(long numberId) {
        NumberId = numberId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public List<String> getImg_url() {
        return img_url;
    }

    public void setImg_url(List<String> img_url) {
        this.img_url = img_url;
    }

    public String getOfficialBack() {
        return OfficialBack;
    }

    public void setOfficialBack(String officialBack) {
        OfficialBack = officialBack;
    }

    public int getComment() {
        return Comment;
    }

    public void setComment(int comment) {
        Comment = comment;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }
}
