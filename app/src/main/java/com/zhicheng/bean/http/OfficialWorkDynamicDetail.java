package com.zhicheng.bean.http;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Donson on 2017/1/15.
 */

/**
 * 工作圈头像
 * 昵称
 * 文字内容
 * 图片或其他媒体类内容
 * 赞
 * 评论人昵称
 *
 */

public class OfficialWorkDynamicDetail{

    private String img;
    private String name;
    private String content;
    private List<String> image_content;
    private int praise;
    private String location;
    private String time;

    public OfficialWorkDynamicDetail(){}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImage_content() {
        return image_content;
    }

    public void setImage_content(List<String> image_content) {
        this.image_content = image_content;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
