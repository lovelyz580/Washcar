package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashEvaluate;

import java.util.List;

public interface WashEvaluateMapper {
    int deleteByPrimaryKey(String evaluateId);

    int insert(WashEvaluate record);

    int insertSelective(WashEvaluate record);

    WashEvaluate selectByPrimaryKey(String evaluateId);

    int updateByPrimaryKeySelective(WashEvaluate record);

    int updateByPrimaryKey(WashEvaluate record);

//    精准查询返回集合
    List<WashEvaluate> selectByCondition(WashEvaluate record);
//模糊查询返回集合
    List<WashEvaluate> selectByConditionData(WashEvaluate record);
    List<WashEvaluate> selectAll();
//    精准查询数量
    Integer count(WashEvaluate record);
//    模糊查询数量
    Integer countData(WashEvaluate record);
//    假删除
    int updatebydel(WashEvaluate record);
//    真删除
    int deleteByCondition(WashEvaluate record);
}