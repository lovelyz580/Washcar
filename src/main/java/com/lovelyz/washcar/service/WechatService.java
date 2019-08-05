package com.lovelyz.washcar.service;

import com.lovelyz.washcar.mapper.WechatMapper;
import com.lovelyz.washcar.wechat.entity.Wechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信相关数据表
 * 
 * service
 */
@Service
public class WechatService {

	@Autowired
	private WechatMapper wechatMapper;

	/**
	 * 根据Wechat实体更新
	 *
	 * @param wechat
	 * @return
	 * @throws Exception
	 */
	public int updateByWechat(Wechat wechat) throws Exception {
		return wechatMapper.updateByWechat(wechat);
	}

	/**
	 * 根据Wechat实体查询
	 *
	 * @param wechat
	 * @return
	 * @throws Exception
	 */
	public List<Wechat> selectByWechat(Wechat wechat) throws Exception {
		return wechatMapper.selectByWechat(wechat);
	}

}
