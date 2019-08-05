package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashImpuse;

import java.util.List;

public interface WashImpuseMapper {
    int deleteByPrimaryKey(Integer impuseId);

    int insert(WashImpuse record);

    int insertSelective(WashImpuse record);

    WashImpuse selectByPrimaryKey(Integer impuseId);

//    根据实体更新
    int updateByPrimaryKeySelective(WashImpuse record);


    int updateByPrimaryKey(WashImpuse record);

    //    实体查询  返回集合
    List<WashImpuse> selectByCondition(WashImpuse record);

    //    模糊查询返回集合
    List<WashImpuse> selectByConditionData(WashImpuse record);

    List<WashImpuse> selectAll();

    //实体查询返回数量
    Integer count(WashImpuse record);

    //模糊查询 返回数量
    Integer countData(WashImpuse record);

    //    真删除
    int deleteByCondition(WashImpuse record);

    //    假删除
    int updatebydel(WashImpuse record);

}