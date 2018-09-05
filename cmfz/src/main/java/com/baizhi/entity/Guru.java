package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by aina on 2018/9/5.
 */
public class Guru implements Serializable {
    private int id;
    private String name;
    private String dharmaName;
    private String status;
    private String phoneNum;
    private String photeImg;

    public Guru() {
    }

    public Guru(int id, String name, String dharmaName, String status, String phoneNum, String photeImg) {
        this.id = id;
        this.name = name;
        this.dharmaName = dharmaName;
        this.status = status;
        this.phoneNum = phoneNum;
        this.photeImg = photeImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoteImg() {
        return photeImg;
    }

    public void setPhoteImg(String photeImg) {
        this.photeImg = photeImg;
    }

    @Override
    public String toString() {
        return "Guru{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", status='" + status + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", photeImg='" + photeImg + '\'' +
                '}';
    }
}
