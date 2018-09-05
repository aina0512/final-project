package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aina on 2018/9/5.
 */
public class User implements Serializable {
    private Integer id;//编号
    private String photoImg;//头像
    private String name;//姓名
    private String dharamName;//法名
    private String sex;//性别
    private String province;//省份
    private String city;//城市
    private String sign;//签名
    private String phoneNum;//手机号
    private String password;//密码
    private String salt;//私盐
    private String status;//状态
    private Date regisDate;//注册日期
    private Guru guru;//上师

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", photoImg='" + photoImg + '\'' +
                ", name='" + name + '\'' +
                ", dharamName='" + dharamName + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", regisDate=" + regisDate +
                ", guru=" + guru +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDharamName() {
        return dharamName;
    }

    public void setDharamName(String dharamName) {
        this.dharamName = dharamName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(Date regisDate) {
        this.regisDate = regisDate;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public User(Integer id, String photoImg, String name, String dharamName, String sex, String province, String city, String sign, String phoneNum, String password, String salt, String status, Date regisDate, Guru guru) {
        this.id = id;
        this.photoImg = photoImg;
        this.name = name;
        this.dharamName = dharamName;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.phoneNum = phoneNum;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.regisDate = regisDate;
        this.guru = guru;
    }
}
