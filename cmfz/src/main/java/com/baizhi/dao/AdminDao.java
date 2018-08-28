package com.baizhi.dao;

import com.baizhi.entity.Admin;



/**
 * Created by aina on 2018/8/28.
 */
public interface AdminDao {
    public void save(Admin admin);
    Admin queryByUsername(String username);
}
