package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by aina on 2018/9/2.
 */
public interface AlbumDao {
    /**
     * @param page 当前是第几页
     * @param rows 每页显示的条数
     * @return  返回当前页面显示数据
     */
    //List<Album> queryByPage(@Param("page")String page,@Param("rows")String rows);
    List<Album> queryAllAlbum();
    void addAlbum(Album album);
}
