package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aina on 2018/8/29.
 */
public class BannerPage implements Serializable{
    private String total;//总条数
    private List<Banner> rows;//每页显示的数据

    public BannerPage() {
    }

    public BannerPage(String total, List<Banner> rows) {
        this.total = total;
        this.rows = rows;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Banner> getRows() {
        return rows;
    }

    public void setRows(List<Banner> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "BannerPage{" +
                "total='" + total + '\'' +
                ", rows=" + rows +
                '}';
    }
}
