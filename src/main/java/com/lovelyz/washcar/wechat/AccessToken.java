package com.lovelyz.washcar.wechat;

import com.lovelyz.washcar.wechat.entity.AccessTokenEntity;
import com.lovelyz.washcar.wechat.utils.HttpUtils;
import com.lovelyz.washcar.wechat.utils.JsonUtils;
import org.apache.log4j.Logger;

import java.util.TreeMap;

/**
 * 微信相关类
 * 
 * 获取access_token
 *
 * Created by Lovelyz on 2019/05/22.
 */

public class AccessToken {
	
	private static Logger logger = Logger.getLogger(AccessToken.class);

	/**
	 * 获取access_token
	 * 
	 * @param AppID 微信开发应用ID
	 * @param AppSecret 微信开发应用秘钥
	 * @return
	 */
	public static AccessTokenEntity getAccessToken(String AppID, String AppSecret) {
		// SortedMap方式
//		// 参数
//		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();  
//		packageParams.put("grant_type", "client_credential");
//		packageParams.put("appid", AppID);
//		packageParams.put("secret", AppSecret);
//		
//		// 将SortedMap转换为xml类型的String 
//      String requestXML = PayToolUtils.getRequestXml(packageParams);
//      // 输出
//      logger.error("AccessToken:requestXML:=============================" + requestXML);
//      System.out.println("AccessToken:requestXML:=============================" + requestXML);
//   
//		// 获取access_token
//      String responseXml = HttpPayUtils.postData(WechatConfig.WECHAT_GET_ACCESS_TOKEN_URL, requestXML); 
//      // 输出
//      logger.error("AccessToken:responseXml:=============================" + responseXml);
//      System.out.println("AccessToken:responseXml:=============================" + responseXml);
//   
//		// 将JSON类型的数据转换为实体类
//		AccessTokenEntity accessToken = JsonUtils.fromJson(responseXml, AccessTokenEntity.class);
//		
//		// 返回数据
//		return accessToken == null ? null : accessToken;
		
		// TreeMap方式
		// 参数
		TreeMap<String, String> requestMap = new TreeMap<String, String>();
		requestMap.put("appid", AppID);
		requestMap.put("secret", AppSecret);
		requestMap.put("grant_type", "client_credential");
		// 输出
		logger.error("AccessToken:requestMap:=============================" + requestMap);
		System.out.println("AccessToken:requestMap:=============================" + requestMap);
				
		String responseXml = HttpUtils.HttpsDefaultExecute("GET", WechatConfig.WECHAT_GET_ACCESS_TOKEN_URL, requestMap, null);
		// 输出
		logger.error("AccessToken:responseXml:=============================" + responseXml);
		System.out.println("AccessToken:responseXml:=============================" + responseXml);

		// 将JSON类型的数据转换为实体类
		AccessTokenEntity accessToken = JsonUtils.fromJson(responseXml, AccessTokenEntity.class);
				
		// 返回数据
		return accessToken == null ? null : accessToken;
	}
	
}
