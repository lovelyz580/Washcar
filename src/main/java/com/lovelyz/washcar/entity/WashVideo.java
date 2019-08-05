package com.lovelyz.washcar.entity;

import java.util.Date;
import java.util.List;

public class WashVideo {
    private Integer videoId;

    private String videoName;

    private String videoUrl;

    private String videoInfo;

    private Date videoCreatetime;

    private Date videoUpdatetime;

    private Integer videoIsdel;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
    }

    public Date getVideoCreatetime() {
        return videoCreatetime;
    }

    public void setVideoCreatetime(Date videoCreatetime) {
        this.videoCreatetime = videoCreatetime;
    }

    public Date getVideoUpdatetime() {
        return videoUpdatetime;
    }

    public void setVideoUpdatetime(Date videoUpdatetime) {
        this.videoUpdatetime = videoUpdatetime;
    }

    public Integer getVideoIsdel() {
        return videoIsdel;
    }

    public void setVideoIsdel(Integer videoIsdel) {
        this.videoIsdel = videoIsdel;
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