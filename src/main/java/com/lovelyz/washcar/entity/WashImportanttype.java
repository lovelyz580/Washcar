package com.lovelyz.washcar.entity;

import java.util.Date;

public class WashImportanttype {
    private String importanttypeId;

    private String importanttypeType;

    private String importanttypeName;


    private String importanttypeUserid;

    private Integer importanttypePtnum;   //普通洗车

    private Integer importanttypePtusenum;//普通使用洗车

    private Integer importanttypeFdnum;   //洗发动机

    private Integer importanttypeFdusenum; //洗发动机使用次数

    private Integer importanttypeKtnum;     //洗空调

    private Integer importanttypeKtusenum;  //洗空调使用次数

    private Integer importanttypeNsnum;    //内外洗

    private Integer importanttypeNsusenum;  //内外洗使用次数

    private Date importanttypeCreatetime;

    private String importanttypeCreatebyuserid;

    private Date importanttypeUpdatetime;

    private String importanttypeUpdatebyuserid;

    private Date importanttypeValidtime;

    private Integer importanttypeIsvalid;

    private Integer importanttypeIsdel;

    public String getImportanttypeId() {
        return importanttypeId;
    }

    public String getImportanttypeName() {
        return importanttypeName;
    }

    public void setImportanttypeName(String importanttypeName) {
        this.importanttypeName = importanttypeName;
    }

    public void setImportanttypeId(String importanttypeId) {
        this.importanttypeId = importanttypeId;
    }

    public String getImportanttypeType() {
        return importanttypeType;
    }

    public void setImportanttypeType(String importanttypeType) {
        this.importanttypeType = importanttypeType;
    }

    public String getImportanttypeUserid() {
        return importanttypeUserid;
    }

    public void setImportanttypeUserid(String importanttypeUserid) {
        this.importanttypeUserid = importanttypeUserid;
    }

    public Integer getImportanttypePtnum() {
        return importanttypePtnum;
    }

    public void setImportanttypePtnum(Integer importanttypePtnum) {
        this.importanttypePtnum = importanttypePtnum;
    }

    public Integer getImportanttypePtusenum() {
        return importanttypePtusenum;
    }

    public void setImportanttypePtusenum(Integer importanttypePtusenum) {
        this.importanttypePtusenum = importanttypePtusenum;
    }

    public Integer getImportanttypeFdnum() {
        return importanttypeFdnum;
    }

    public void setImportanttypeFdnum(Integer importanttypeFdnum) {
        this.importanttypeFdnum = importanttypeFdnum;
    }

    public Integer getImportanttypeFdusenum() {
        return importanttypeFdusenum;
    }

    public void setImportanttypeFdusenum(Integer importanttypeFdusenum) {
        this.importanttypeFdusenum = importanttypeFdusenum;
    }

    public Integer getImportanttypeKtnum() {
        return importanttypeKtnum;
    }

    public void setImportanttypeKtnum(Integer importanttypeKtnum) {
        this.importanttypeKtnum = importanttypeKtnum;
    }

    public Integer getImportanttypeKtusenum() {
        return importanttypeKtusenum;
    }

    public void setImportanttypeKtusenum(Integer importanttypeKtusenum) {
        this.importanttypeKtusenum = importanttypeKtusenum;
    }

    public Integer getImportanttypeNsnum() {
        return importanttypeNsnum;
    }

    public void setImportanttypeNsnum(Integer importanttypeNsnum) {
        this.importanttypeNsnum = importanttypeNsnum;
    }

    public Integer getImportanttypeNsusenum() {
        return importanttypeNsusenum;
    }

    public void setImportanttypeNsusenum(Integer importanttypeNsusenum) {
        this.importanttypeNsusenum = importanttypeNsusenum;
    }

    public Date getImportanttypeCreatetime() {
        return importanttypeCreatetime;
    }

    public void setImportanttypeCreatetime(Date importanttypeCreatetime) {
        this.importanttypeCreatetime = importanttypeCreatetime;
    }

    public String getImportanttypeCreatebyuserid() {
        return importanttypeCreatebyuserid;
    }

    public void setImportanttypeCreatebyuserid(String importanttypeCreatebyuserid) {
        this.importanttypeCreatebyuserid = importanttypeCreatebyuserid;
    }

    public Date getImportanttypeUpdatetime() {
        return importanttypeUpdatetime;
    }

    public void setImportanttypeUpdatetime(Date importanttypeUpdatetime) {
        this.importanttypeUpdatetime = importanttypeUpdatetime;
    }

    public String getImportanttypeUpdatebyuserid() {
        return importanttypeUpdatebyuserid;
    }

    public void setImportanttypeUpdatebyuserid(String importanttypeUpdatebyuserid) {
        this.importanttypeUpdatebyuserid = importanttypeUpdatebyuserid;
    }

    public Date getImportanttypeValidtime() {
        return importanttypeValidtime;
    }

    public void setImportanttypeValidtime(Date importanttypeValidtime) {
        this.importanttypeValidtime = importanttypeValidtime;
    }

    public Integer getImportanttypeIsvalid() {
        return importanttypeIsvalid;
    }

    public void setImportanttypeIsvalid(Integer importanttypeIsvalid) {
        this.importanttypeIsvalid = importanttypeIsvalid;
    }

    public Integer getImportanttypeIsdel() {
        return importanttypeIsdel;
    }

    public void setImportanttypeIsdel(Integer importanttypeIsdel) {
        this.importanttypeIsdel = importanttypeIsdel;
    }
}