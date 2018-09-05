package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by aina on 2018/9/5.
 */
public interface UserService {
    List<User> queryAll();
    List<Integer> queryUserNum();
    Map<String,Object> queryUserMap();
    void export(String titles, String params,HttpServletResponse response);
    void exportAll(HttpServletResponse response) throws Exception;
}
