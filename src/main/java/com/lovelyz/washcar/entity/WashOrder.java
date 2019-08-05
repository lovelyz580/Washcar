package com.lovelyz.washcar.entity;

import java.util.Date;

public class WashOrder {
    private String orderId;

    private String orderNumber;

    private String orderUserid;

    private String orderUsername;

    private String orderUserphone;

    private String orderAddress;

    private Date orderStarttime;

    private Date orderEndtime;

    private String orderServiceid;

    private String orderStatic;

    private String orderRankid;

    private String orderRankname;

    private String orderFeeid;

    private String orderFeename;

    private String orderBeforepic;

    private String orderEndpic;

    private Double orderAllmoney;

    private Double orderIsreduced;

    private Double orderPaymoney;

    private Integer orderIspay;

    private Integer orderIsdel;

    private Integer orderIseva;

    private Date orderCreatetime;

    private Date orderUpdatetime;

    private String orderUpdatebyuserid;

    private String orderEvaluateid;

    private String orderOther1;

    private String orderOther2;

    private String orderOther3;

    private String orderOther4;

    private String orderOther5;
    private WashEvaluate washEvaluate;  //评价表

    private String userName;

    private String userRName;  //洗车人员姓名

    private Date starttime;  //开始时间

    private Integer userIsfirst;  //是否是首單
    private Date endtime;  //结束时间

    private Integer ZLnum;  //总量
    private Integer WJDnum;   //未接单
    private Integer YWCnum;  //已完成量
    private Integer JXZnum;  //进行中量
    private Integer YJSnum;  //已结束量
    private Integer WWCnum;  //未完成量
    private Integer YQXnum;  //已取消量



    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;

    public String getUserRName() {
        return userRName;
    }

