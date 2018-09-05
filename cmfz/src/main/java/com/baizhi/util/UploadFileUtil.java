package com.baizhi.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by aina on 2018/9/2.
 */
public class UploadFileUtil {
    public static String UploadFile(MultipartFile imagePath, String path) throws IOException {
        //指定文件存放的路径
        String uploadFilePath=path+"upload";
        //根据路径生成文件
        File file = new File(uploadFilePath);
        //判断文件夹是否存在，不存在则生成文件夹
        if(!file.exists()){
            file.mkdir();
        }
        //生成通用唯一识别码UUID用来作为图片格式的前缀
        String uuid = UUID.randomUUID().toString();
        //获取上传的文件名
        String filename = imagePath.getOriginalFilename();
        //获取文件的后缀
        String extension = FilenameUtils.getExtension(filename);
        //生成新的文件名
        String imageName=uuid+"."+extension;
        //上传文件到指定的文件夹
        imagePath.transferTo(new File(uploadFilePath,imageName));
        return imageName;
    }
}
