package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by aina on 2018/9/2.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Album> queryAllAlbum() {
        return albumDao.queryAllAlbum();
    }

    @Override
    public void addAlbum(Album album, String path, MultipartFile corverImgPath) throws IOException {
        String imageName = UploadFileUtil.UploadFile(corverImgPath, path);
        //将生成的文件名存入对象中便于入库
        album.setCorverImg(imageName);
        albumDao.addAlbum(album);
    }
}
