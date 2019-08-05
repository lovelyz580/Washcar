package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashCouponuse {
    private String couponuseId;

    private Double couponuseMoney;

    private Double couponuseRemoney;

    private Date couponuseCreatetime;

    private Date couponuseStarttime;

    private Date couponuseEndtime;

    private String couponuseUserid;

    private String couponuseOrderid;

    private Date couponuseUpdatetime;

    private Integer couponuseIsuse;

    private Integer couponuseIsdel;

    private String couponuseOther1;

    private String couponuseOther2;

    private String couponuseOther3;

    private String couponuseOther4;

    private String couponuseOther5;

    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;

    private List<String> idlist;

    public List<String> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<String> idlist) {
        this.idlist = idlist;
    }

    public String getCouponuseId() {
        return couponuseId;
    }

    public void setCouponuseId(String couponuseId) {
        this.couponuseId = couponuseId;
    }

    public Double getCouponuseMoney() {
        return couponuseMoney;
    }

    public void setCouponuseMoney(Double couponuseMoney) {
        this.couponuseMoney = couponuseMoney;
    }

    public Double getCouponuseRemoney() {
        return couponuseRemoney;
    }

    public void setCouponuseRemoney(Double couponuseRemoney) {
        this.couponuseRemoney = couponuseRemoney;
    }

    public Date getCouponuseCreatetime() {
        return couponuseCreatetime;
    }

    public void setCouponuseCreatetime(Date couponuseCreatetime) {
        this.couponuseCreatetime = couponuseCreatetime;
    }

    public Date getCouponuseStarttime() {
        return couponuseStarttime;
    }

    public void setCouponuseStarttime(Date couponuseStarttime) {
        this.couponuseStarttime = couponuseStarttime;
    }

    public Date getCouponuseEndtime() {
        return couponuseEndtime;
    }

    public void setCouponuseEndtime(Date couponuseEndtime) {
        this.couponuseEndtime = couponuseEndtime;
    }

    public String getCouponuseUserid() {
        return couponuseUserid;
    }

    public void setCouponuseUserid(String couponuseUserid) {
        this.couponuseUserid = couponuseUserid;
    }

    public String getCouponuseOrderid() {
        return couponuseOrderid;
    }

    public void setCouponuseOrderid(String couponuseOrderid) {
        this.couponuseOrderid = couponuseOrderid;
    }

    public Date getCouponuseUpdatetime() {
        return couponuseUpdatetime;
    }

    public void setCouponuseUpdatetime(Date couponuseUpdatetime) {
        this.couponuseUpdatetime = couponuseUpdatetime;
    }

    public Integer getCouponuseIsuse() {
        return couponuseIsuse;
    }

    public void setCouponuseIsuse(Integer couponuseIsuse) {
        this.couponuseIsuse = couponuseIsuse;
    }

    public Integer getCouponuseIsdel() {
        return couponuseIsdel;
    }

    public void setCouponuseIsdel(Integer couponuseIsdel) {
        this.couponuseIsdel = couponuseIsdel;
    }

    public String getCouponuseOther1() {
        return couponuseOther1;
    }

    public void setCouponuseOther1(String couponuseOther1) {
        this.couponuseOther1 = couponuseOther1;
    }

    public String getCouponuseOther2() {
        return couponuseOther2;
    }

    public void setCouponuseOther2(String couponuseOther2) {
        this.couponuseOther2 = couponuseOther2;
    }

    public String getCouponuseOther3() {
        return couponuseOther3;
    }

    public void setCouponuseOther3(String couponuseOther3) {
        this.couponuseOther3 = couponuseOther3;
    }

    public String getCouponuseOther4() {
        return couponuseOther4;
    }

    public void setCouponuseOther4(String couponuseOther4) {
        this.couponuseOther4 = couponuseOther4;
    }

    public String getCouponuseOther5() {
        return couponuseOther5;
    }

    public void setCouponuseOther5(String couponuseOther5) {
        this.couponuseOther5 = couponuseOther5;
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}