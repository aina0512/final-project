package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aina on 2018/8/29.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDao bannerDao;
    @Override
    public BannerPage findByPage(Integer page, Integer rows) {
        BannerPage bannerPage=new BannerPage();
        Integer page1=(page-1)*rows;//每页显示条数的起始值
        List<Banner> banners = bannerDao.queryByPage(page1, rows);
        Integer count = bannerDao.count();
        String count1=count.toString();
        bannerPage.setTotal(count1);
        bannerPage.setRows(banners);
        return bannerPage;
    }

    @Override
    public void save(Banner banner) {

        bannerDao.save(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerDao.delete(id);
    }
}
