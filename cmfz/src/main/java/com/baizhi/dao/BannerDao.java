package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by aina on 2018/8/29.
 */
public interface BannerDao {
    /**
     * @param page 当前是第几页
     * @param rows 每页显示的条数
     * @return  返回当前页面显示数据
     */
    List<Banner> queryByPage(@Param("page")Integer page, @Param("rows")Integer rows);
    Integer count();
    void save(Banner banner);
    void update(Banner banner);
    void delete(@Param("id") Integer id);
}
