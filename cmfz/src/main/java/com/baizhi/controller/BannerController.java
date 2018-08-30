package com.baizhi.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPage;
import com.baizhi.service.BannerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by aina on 2018/8/29.
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("/findPage")
    @ResponseBody

    public BannerPage findPage(Integer page,Integer rows){
        //System.out.println(page+"~~~~"+rows);
        BannerPage bannerPage = bannerService.findByPage(page, rows);
        //System.out.println(bannerPage);
        return bannerPage;
    }
    @RequestMapping("/save")
    public String save(Banner banner, MultipartFile img, HttpServletRequest request){
        /**
         * 1.指定上传的位置
         * 项目的绝对路径  D:\source\final-project\cmfz\src\main\webapp\
         */
        String realPath = request.getServletContext().getRealPath("/");
        /**
         *拼接文件存放地址
         */
        String uploadFilePath = realPath + "upload";
        /**
         * 读取文件夹中的文件  路径对应的对象
         */
        File file=new File(uploadFilePath);
        /**
         * 判断文件夹是否存在 不存在的话创建文件夹
         */
        if(!file.exists()){
            file.mkdir();
        }
        /**
         *2.防止文件重名
         * 获取文件的原始名字
         */
        String originalFilename = img.getOriginalFilename();
        //获取随机字符串
        String uuid = UUID.randomUUID().toString();
        //获取文件后缀名
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFilename = uuid + "." + extension;
        /**
         *3.上传文件到指定位置
         */
        try {
            img.transferTo(new File(uploadFilePath, newFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 入库
         */
        banner.setImgPath("/upload/"+newFilename);
        banner.setCreateDate(new Date());
        bannerService.save(banner);
        return "redirect:/main/main.jsp";
    }
    @RequestMapping("/update")
    public String update(Banner banner){
        bannerService.update(banner);
        return "redirect:/main/main.jsp";
    }
    @RequestMapping("/delete")
    public String delete(Integer id){
        System.out.println(id);
        bannerService.delete(id);
        return "redirect:/main/main.jsp";
    }
}
