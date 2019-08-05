package com.lovelyz.washcar.restful;

import com.alibaba.fastjson.JSON;

import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashUser;
import com.lovelyz.washcar.service.WashUserService;
import com.lovelyz.washcar.service.WechatService;
import com.lovelyz.washcar.wechat.AccessToken;
import com.lovelyz.washcar.wechat.JsapiTicket;
import com.lovelyz.washcar.wechat.OpenId;
import com.lovelyz.washcar.wechat.WechatGetUserInfoUtils;
import com.lovelyz.washcar.wechat.entity.OpenIdEntity;
import com.lovelyz.washcar.wechat.entity.Wechat;
import com.lovelyz.washcar.wechat.entity.WechatinfoEntity;
import com.lovelyz.washcar.wechat.utils.QrCodeUtils;
import com.lovelyz.washcar.wechat.utils.WechatFormUtil;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 微信相关数据表
 * <p>
 * Controller
 */

@Controller
@MultipartConfig
@RequestMapping("/wechat")
public class WechatRestful {

    @Autowired
    private WechatService wechatService;
    @Autowired
    private WashUserService washUserService;

    private static Logger logger = Logger.getLogger(WechatRestful.class);


    /**
     * 获取access_token
     *
     * @param wechatService
     * @return
     */
    public static String getAccessToken(WechatService wechatService) {
        try {
            // 查询的实体
            Wechat wechat = new Wechat();

            // 查询
            List<Wechat> wechatList = wechatService.selectByWechat(wechat);

            // 返回数据
            if (wechatList.size() == 0) {
                return null;
            } else {
                if (wechatList.get(0).getAccesstoken() == null || TextUtils.isEmpty(wechatList.get(0).getAccesstoken())) {
                    // 输出
                    logger.error("WechatController:getAccessToken:=============================数据库中没有accesss_token信息，重新获取");
                    System.out.println("WechatController:getAccessToken:=============================数据库中没有accesss_token信息，重新获取");
                    // 数据库中没有accesss_token信息
                    // 获取accesss_token
                    wechat = wechatList.get(0);
                    String AppID = wechat.getAppid();
                    String AppSecret = wechat.getAppsecret();
                    // 获取
                    String access_token = AccessToken.getAccessToken(AppID, AppSecret).getAccess_token();
                    // 输出
                    logger.error("WechatController:getAccessToken:=============================" + access_token);
                    System.out.println("WechatController:getAccessToken:=============================" + access_token);

                    if (access_token != null && !TextUtils.isEmpty(access_token)) {
                        // 成功获取accesss_token后，更新数据库
                        wechat.setAccesstoken(access_token);
                        wechat.setTokenbuildtime(new Date());
                        // 更新
                        wechatService.updateByWechat(wechat);
                        // 返回accesss_token
                        return access_token;
                    }
                } else {
                    // 输出
                    logger.error("WechatController:getAccessToken:=============================数据库中有accesss_token信息");
                    System.out.println("WechatController:getAccessToken:=============================数据库中有accesss_token信息");

                    if (new Date().getTime() - wechatList.get(0).getTokenbuildtime().getTime() > 1.5 * 60 * 60 * 1000) {
                        // 输出
                        logger.error("WechatController:getAccessToken:=============================数据库中有accesss_token信息，但超过1.5个小时，重新获取");
                        System.out.println("WechatController:getAccessToken:=============================数据库中有accesss_token信息，但超过1.5个小时，重新获取");

                        // 数据库中有accesss_token信息，但已经超过有效时间
                        // 获取accesss_token
                        wechat = wechatList.get(0);
                        String AppID = wechat.getAppid();
                        String AppSecret = wechat.getAppsecret();

                        // 获取
                        String access_token = AccessToken.getAccessToken(AppID, AppSecret).getAccess_token();
                        // 输出
                        logger.error("WechatController:getAccessToken:=============================" + access_token);
                        System.out.println("WechatController:getAccessToken:=============================" + access_token);
                        if (access_token != null && !TextUtils.isEmpty(access_token)) {
                            // 成功获取accesss_token后，更新数据库
                            wechat.setAccesstoken(access_token);
                            wechat.setTokenbuildtime(new Date());
                            // 更新
                            wechatService.updateByWechat(wechat);
                            // 返回accesss_token
                            return access_token;
                        }
                    } else {
                        // 输出
                        logger.error("WechatController:getAccessToken:=============================数据库中有accesss_token信息，并且没有超过1.5个小时");
                        System.out.println("WechatController:getAccessToken:=============================数据库中有accesss_token信息，并且没有超过1.5个小时");
                        logger.error("WechatController:getAccessToken:=============================" + wechatList.get(0).getAccesstoken());
                        System.out.println("WechatController:getAccessToken:=============================" + wechatList.get(0).getAccesstoken());

                        // 数据库中有accesss_token信息，并且没有已经超过有效时间
                        return wechatList.get(0).getAccesstoken();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 默认返回null
        return null;
    }

    /**
     * 微信扫一扫功能
     * <p>
     * 获取appId、jsapi_ticket
     *
     * @param wechatService
     * @return
     */
    public static String[] getAppIdAndJsapiTicket(WechatService wechatService) {
        String[] returnData = new String[2];

        try {
            // 查询的实体
            Wechat wechat = new Wechat();

            // 查询
            List<Wechat> wechatList = wechatService.selectByWechat(wechat);

            // 返回数据
            if (wechatList.size() == 0) {
                return null;
            } else {
                // appId
                returnData[0] = wechatList.get(0).getAppid();
                // 输出
                logger.error("WechatController:getAppIdAndJsapiTicket:appId:=============================" + returnData[0]);
                System.out.println("WechatController:getAppIdAndJsapiTicket:appId:=============================" + returnData[0]);

                if (wechatList.get(0).getJsapiticket() == null || TextUtils.isEmpty(wechatList.get(0).getJsapiticket())) {
                    // 输出
                    logger.error("WechatController:getAppIdAndJsapiTicket:=============================数据库中没有jsapi_ticket信息，重新获取");
                    System.out.println("WechatController:getAppIdAndJsapiTicket:=============================数据库中没有jsapi_ticket信息，重新获取");

                    // 数据库中没有jsapi_ticket信息
                    // 获取accesss_token
                    wechat = wechatList.get(0);
                    String AppID = wechat.getAppid();
                    String AppSecret = wechat.getAppsecret();

                    // 获取accesss_token
                    String access_token = AccessToken.getAccessToken(AppID, AppSecret).getAccess_token();
                    // 输出
                    logger.error("WechatController:getAppIdAndJsapiTicket:getAccessToken:=============================" + access_token);
                    System.out.println("WechatController:getAppIdAndJsapiTicket:getAccessToken:=============================" + access_token);

                    if (access_token != null && !TextUtils.isEmpty(access_token)) {
                        // 成功获取accesss_token后，获取jsapi_ticket
                        String jsapi_ticket = JsapiTicket.getJsapiTicket(access_token).getTicket();
                        // 输出
                        logger.error("WechatController:getAppIdAndJsapiTicket:getJsapiticket:=============================" + jsapi_ticket);
                        System.out.println("WechatController:getAppIdAndJsapiTicket:getJsapiticket:=============================" + jsapi_ticket);

                        if (jsapi_ticket != null && !TextUtils.isEmpty(jsapi_ticket)) {
                            // 成功获取jsapi_ticket后，更新数据库
                            wechat.setAccesstoken(access_token);
                            wechat.setJsapiticket(jsapi_ticket);
                            wechat.setTokenbuildtime(new Date());

                            // 更新
                            wechatService.updateByWechat(wechat);

                            // 返回数组
                            returnData[1] = jsapi_ticket;
                            return returnData;
                        }
                    }
                } else {
                    // 输出
                    logger.error("WechatController:getAppIdAndJsapiTicket:=============================数据库中有jsapi_ticket信息");
                    System.out.println("WechatController:getAppIdAndJsapiTicket:=============================数据库中有jsapi_ticket信息");

                    if (new Date().getTime() - wechatList.get(0).getTokenbuildtime().getTime() > 1.5 * 60 * 60 * 1000) {
                        // 输出
                        logger.error("WechatController:getAppIdAndJsapiTicket:=============================数据库中有jsapi_ticket信息，但超过1.5个小时，重新获取");
                        System.out.println("WechatController:getAppIdAndJsapiTicket:=============================数据库中有jsapi_ticket信息，但超过1.5个小时，重新获取");
                        // 数据库中有jsapi_ticket信息，但已经超过有效时间
                        // 获取accesss_token
                        wechat = wechatList.get(0);
                        String AppID = wechat.getAppid();
                        String AppSecret = wechat.getAppsecret();
                        // 获取accesss_token
                        String access_token = AccessToken.getAccessToken(AppID, AppSecret).getAccess_token();
                        // 输出
                        logger.error("WechatController:getAppIdAndJsapiTicket:getAccessToken:=============================" + access_token);
                        System.out.println("WechatController:getAppIdAndJsapiTicket:getAccessToken:=============================" + access_token);

                        if (access_token != null && !TextUtils.isEmpty(access_token)) {
                            // 成功获取accesss_token后，获取jsapi_ticket
                            String jsapi_ticket = JsapiTicket.getJsapiTicket(access_token).getTicket();
                            // 输出
                            logger.error("WechatController:getAppIdAndJsapiTicket:getJsapiticket:=============================" + jsapi_ticket);
                            System.out.println("WechatController:getAppIdAndJsapiTicket:getJsapiticket:=============================" + jsapi_ticket);

                            if (jsapi_ticket != null && !TextUtils.isEmpty(jsapi_ticket)) {
                                // 成功获取jsapi_ticket后，更新数据库
                                wechat.setAccesstoken(access_token);
                                wechat.setJsapiticket(jsapi_ticket);
                                wechat.setTokenbuildtime(new Date());

                                // 更新
                                wechatService.updateByWechat(wechat);

                                // 返回数组
                                returnData[1] = jsapi_ticket;
                                return returnData;
                            }
                        }
                    } else {
                        // 数据库中有jsapi_ticket信息，并且没有已经超过有效时间
                        // 输出
                        logger.error("WechatController:getAppIdAndJsapiTicket:=============================数据库中有jsapi_ticket信息，并且没有超过1.5个小时");
                        System.out.println("WechatController:getAppIdAndJsapiTicket:=============================数据库中有jsapi_ticket信息，并且没有超过1.5个小时");
                        logger.error("WechatController:getAppIdAndJsapiTicket:=============================" + wechatList.get(0).getJsapiticket());
                        System.out.println("WechatController:getAppIdAndJsapiTicket:=============================" + wechatList.get(0).getJsapiticket());

                        // 返回数组
                        returnData[1] = wechatList.get(0).getJsapiticket();
                        return returnData;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 默认返回null
        return null;
    }

    /**
     * 获取openId
     *
     * @param code
     * @return Created by Lovelyz on 2019/05/22.
     */
    @ResponseBody
    @RequestMapping("/getuserinfo")
    public LayuiDataTemplet<WechatinfoEntity> getuserinfo(String encryptedData, String iv, String code) {
        LayuiDataTemplet<WechatinfoEntity> returnData = new LayuiDataTemplet<WechatinfoEntity>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        try {
            if (code == null || code.isEmpty()) {
                returnData.setMsg("code值为空！");
                return returnData;
            }
            // 输出
            logger.error("WechatRestful:queryOpenId:code:=============================" + code);
            System.out.println("WechatRestful:queryOpenId:code:=============================" + code);
//			openid 实体
            OpenIdEntity openIdEntity = new OpenIdEntity();
            String openId; // 返回数据 openid
            String session_key;  //返回的session_key
            WechatinfoEntity wechatinfoEntity = new WechatinfoEntity();
            // 获取微信相关数据
            // 查询的实体
            Wechat wechat = new Wechat();
            // 查询
            List<Wechat> wechatList = wechatService.selectByWechat(wechat);
            if (wechatList == null || wechatList.size() == 0) {
                returnData.setMsg("getuserinfo获取失败！");
            } else {
                // 获取数据
                wechat = wechatList.get(0);
                String AppID = wechat.getAppid();
                String AppSecret = wechat.getAppsecret();
                // 获取openId
                openIdEntity = OpenId.getOpenId(AppID, AppSecret, code);
                openId = openIdEntity.getOpenid();
                session_key = openIdEntity.getSession_key();
                // 返回数据
                if (openId == null || TextUtils.isEmpty(openId)) {
                    // 输出
                    logger.error("WechatRestful:queryOpenId:openId:=============================getuserinfo获取失败");
                    System.out.println("WechatRestful:queryOpenId:openId:=============================getuserinfo获取失败");
                    returnData.setMsg("getuserinfo获取失败！");
                } else {
                    //解密获取用户信息
                    com.alibaba.fastjson.JSONObject userInfoJSON = WechatGetUserInfoUtils.getUserInfo(encryptedData, session_key, iv);
                    System.out.println(userInfoJSON);
                    wechatinfoEntity = JSON.toJavaObject(userInfoJSON, WechatinfoEntity.class);
                    System.out.println(wechatinfoEntity);
                    // 输出
                    logger.error("WechatRestful:queryOpenId:openId:=============================" + openId);
                    System.out.println("WechatRestful:queryOpenId:openId:=============================" + openId);
                    returnData.setCount(1);
                    returnData.setCode(200);
                    returnData.setMsg(openId);
                    returnData.setDataone(wechatinfoEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            returnData.setMsg("getuserinfo获取失败！");
        }

        return returnData;
    }

    /**
     * 获取推广二维码
     *
     * @param
     * @return
     * @author WJF on 2018/10/12
     */
    @ResponseBody
    @RequestMapping("/getQRCode")
    public LayuiDataTemplet<WashUser> getExtensionQRCode(HttpServletRequest request, String userId) {
        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        String path = null;
        // 更新判断
        if (userId == null || userId.isEmpty()) {
            returnData.setMsg("缺少获取条件，推广二维码获取失败！");
            return returnData;
        } else {
            // 获取access_token
            String access_token = getAccessToken(wechatService);
            // 获取推广二维码
            //        生成推广码图片
            path = QrCodeUtils.CreateCode(request, userId, access_token);
            WashUser user = new WashUser();
            user.setUserId(userId); // 用户ID(YHBG+UUID)
            user.setUserRemark(path); // 用户备注(用户推广二维码图片路径)
            washUserService.updateByPrimaryKeySelective(user); // 更新

        }
        // 返回数据
        returnData.setCount(1);
        returnData.setMsg(path);
        return returnData;
    }


    /**
     * //    发送服务通知
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendTemplateMessage")
    public LayuiDataTemplet<WashUser> sendTemplateMessage(HttpServletRequest request, String userId) {
        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        String openId = "";
        String templateId = "";
        String formId = "";
//    for (int j = 0; j < wechatFormList.size(); j++) {
//        Date createTime = wechatFormList.get(j).getCreatedate();
//        Date nowTime = new Date();
//        long cha = nowTime.getTime() - createTime.getTime();
//        if (cha / (1000 * 60 * 60 * 24) > 6) {
//            List<Integer> deleteList = new ArrayList<Integer>();
//            deleteList.add(wechatFormList.get(j).getId());
//            wechatFormList.remove(j);
//            wechatFormService.deleteByIdList(deleteList);
//        }
//    }
//    openId = wechatFormList.get(0).getOpenid();
        templateId = "UIMqKoW_cRYMlO6Zs9IfHANwvJOs2WVO90poC9ZOlGM";
//    formId = wechatFormList.get(0).getFormid();
        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = getAccessToken(wechatService);
        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "平台确认收款：", null, null, null);
        boolean result = WechatFormUtil.sendTemplateMessage(access_token, jsonMsg);
        List<Integer> idList = new ArrayList<Integer>();
//    idList.add(wechatFormList.get(0).getId());
//    wechatFormService.deleteByIdList(idList);
        logger.error("服务通知result" + result);
        System.out.println("服务通知result" + result);
        return null;
    }

}
