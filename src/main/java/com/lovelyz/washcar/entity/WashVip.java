package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashVip {
    private String vipId;

    private String vipName;

    private String vipType;

    private Double vipMoney;

    private Integer vipPtnum;

    private Integer vipFdnum;

    private Integer vipKtnum;

    private Integer vipNsnum;

    private Date vipCreatetime;

    private String vipCreatebyuserid;

    private Date vipUpdatetime;

    private String vipUpdatebyuserid;

    private Integer vipIsvalid;

    private Integer vipIsdel;

    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;
    /**
     * 用于假删除的集合
     */
    private List<String> idlist;

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public Double getVipMoney() {
        return vipMoney;
    }

    public void setVipMoney(Double vipMoney) {
        this.vipMoney = vipMoney;
    }

    public Integer getVipPtnum() {
        return vipPtnum;
    }

    public void setVipPtnum(Integer vipPtnum) {
        this.vipPtnum = vipPtnum;
    }

    public Integer getVipFdnum() {
        return vipFdnum;
    }

    public void setVipFdnum(Integer vipFdnum) {
        this.vipFdnum = vipFdnum;
    }

    public Integer getVipKtnum() {
        return vipKtnum;
    }

    public void setVipKtnum(Integer vipKtnum) {
        this.vipKtnum = vipKtnum;
    }

    public Integer getVipNsnum() {
        return vipNsnum;
    }

    public void setVipNsnum(Integer vipNsnum) {
        this.vipNsnum = vipNsnum;
    }

    public Date getVipCreatetime() {
        return vipCreatetime;
    }

    public void setVipCreatetime(Date vipCreatetime) {
        this.vipCreatetime = vipCreatetime;
    }

    public String getVipCreatebyuserid() {
        return vipCreatebyuserid;
    }

    public void setVipCreatebyuserid(String vipCreatebyuserid) {
        this.vipCreatebyuserid = vipCreatebyuserid;
    }

    public Date getVipUpdatetime() {
        return vipUpdatetime;
    }

    public void setVipUpdatetime(Date vipUpdatetime) {
        this.vipUpdatetime = vipUpdatetime;
    }

    public String getVipUpdatebyuserid() {
        return vipUpdatebyuserid;
    }

    public void setVipUpdatebyuserid(String vipUpdatebyuserid) {
        this.vipUpdatebyuserid = vipUpdatebyuserid;
    }

    public Integer getVipIsvalid() {
        return vipIsvalid;
    }

    public void setVipIsvalid(Integer vipIsvalid) {
        this.vipIsvalid = vipIsvalid;
    }

    public Integer getVipIsdel() {
        return vipIsdel;
    }

    public void setVipIsdel(Integer vipIsdel) {
        this.vipIsdel = vipIsdel;
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

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }
}