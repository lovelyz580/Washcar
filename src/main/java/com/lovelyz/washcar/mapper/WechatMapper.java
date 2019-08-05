package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.wechat.entity.Wechat;


import java.util.List;

/**
 * 微信相关数据表
 * 
 * mapper
 */

public interface WechatMapper {
	
    int insert(Wechat record);

    int insertSelective(Wechat record);

    int updateByPrimaryKeySelective(Wechat record);

    int updateByPrimaryKey(Wechat record);
    
    Wechat selectByPrimaryKey(Integer id);
    
    int deleteByPrimaryKey(Integer id);
    
    /**
	 * 根据Y_BG_Wechat实体更新
	 * 
	 * @param wechat
	 * @return
	 */
	int updateByWechat(Wechat wechat);
	
    /**
	 * 根据Y_BG_Wechat实体查询
	 * 
	 * @param wechat
	 * @return
	 */
	List<Wechat> selectByWechat(Wechat wechat);
	
}