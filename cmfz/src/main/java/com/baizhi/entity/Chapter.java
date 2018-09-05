package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by aina on 2018/8/31.
 */
public class Chapter implements Serializable{
    private int id;//编号
    private String title;//名称
    private String duration;//时长
    private String size;//大小 MB
    private String audioPath;//音频路径
    private int parentId;//章节所属专辑id

    public Chapter() {
    }

    public Chapter(int id, String title, String duration, String size, String audioPath,int parentId) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.audioPath = audioPath;
        this.parentId=parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
