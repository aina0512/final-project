package com.baizhi.controller;
import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/queryAll")
    public List<User> queryAll(){
        return userService.queryAll();
    }

    @RequestMapping("/export")
    public void export(String titles, String params, HttpServletResponse response){

        userService.export(titles,params,response);
    }
    @RequestMapping("/exportAll")
    public void exportAll(HttpServletResponse response){
        try {
            userService.exportAll(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/userCount")
    @ResponseBody
    public List<Integer> userCount(){
        List<Integer> list = userService.queryUserNum();
        return list;
    }
    @RequestMapping("/userMap")
    @ResponseBody
    public Map<String,Object> userMap(){
        Map<String, Object> map = userService.queryUserMap();
        return map;
    }
    @RequestMapping("update")
    public void update(User user, Guru guru){
        System.out.println(user);
        System.out.println(guru);
    }

}
