package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aina on 2018/8/28.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public void save(Admin admin) {
        adminDao.save(admin);
    }

    @Override
    public Admin login(String username,String password) {
        Admin admin = adminDao.queryByUsername(username);
        System.out.println(admin);
        if (admin==null) throw new RuntimeException("用户名不存在，请输入正确用户名");
        if (!password.equals(admin.getPassword())) throw new RuntimeException("密码输入错误，请重新输入");
        return admin;
    }
}
