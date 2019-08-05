package com.lovelyz.washcar.entity;

import java.util.Date;

public class WashEvaluate {
//    订单信息
    private String orderNumber;  //车牌号

    private String evaluateId;

    private String evaluateContent;

    private String evaluateOrderid;

    private String evaluateCreatebyid;

    private String evaluateTouserid;

    private Date evaluateCreatetime;

    private String evaluateUpdatebyid;

    private Date evaluateUpdatebytime;

    private Integer evaluateIsgood;

    private Integer evaluateIeDel;

    private String evaluateOther1;

    private String evaluateOther2;

    private String evaluateOther3;

    private String evaluateOther4;

    private String evaluateOther5;
    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateOrderid() {
        return evaluateOrderid;
    }

    public void setEvaluateOrderid(String evaluateOrderid) {
        this.evaluateOrderid = evaluateOrderid;
    }

    public String getEvaluateCreatebyid() {
        return evaluateCreatebyid;
    }

    public void setEvaluateCreatebyid(String evaluateCreatebyid) {
        this.evaluateCreatebyid = evaluateCreatebyid;
    }

    public String getEvaluateTouserid() {
        return evaluateTouserid;
    }

    public void setEvaluateTouserid(String evaluateTouserid) {
        this.evaluateTouserid = evaluateTouserid;
    }

    public Date getEvaluateCreatetime() {
        return evaluateCreatetime;
    }

    public void setEvaluateCreatetime(Date evaluateCreatetime) {
        this.evaluateCreatetime = evaluateCreatetime;
    }

    public String getEvaluateUpdatebyid() {
        return evaluateUpdatebyid;
    }

    public void setEvaluateUpdatebyid(String evaluateUpdatebyid) {
        this.evaluateUpdatebyid = evaluateUpdatebyid;
    }

    public Date getEvaluateUpdatebytime() {
        return evaluateUpdatebytime;
    }

    public void setEvaluateUpdatebytime(Date evaluateUpdatebytime) {
        this.evaluateUpdatebytime = evaluateUpdatebytime;
    }

    public Integer getEvaluateIsgood() {
        return evaluateIsgood;
    }

    public void setEvaluateIsgood(Integer evaluateIsgood) {
        this.evaluateIsgood = evaluateIsgood;
    }

    public Integer getEvaluateIeDel() {
        return evaluateIeDel;
    }

    public void setEvaluateIeDel(Integer evaluateIeDel) {
        this.evaluateIeDel = evaluateIeDel;
    }

    public String getEvaluateOther1() {
        return evaluateOther1;
    }

    public void setEvaluateOther1(String evaluateOther1) {
        this.evaluateOther1 = evaluateOther1;
    }

    public String getEvaluateOther2() {
        return evaluateOther2;
    }

    public void setEvaluateOther2(String evaluateOther2) {
        this.evaluateOther2 = evaluateOther2;
    }

    public String getEvaluateOther3() {
        return evaluateOther3;
    }

    public void setEvaluateOther3(String evaluateOther3) {
        this.evaluateOther3 = evaluateOther3;
    }

    public String getEvaluateOther4() {
        return evaluateOther4;
    }

    public void setEvaluateOther4(String evaluateOther4) {
        this.evaluateOther4 = evaluateOther4;
    }

    public String getEvaluateOther5() {
        return evaluateOther5;
    }

    public void setEvaluateOther5(String evaluateOther5) {
        this.evaluateOther5 = evaluateOther5;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}