    public void setUserRName(String userRName) {
        this.userRName = userRName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderUserid() {
        return orderUserid;
    }

    public void setOrderUserid(String orderUserid) {
        this.orderUserid = orderUserid;
    }

    public String getOrderUsername() {
        return orderUsername;
    }

    public void setOrderUsername(String orderUsername) {
        this.orderUsername = orderUsername;
    }

    public String getOrderUserphone() {
        return orderUserphone;
    }

    public void setOrderUserphone(String orderUserphone) {
        this.orderUserphone = orderUserphone;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Date getOrderStarttime() {
        return orderStarttime;
    }

    public void setOrderStarttime(Date orderStarttime) {
        this.orderStarttime = orderStarttime;
    }

    public Date getOrderEndtime() {
        return orderEndtime;
    }

    public void setOrderEndtime(Date orderEndtime) {
        this.orderEndtime = orderEndtime;
    }

    public String getOrderServiceid() {
        return orderServiceid;
    }

    public void setOrderServiceid(String orderServiceid) {
        this.orderServiceid = orderServiceid;
    }

    public String getOrderStatic() {
        return orderStatic;
    }

    public void setOrderStatic(String orderStatic) {
        this.orderStatic = orderStatic;
    }

    public String getOrderRankid() {
        return orderRankid;
    }

    public void setOrderRankid(String orderRankid) {
        this.orderRankid = orderRankid;
    }

    public String getOrderRankname() {
        return orderRankname;
    }

    public void setOrderRankname(String orderRankname) {
        this.orderRankname = orderRankname;
    }

    public String getOrderFeeid() {
        return orderFeeid;
    }

    public void setOrderFeeid(String orderFeeid) {
        this.orderFeeid = orderFeeid;
    }

    public String getOrderFeename() {
        return orderFeename;
    }

    public void setOrderFeename(String orderFeename) {
        this.orderFeename = orderFeename;
    }

    public String getOrderBeforepic() {
        return orderBeforepic;
    }

    public void setOrderBeforepic(String orderBeforepic) {
        this.orderBeforepic = orderBeforepic;
    }

    public String getOrderEndpic() {
        return orderEndpic;
    }

    public void setOrderEndpic(String orderEndpic) {
        this.orderEndpic = orderEndpic;
    }

    public Double getOrderAllmoney() {
        return orderAllmoney;
    }

    public void setOrderAllmoney(Double orderAllmoney) {
        this.orderAllmoney = orderAllmoney;
    }

    public Double getOrderIsreduced() {
        return orderIsreduced;
    }

    public void setOrderIsreduced(Double orderIsreduced) {
        this.orderIsreduced = orderIsreduced;
    }

    public Double getOrderPaymoney() {
        return orderPaymoney;
    }

    public void setOrderPaymoney(Double orderPaymoney) {
        this.orderPaymoney = orderPaymoney;
    }

    public Integer getOrderIspay() {
        return orderIspay;
    }

    public void setOrderIspay(Integer orderIspay) {
        this.orderIspay = orderIspay;
    }

    public Integer getOrderIsdel() {
        return orderIsdel;
    }

    public void setOrderIsdel(Integer orderIsdel) {
        this.orderIsdel = orderIsdel;
    }

    public Integer getOrderIseva() {
        return orderIseva;
    }

    public void setOrderIseva(Integer orderIseva) {
        this.orderIseva = orderIseva;
    }

    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }

    public Date getOrderUpdatetime() {
        return orderUpdatetime;
    }

    public void setOrderUpdatetime(Date orderUpdatetime) {
        this.orderUpdatetime = orderUpdatetime;
    }

    public String getOrderUpdatebyuserid() {
        return orderUpdatebyuserid;
    }

    public void setOrderUpdatebyuserid(String orderUpdatebyuserid) {
        this.orderUpdatebyuserid = orderUpdatebyuserid;
    }

    public String getOrderEvaluateid() {
        return orderEvaluateid;
    }

    public void setOrderEvaluateid(String orderEvaluateid) {
        this.orderEvaluateid = orderEvaluateid;
    }

    public String getOrderOther1() {
        return orderOther1;
    }

    public void setOrderOther1(String orderOther1) {
        this.orderOther1 = orderOther1;
    }

    public String getOrderOther2() {
        return orderOther2;
    }

    public void setOrderOther2(String orderOther2) {
        this.orderOther2 = orderOther2;
    }

    public String getOrderOther3() {
        return orderOther3;
    }

    public void setOrderOther3(String orderOther3) {
        this.orderOther3 = orderOther3;
    }

    public String getOrderOther4() {
        return orderOther4;
    }

    public void setOrderOther4(String orderOther4) {
        this.orderOther4 = orderOther4;
    }

    public String getOrderOther5() {
        return orderOther5;
    }

    public void setOrderOther5(String orderOther5) {
        this.orderOther5 = orderOther5;
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

    public WashEvaluate getWashEvaluate() {
        return washEvaluate;
    }

    public void setWashEvaluate(WashEvaluate washEvaluate) {
        this.washEvaluate = washEvaluate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getZLnum() {
        return ZLnum;
    }

    public void setZLnum(Integer ZLnum) {
        this.ZLnum = ZLnum;
    }

    public Integer getYWCnum() {
        return YWCnum;
    }

    public void setYWCnum(Integer YWCnum) {
        this.YWCnum = YWCnum;
    }

    public Integer getJXZnum() {
        return JXZnum;
    }

    public void setJXZnum(Integer JXZnum) {
        this.JXZnum = JXZnum;
    }

    public Integer getYJSnum() {
        return YJSnum;
    }

    public void setYJSnum(Integer YJSnum) {
        this.YJSnum = YJSnum;
    }

    public Integer getWWCnum() {
        return WWCnum;
    }

    public void setWWCnum(Integer WWCnum) {
        this.WWCnum = WWCnum;
    }

    public Integer getYQXnum() {
        return YQXnum;
    }

    public void setYQXnum(Integer YQXnum) {
        this.YQXnum = YQXnum;
    }

    public Integer getUserIsfirst() {
        return userIsfirst;
    }

    public void setUserIsfirst(Integer userIsfirst) {
        this.userIsfirst = userIsfirst;
    }

    public Integer getWJDnum() {
        return WJDnum;
    }

    public void setWJDnum(Integer WJDnum) {
        this.WJDnum = WJDnum;
    }
}