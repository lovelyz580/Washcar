package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashImpuse {
    private Integer impuseId;

    private String impuseOrderid;

    private String impuseUserid;

    private String impuseImportantid;

    private Date impuseCreatetime;

    private String impuseCreatebyuserid;

    private Date impuseUpdatetime;

    private String impuseUpdatebyuserid;

    private Integer impuseIsdel;

    private String impuseOther1;

    private String impuseOther2;

    private String impuseOther3;

    private String impuseOther4;

    private String impuseOther5;
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

    public Integer getImpuseId() {
        return impuseId;
    }

    public void setImpuseId(Integer impuseId) {
        this.impuseId = impuseId;
    }

    public String getImpuseOrderid() {
        return impuseOrderid;
    }

    public void setImpuseOrderid(String impuseOrderid) {
        this.impuseOrderid = impuseOrderid;
    }

    public String getImpuseUserid() {
        return impuseUserid;
    }

    public void setImpuseUserid(String impuseUserid) {
        this.impuseUserid = impuseUserid;
    }

    public String getImpuseImportantid() {
        return impuseImportantid;
    }

    public void setImpuseImportantid(String impuseImportantid) {
        this.impuseImportantid = impuseImportantid;
    }

    public Date getImpuseCreatetime() {
        return impuseCreatetime;
    }

    public void setImpuseCreatetime(Date impuseCreatetime) {
        this.impuseCreatetime = impuseCreatetime;
    }

    public String getImpuseCreatebyuserid() {
        return impuseCreatebyuserid;
    }

    public void setImpuseCreatebyuserid(String impuseCreatebyuserid) {
        this.impuseCreatebyuserid = impuseCreatebyuserid;
    }

    public Date getImpuseUpdatetime() {
        return impuseUpdatetime;
    }

    public void setImpuseUpdatetime(Date impuseUpdatetime) {
        this.impuseUpdatetime = impuseUpdatetime;
    }

    public String getImpuseUpdatebyuserid() {
        return impuseUpdatebyuserid;
    }

    public void setImpuseUpdatebyuserid(String impuseUpdatebyuserid) {
        this.impuseUpdatebyuserid = impuseUpdatebyuserid;
    }

    public Integer getImpuseIsdel() {
        return impuseIsdel;
    }

    public void setImpuseIsdel(Integer impuseIsdel) {
        this.impuseIsdel = impuseIsdel;
    }

    public String getImpuseOther1() {
        return impuseOther1;
    }

    public void setImpuseOther1(String impuseOther1) {
        this.impuseOther1 = impuseOther1;
    }

    public String getImpuseOther2() {
        return impuseOther2;
    }

    public void setImpuseOther2(String impuseOther2) {
        this.impuseOther2 = impuseOther2;
    }

    public String getImpuseOther3() {
        return impuseOther3;
    }

    public void setImpuseOther3(String impuseOther3) {
        this.impuseOther3 = impuseOther3;
    }

    public String getImpuseOther4() {
        return impuseOther4;
    }

    public void setImpuseOther4(String impuseOther4) {
        this.impuseOther4 = impuseOther4;
    }

    public String getImpuseOther5() {
        return impuseOther5;
    }

    public void setImpuseOther5(String impuseOther5) {
        this.impuseOther5 = impuseOther5;
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