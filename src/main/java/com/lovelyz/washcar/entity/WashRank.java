package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashRank {
    private String rankId;

    private String rankName;

    private String rankImages;

    private Date rankCreatetime;

    private String rankCreatrebyid;

    private Integer rankIsdel;

    private String rankOther1;

    private String rankOther2;

    private String rankOther3;

    private String rankOther4;

    private String rankOther5;
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

    public String getRankId() {
        return rankId;
    }

    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getRankImages() {
        return rankImages;
    }

    public void setRankImages(String rankImages) {
        this.rankImages = rankImages;
    }

    public Date getRankCreatetime() {
        return rankCreatetime;
    }

    public void setRankCreatetime(Date rankCreatetime) {
        this.rankCreatetime = rankCreatetime;
    }

    public String getRankCreatrebyid() {
        return rankCreatrebyid;
    }

    public void setRankCreatrebyid(String rankCreatrebyid) {
        this.rankCreatrebyid = rankCreatrebyid;
    }

    public Integer getRankIsdel() {
        return rankIsdel;
    }

    public void setRankIsdel(Integer rankIsdel) {
        this.rankIsdel = rankIsdel;
    }

    public String getRankOther1() {
        return rankOther1;
    }

    public void setRankOther1(String rankOther1) {
        this.rankOther1 = rankOther1;
    }

    public String getRankOther2() {
        return rankOther2;
    }

    public void setRankOther2(String rankOther2) {
        this.rankOther2 = rankOther2;
    }

    public String getRankOther3() {
        return rankOther3;
    }

    public void setRankOther3(String rankOther3) {
        this.rankOther3 = rankOther3;
    }

    public String getRankOther4() {
        return rankOther4;
    }

    public void setRankOther4(String rankOther4) {
        this.rankOther4 = rankOther4;
    }

    public String getRankOther5() {
        return rankOther5;
    }

    public void setRankOther5(String rankOther5) {
        this.rankOther5 = rankOther5;
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