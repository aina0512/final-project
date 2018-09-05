package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.util.Mp3Util;
import com.baizhi.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by aina on 2018/9/2.
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    public void addChapter(Chapter chapter, String path, MultipartFile audioPath) throws IOException {
        //获取音频的大小
        long size = audioPath.getSize();
        double audioSize=size/1024;
        //保留3位有效数字
        chapter.setSize((double)Math.round(audioSize/1024*100)/100+"MB");
        String imageName = UploadFileUtil.UploadFile(audioPath, path);
        //获取音频文件的时长
        File file = new File(path + "/upload/" + imageName);
        String duration = Mp3Util.getDuration(file);
        chapter.setDuration(duration);
        //将生成的文件名存入对象中便于入库
        chapter.setAudioPath(imageName);
        System.out.println(chapter);
        chapterDao.addChapter(chapter);
    }
}
