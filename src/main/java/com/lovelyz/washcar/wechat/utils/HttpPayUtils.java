package com.lovelyz.washcar.wechat.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * 微信工具类
 * 
 * HTTP工具
 * 
 * 负责发起get、post请求并获取返回数据
 *
 * Created by Lovelyz on 2019/05/22.
 */

public class HttpPayUtils {

	private final static String DEFAULT_ENCODING = "UTF-8"; // 字符编码
	private final static int CONNECT_TIMEOUT = 5000; // 连接超时

	/**
	 * GET方法获取数据
	 * 
	 * @param urlStr
	 * @param data
	 * @return
	 */
	public static String getData(String urlStr, String data) {
		return reader(urlStr, data, "GET");
	}
	
	/**
	 * POST方法获取数据
	 * 
	 * @param urlStr
	 * @param data
	 * @return
	 */
	public static String postData(String urlStr, String data) {
		return reader(urlStr, data, "POST");
	}

	public static String reader(String urlStr, String data, String contentType) {
		BufferedReader reader = null;
		
		try {
			URL url = new URL(urlStr);
			
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(CONNECT_TIMEOUT);
			if (contentType != null) {
				conn.setRequestProperty("content-type", contentType);
			}
			
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
			if (data == null) {
				data = "";
			}
			writer.write(data);
			writer.flush();
			writer.close();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			return sb.toString();
		} catch (IOException e) {
			// logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				
			}
		}
		
		return null;
	}

}
