package com.lovelyz.washcar.entity;

import java.lang.reflect.Array;
import java.util.List;

public class WashFee {
    private String feeId;

    private String feeName;

    private String rankName;   //车辆级别名称

    private String feeRenkid;

    private String feeInfo;

    private String feeImages;

    private Double feeMoney;

    private Integer feeIedel;

    private String feeOther1;

    private String feeOther2;

    private String feeOther3;

    private String feeOther4;

    private String feeOther5;
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

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public String getFeeRenkid() {
        return feeRenkid;
    }

    public void setFeeRenkid(String feeRenkid) {
        this.feeRenkid = feeRenkid;
    }

    public String getFeeInfo() {
        return feeInfo;
    }

    public void setFeeInfo(String feeInfo) {
        this.feeInfo = feeInfo;
    }

    public String getFeeImages() {
        return feeImages;
    }

    public void setFeeImages(String feeImages) {
        this.feeImages = feeImages;
    }

    public Double getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(Double feeMoney) {
        this.feeMoney = feeMoney;
    }

    public Integer getFeeIedel() {
        return feeIedel;
    }

    public void setFeeIedel(Integer feeIedel) {
        this.feeIedel = feeIedel;
    }

    public String getFeeOther1() {
        return feeOther1;
    }

    public void setFeeOther1(String feeOther1) {
        this.feeOther1 = feeOther1;
    }

    public String getFeeOther2() {
        return feeOther2;
    }

    public void setFeeOther2(String feeOther2) {
        this.feeOther2 = feeOther2;
    }

    public String getFeeOther3() {
        return feeOther3;
    }

    public void setFeeOther3(String feeOther3) {
        this.feeOther3 = feeOther3;
    }

    public String getFeeOther4() {
        return feeOther4;
    }

    public void setFeeOther4(String feeOther4) {
        this.feeOther4 = feeOther4;
    }

    public String getFeeOther5() {
        return feeOther5;
    }

    public void setFeeOther5(String feeOther5) {
        this.feeOther5 = feeOther5;
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

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}