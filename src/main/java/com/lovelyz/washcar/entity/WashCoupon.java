package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashCoupon {
    private String couponId;

    private Double couponMoney;

    private Double couponRemoney;

    private String couponInfo;

    private Date couponCreatetime;

    private Date couponStarttime;

    private Date couponEndtime;

    private Date couponUpdatetime;

    private Integer couponIseffect;

    private Integer couponIsdel;

    private String couponOther1;

    private String couponOther2;

    private String couponOther3;

    private String couponOther4;

    private String couponOther5;

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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Double getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Double couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Double getCouponRemoney() {
        return couponRemoney;
    }

    public void setCouponRemoney(Double couponRemoney) {
        this.couponRemoney = couponRemoney;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public Date getCouponCreatetime() {
        return couponCreatetime;
    }

    public void setCouponCreatetime(Date couponCreatetime) {
        this.couponCreatetime = couponCreatetime;
    }

    public Date getCouponStarttime() {
        return couponStarttime;
    }

    public void setCouponStarttime(Date couponStarttime) {
        this.couponStarttime = couponStarttime;
    }

    public Date getCouponEndtime() {
        return couponEndtime;
    }

    public void setCouponEndtime(Date couponEndtime) {
        this.couponEndtime = couponEndtime;
    }

    public Date getCouponUpdatetime() {
        return couponUpdatetime;
    }

    public void setCouponUpdatetime(Date couponUpdatetime) {
        this.couponUpdatetime = couponUpdatetime;
    }

    public Integer getCouponIseffect() {
        return couponIseffect;
    }

    public void setCouponIseffect(Integer couponIseffect) {
        this.couponIseffect = couponIseffect;
    }

    public Integer getCouponIsdel() {
        return couponIsdel;
    }

    public void setCouponIsdel(Integer couponIsdel) {
        this.couponIsdel = couponIsdel;
    }

    public String getCouponOther1() {
        return couponOther1;
    }

    public void setCouponOther1(String couponOther1) {
        this.couponOther1 = couponOther1;
    }

    public String getCouponOther2() {
        return couponOther2;
    }

    public void setCouponOther2(String couponOther2) {
        this.couponOther2 = couponOther2;
    }

    public String getCouponOther3() {
        return couponOther3;
    }

    public void setCouponOther3(String couponOther3) {
        this.couponOther3 = couponOther3;
    }

    public String getCouponOther4() {
        return couponOther4;
    }

    public void setCouponOther4(String couponOther4) {
        this.couponOther4 = couponOther4;
    }

    public String getCouponOther5() {
        return couponOther5;
    }

    public void setCouponOther5(String couponOther5) {
        this.couponOther5 = couponOther5;
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

    public List<String> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<String> idlist) {
        this.idlist = idlist;
    }
}