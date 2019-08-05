package com.lovelyz.washcar.entity;

import java.util.Date;

public class WashPayment {


    private String paymentId;
    private String orderNumber;     //车牌号
    private String orderUsername;  //付款人姓名
    private String orderUserphone;   //付款人电话
    private Double orderAllmoney;     //金额

    private String paymentOrderid;

    private String paymentCreatebyid;

    private Date paymentCreatetime;

    private String paymentUpdatebyid;

    private Date paymentUpdatetime;

    private Integer paymentIsdel;

    private String paymentOther1;

    private String paymentOthe2;

    private String paymentOther3;

    private String paymentOther4;

    private String paymentOther5;

    private Date starttime;  //开始时间


    private Date endtime;  //结束时间

    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;

    private WashOrder washOrder;  //订单表

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentOrderid() {
        return paymentOrderid;
    }

    public void setPaymentOrderid(String paymentOrderid) {
        this.paymentOrderid = paymentOrderid;
    }

    public String getPaymentCreatebyid() {
        return paymentCreatebyid;
    }

    public void setPaymentCreatebyid(String paymentCreatebyid) {
        this.paymentCreatebyid = paymentCreatebyid;
    }

    public Date getPaymentCreatetime() {
        return paymentCreatetime;
    }

    public void setPaymentCreatetime(Date paymentCreatetime) {
        this.paymentCreatetime = paymentCreatetime;
    }

    public String getPaymentUpdatebyid() {
        return paymentUpdatebyid;
    }

    public void setPaymentUpdatebyid(String paymentUpdatebyid) {
        this.paymentUpdatebyid = paymentUpdatebyid;
    }

    public Date getPaymentUpdatetime() {
        return paymentUpdatetime;
    }

    public void setPaymentUpdatetime(Date paymentUpdatetime) {
        this.paymentUpdatetime = paymentUpdatetime;
    }

    public Integer getPaymentIsdel() {
        return paymentIsdel;
    }

    public void setPaymentIsdel(Integer paymentIsdel) {
        this.paymentIsdel = paymentIsdel;
    }

    public String getPaymentOther1() {
        return paymentOther1;
    }

    public void setPaymentOther1(String paymentOther1) {
        this.paymentOther1 = paymentOther1;
    }

    public String getPaymentOthe2() {
        return paymentOthe2;
    }

    public void setPaymentOthe2(String paymentOthe2) {
        this.paymentOthe2 = paymentOthe2;
    }

    public String getPaymentOther3() {
        return paymentOther3;
    }

    public void setPaymentOther3(String paymentOther3) {
        this.paymentOther3 = paymentOther3;
    }

    public String getPaymentOther4() {
        return paymentOther4;
    }

    public void setPaymentOther4(String paymentOther4) {
        this.paymentOther4 = paymentOther4;
    }

    public String getPaymentOther5() {
        return paymentOther5;
    }

    public void setPaymentOther5(String paymentOther5) {
        this.paymentOther5 = paymentOther5;
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

    public WashOrder getWashOrder() {
        return washOrder;
    }

    public void setWashOrder(WashOrder washOrder) {
        this.washOrder = washOrder;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getOrderAllmoney() {
        return orderAllmoney;
    }

    public void setOrderAllmoney(Double orderAllmoney) {
        this.orderAllmoney = orderAllmoney;
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
}