package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by aina on 2018/9/2.
 */
public interface AlbumService {
    List<Album> queryAllAlbum();
    void addAlbum(Album album, String path, MultipartFile corverImgPath) throws IOException;
}
