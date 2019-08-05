package com.lovelyz.washcar.entity;

import java.util.Date;

public class WashUser {
    private String userId;

    private String userName;

    private String userPassword;

    private String userRname;

    private Integer userSex;

    private String userPhone;

    private String userCodeid;

    private String userState;

    private String userRole;

    private String userOpenid;

    private Date userCreatetime;

    private String userUpdateuserid;

    private Date userUpdatetime;

    private String userRemark;

    private Integer userIsdel;

    //接单统计数量
    private Integer userWashCount;

    //接单统计总实收金额
    private Double userWashTotalMoney;
    //预约开始查询开始时间
    private Date startTime;

    //预约开始查询结束时间
    private Date endTime;

    private Integer userIsfirst;  //是否是新用户
    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRname() {
        return userRname;
    }

    public void setUserRname(String userRname) {
        this.userRname = userRname;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCodeid() {
        return userCodeid;
    }

    public void setUserCodeid(String userCodeid) {
        this.userCodeid = userCodeid;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    public String getUserUpdateuserid() {
        return userUpdateuserid;
    }

    public void setUserUpdateuserid(String userUpdateuserid) {
        this.userUpdateuserid = userUpdateuserid;
    }

    public Date getUserUpdatetime() {
        return userUpdatetime;
    }

    public void setUserUpdatetime(Date userUpdatetime) {
        this.userUpdatetime = userUpdatetime;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public Integer getUserIsdel() {
        return userIsdel;
    }

    public void setUserIsdel(Integer userIsdel) {
        this.userIsdel = userIsdel;
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

    public Integer getUserIsfirst() {
        return userIsfirst;
    }

    public void setUserIsfirst(Integer userIsfirst) {
        this.userIsfirst = userIsfirst;
    }

    public Integer getUserWashCount() {
        return userWashCount;
    }

    public void setUserWashCount(Integer userWashCount) {
        this.userWashCount = userWashCount;
    }

    public Double getUserWashTotalMoney() {
        return userWashTotalMoney;
    }

    public void setUserWashTotalMoney(Double userWashTotalMoney) {
        this.userWashTotalMoney = userWashTotalMoney;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}