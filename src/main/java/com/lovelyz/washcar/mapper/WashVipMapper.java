package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashVip;

import java.util.List;

public interface WashVipMapper {
    int deleteByPrimaryKey(String vipId);

    int insert(WashVip record);

    int insertSelective(WashVip record);

    WashVip selectByPrimaryKey(String vipId);

    int updateByPrimaryKeySelective(WashVip record);

    int updateByPrimaryKey(WashVip record);

    List<WashVip> selectByCondition(WashVip record);

    List<WashVip> selectAll();


    Integer count(WashVip record);

    int deleteByCondition(WashVip record);

//    假删除
    int updatebydel(String record);
}