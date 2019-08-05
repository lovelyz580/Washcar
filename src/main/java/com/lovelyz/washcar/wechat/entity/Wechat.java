package com.lovelyz.washcar.wechat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 微信相关数据表
 * 
 * 实体类
 * 
 * @author ZY on 2018/10/12
 */

public class Wechat {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 微信应用ID
	 */
    private String appid;

    /**
	 * 微信应用秘钥
	 */
    private String appsecret;

    /**
	 * 微信商户号
	 */
    private String mchid;

    /**
	 * 微信API
	 */
    private String api;

    /**
     * 微信jsapi_ticket(用于微信扫一扫)
     */
    private String jsapiticket;
    
    /**
	 * 微信access_token
	 */
    private String accesstoken;

    /**
	 * 微信token生成时间
	 */
    private Date tokenbuildtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid == null ? null : mchid.trim();
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api == null ? null : api.trim();
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getTokenbuildtime() {
        return tokenbuildtime;
    }

    public void setTokenbuildtime(Date tokenbuildtime) {
        this.tokenbuildtime = tokenbuildtime;
    }

    public String getJsapiticket() {
        return jsapiticket;
    }

    public void setJsapiticket(String jsapiticket) {
        this.jsapiticket = jsapiticket;
    }
}