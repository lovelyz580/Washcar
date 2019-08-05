package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashFee;

import java.util.List;

public interface WashFeeMapper {
    int deleteByPrimaryKey(String feeId);

    int insert(WashFee record);

    int insertSelective(WashFee record);

    WashFee selectByPrimaryKey(String feeId);

    int updateByPrimaryKeySelective(WashFee record);

    int updateByPrimaryKey(WashFee record);

    //精准查询
    List<WashFee> selectByCondition(WashFee record);

    //    模糊查询
    List<WashFee> selectByConditionData(WashFee record);

    List<WashFee> selectAll();

    List<WashFee> selectByPage(WashFee washFee);

    //    精准查询数量
    Integer count(WashFee record);

    //    模糊查询数量
    Integer countData(WashFee record);
//   真删除
    int deleteByCondition(WashFee record);
//    假删除
    int updatebydel(String feeId);
}