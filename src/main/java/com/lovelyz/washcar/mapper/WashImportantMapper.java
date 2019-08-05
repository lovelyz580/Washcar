package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashImportant;

import java.util.List;

public interface WashImportantMapper {
    int deleteByPrimaryKey(Integer importantId);

    int insert(WashImportant record);

    int insertSelective(WashImportant record);

    WashImportant selectByPrimaryKey(Integer importantId);

    //    根据实体更新
    int updateByPrimaryKeySelective(WashImportant record);

    int updateByPrimaryKey(WashImportant record);

    //    根据实体假删除
    int updatebydel(WashImportant record);

    //精准查询返回集合
    List<WashImportant> selectByCondition(WashImportant record);

    //    模糊查询返回集合
    List<WashImportant> selectByConditionData(WashImportant record);

    List<WashImportant> selectAll();

    //精准查询数量
    Integer count(WashImportant record);

    //    模糊查询数量
    Integer countData(WashImportant record);
    
    int deleteByCondition(WashImportant record);
}