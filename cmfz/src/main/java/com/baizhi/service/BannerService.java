package com.baizhi.service;
import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPage;
/**
 * Created by aina on 2018/8/29.
 */
public interface BannerService {
    BannerPage findByPage(Integer page, Integer rows);
    void save(Banner banner);
    void update(Banner banner);
    void delete(Integer id);
}
