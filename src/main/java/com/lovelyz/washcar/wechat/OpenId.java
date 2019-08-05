package com.lovelyz.washcar.wechat;

import com.lovelyz.washcar.wechat.entity.OpenIdEntity;
import com.lovelyz.washcar.wechat.utils.HttpUtils;
import com.lovelyz.washcar.wechat.utils.JsonUtils;
import org.apache.log4j.Logger;
import java.util.TreeMap;

/**
 * openId
 *
 * Created by Lovelyz on 2019/05/22.
 */

public class OpenId {

	private static Logger logger = Logger.getLogger(OpenId.class);

	/**
	 * 获取openId
	 * 
	 * @param AppID 微信开发应用ID
	 * @param AppSecret 微信开发应用秘钥
	 * @param code 
	 * @return
	 */
	public static OpenIdEntity getOpenId(String AppID, String AppSecret, String code) {

		// TreeMap方式
		// 参数
		TreeMap<String, String> requestMap = new TreeMap<String, String>();
		requestMap.put("appid", AppID);
		requestMap.put("secret", AppSecret);
		requestMap.put("js_code", code);
		requestMap.put("grant_type", "authorization_code");
		// 输出
		logger.error("OpenId:requestMap:=============================" + requestMap);
		System.out.println("OpenId:requestMap:=============================" + requestMap);
		
		String responseXml = HttpUtils.HttpsDefaultExecute("GET", WechatConfig.WECHAT_GET_OPENID_URL, requestMap, null);
		// 输出
		logger.error("OpenId:responseXml:=============================" + responseXml);
		System.out.println("OpenId:responseXml:=============================" + responseXml);

		// 将JSON类型的数据转换为实体类
		OpenIdEntity openId = JsonUtils.fromJson(responseXml, OpenIdEntity.class);
		
		// 返回数据
		return openId == null ? null : openId;
	}

}
