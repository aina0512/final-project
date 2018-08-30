package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by aina on 2018/8/28.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/save")
    public String save(Admin admin){
        adminService.save(admin);
        return "index";
    }
    @RequestMapping("/login")
    public String login(Admin admin,String enCode, HttpSession session){
        String serverCode = (String) session.getAttribute("serverCode");
        /**
         * 验证码验证
         * */
        if(serverCode.equalsIgnoreCase(enCode)){
            try {
                System.out.println(admin.getUsername());
                Admin login = adminService.login(admin.getUsername(), admin.getPassword());
                /**
                 * 储存登录标志 也是登录对象
                 */
                session.setAttribute("admin",login);
                return "redirect:/main/main.jsp";
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error",e.getMessage());
                return "redirect:/login.jsp";
            }
        }
        else
            return "redirect:/login.jsp";
    }
}
