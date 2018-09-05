package com.baizhi.entity;

/**
 * Created by aina on 2018/9/5.
 */
public class UserDto {
    private String name;
    private Integer value;

    public UserDto() {
    }

    public UserDto(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
