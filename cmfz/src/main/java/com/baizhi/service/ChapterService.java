package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by aina on 2018/9/2.
 */
public interface ChapterService {
    void addChapter(Chapter chapter, String path, MultipartFile audioPath) throws IOException;
}
