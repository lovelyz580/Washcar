package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashVideo;

import java.util.List;

public interface WashVideoMapper {
    int deleteByPrimaryKey(Integer videoId);

    int insert(WashVideo record);

    int insertSelective(WashVideo record);

    WashVideo selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKeySelective(WashVideo record);

    int updateByPrimaryKey(WashVideo record);

    List<WashVideo> selectByCondition(WashVideo record);

    List<WashVideo> selectAll();


    Integer count(WashVideo record);

    int deleteByCondition(WashVideo record);
}