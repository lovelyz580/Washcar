package com.lovelyz.washcar.wechat;

import com.lovelyz.washcar.wechat.entity.JsapiTicketEntity;
import com.lovelyz.washcar.wechat.utils.HttpUtils;
import com.lovelyz.washcar.wechat.utils.JsonUtils;
import org.apache.log4j.Logger;

import java.util.TreeMap;

/**
 * 微信相关类
 * 
 * 微信扫一扫功能
 * 
 * 获取jsapi_ticket
 */

public class JsapiTicket {

	private static Logger logger = Logger.getLogger(AccessToken.class);

	/**
	 * 获取jsapi_ticket
	 * 
	 * @param access_token 
	 * @return
	 */
	public static JsapiTicketEntity getJsapiTicket(String access_token) {
		// TreeMap方式
		// 参数
		TreeMap<String, String> requestMap = new TreeMap<String, String>();
		requestMap.put("access_token", access_token);
		requestMap.put("type", "jsapi");
		// 输出
		logger.error("JsapiTicket:requestMap:=============================" + requestMap);
		System.out.println("JsapiTicket:requestMap:=============================" + requestMap);
				
		String responseXml = HttpUtils.HttpsDefaultExecute("GET", WechatConfig.WECHAT_GET_JSAPI_TICKET_URL, requestMap, null);
		// 输出
		logger.error("JsapiTicket:responseXml:=============================" + responseXml);
		System.out.println("JsapiTicket:responseXml:=============================" + responseXml);

		// 将JSON类型的数据转换为实体类
		JsapiTicketEntity jsapiTicket = JsonUtils.fromJson(responseXml, JsapiTicketEntity.class);
				
		// 返回数据
		return jsapiTicket == null ? null : jsapiTicket;
	}
	
}
