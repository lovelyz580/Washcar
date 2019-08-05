package com.lovelyz.washcar.mapper;


import com.lovelyz.washcar.entity.WashRank;

import java.util.List;

public interface WashRankMapper {
    int deleteByPrimaryKey(String rankId);

    int insert(WashRank record);

    int insertSelective(WashRank record);

    WashRank selectByPrimaryKey(String rankId);

    int updateByPrimaryKeySelective(WashRank record);

    int updateByPrimaryKey(WashRank record);

    List<WashRank> selectByCondition(WashRank record);

    List<WashRank> selectByConditionData(WashRank record);
    List<WashRank> selectAll();

    List<WashRank> selectByPage(WashRank washRank);
//   准确查询
    Integer count(WashRank record);
//    模糊查询
    Integer countData(WashRank record);
//   实体真删除
    int deleteByCondition(WashRank record);
    //   实体假删除
    int updatebydel(String record);

}