package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30.
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/allAlbum")
    @ResponseBody
    public List<Album> queryAllAlbum(){
        List<Album> albums = albumService.queryAllAlbum();
        return albums;
    }
    @RequestMapping("/addAlbum")
    public String addAlbum(Album album, MultipartFile corverImgPath, HttpServletRequest request){
        //获取项目的真实路径
        String realPath = request.getServletContext().getRealPath("/");
        try {
            albumService.addAlbum(album,realPath,corverImgPath);
            return "redirect:/album/allAlbum";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
