package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashImportant {
    private Integer importantId;

    private String importantUserid;  //用户ID


    private String userRname;   //用户姓名

    private String userPhone;    //用户电话


    private String importantTypeid;

    private String importantMark;

    private Date importantCreatetime;

    private String importantCreatebyuserid;

    private Date importantUpdatetime;

    private String importantUpdatebyuserid;

    private Integer importantIsdel;

    private String importantOther1;

    private String importantOther2;

    private String importantOther3;

    private String importantOther4;

    private String importantOther5;
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
    private List<Integer> idlist;



//    会员套餐字段
private String importanttypeId;

    private String importanttypeName;

    private String importanttypeType;

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


    public String getImportantMark() {
        return importantMark;
    }

    public void setImportantMark(String importantMark) {
        this.importantMark = importantMark;
    }

    public Integer getImportantId() {
        return importantId;
    }

    public void setImportantId(Integer importantId) {
        this.importantId = importantId;
    }

    public String getImportantUserid() {
        return importantUserid;
    }

    public void setImportantUserid(String importantUserid) {
        this.importantUserid = importantUserid;
    }

    public String getImportantTypeid() {
        return importantTypeid;
    }

    public void setImportantTypeid(String importantTypeid) {
        this.importantTypeid = importantTypeid;
    }

    public String getImportanttypeName() {
        return importanttypeName;
    }

    public void setImportanttypeName(String importanttypeName) {
        this.importanttypeName = importanttypeName;
    }

    public Date getImportantCreatetime() {
        return importantCreatetime;
    }

    public void setImportantCreatetime(Date importantCreatetime) {
        this.importantCreatetime = importantCreatetime;
    }

    public String getImportantCreatebyuserid() {
        return importantCreatebyuserid;
    }

    public void setImportantCreatebyuserid(String importantCreatebyuserid) {
        this.importantCreatebyuserid = importantCreatebyuserid;
    }

    public Date getImportantUpdatetime() {
        return importantUpdatetime;
    }

    public void setImportantUpdatetime(Date importantUpdatetime) {
        this.importantUpdatetime = importantUpdatetime;
    }

    public String getImportantUpdatebyuserid() {
        return importantUpdatebyuserid;
    }

    public void setImportantUpdatebyuserid(String importantUpdatebyuserid) {
        this.importantUpdatebyuserid = importantUpdatebyuserid;
    }

    public Integer getImportantIsdel() {
        return importantIsdel;
    }

    public void setImportantIsdel(Integer importantIsdel) {
        this.importantIsdel = importantIsdel;
    }

    public String getImportantOther1() {
        return importantOther1;
    }

    public void setImportantOther1(String importantOther1) {
        this.importantOther1 = importantOther1;
    }

    public String getImportantOther2() {
        return importantOther2;
    }

    public void setImportantOther2(String importantOther2) {
        this.importantOther2 = importantOther2;
    }

    public String getImportantOther3() {
        return importantOther3;
    }

    public void setImportantOther3(String importantOther3) {
        this.importantOther3 = importantOther3;
    }

    public String getImportantOther4() {
        return importantOther4;
    }

    public void setImportantOther4(String importantOther4) {
        this.importantOther4 = importantOther4;
    }

    public String getImportantOther5() {
        return importantOther5;
    }

    public void setImportantOther5(String importantOther5) {
        this.importantOther5 = importantOther5;
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

    public List<Integer> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<Integer> idlist) {
        this.idlist = idlist;
    }

    public String getUserRname() {
        return userRname;
    }

    public void setUserRname(String userRname) {
        this.userRname = userRname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getImportanttypeId() {
        return importanttypeId;
    }

    public void setImportanttypeId(String importanttypeId) {
        this.importanttypeId = importanttypeId;
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