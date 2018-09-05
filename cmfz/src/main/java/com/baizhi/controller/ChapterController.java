package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

;

/**
 * Created by Administrator on 2018/8/30.
 */
@Controller
@RequestMapping("/chap")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChap")
    public String addChapter(Chapter chapter, MultipartFile audio_Path, HttpServletRequest request){
        System.out.println(chapter);
        System.out.println(audio_Path);
        System.out.println(request);

        //获取项目的真实路径
        String realPath = request.getServletContext().getRealPath("/");
        try {
            System.out.println(chapter);
            chapterService.addChapter(chapter,realPath,audio_Path);
            return "redirect:/album/allAlbum";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/download")
    public void downAudio(String url, String name, HttpServletRequest request, HttpServletResponse response){
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
        String path = request.getServletContext().getRealPath("/");
        String filePath=path+"/upload/"+url;
        //通过文件路径获得File对象
        File file = new File(filePath);
        //将文件名转换为原文件名
        String extension = FilenameUtils.getExtension(url);
        name=name+"."+extension;
        String fileName = null;
        try {
            fileName = new String(name.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置文件ContentType类型
        response.setContentType("audio/mpeg");
        //设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            //通过response获取ServletOutputStream对象
            ServletOutputStream outputStream = response.getOutputStream();
            //读取java文件到字节数组
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
