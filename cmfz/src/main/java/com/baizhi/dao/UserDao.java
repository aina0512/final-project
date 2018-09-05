package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by aina on 2018/9/5.
 */
public interface UserDao {
    List<User> queryAll();
    Integer queryUerNum(int num);
    List<UserDto> queryUserDto(String sex);
}
