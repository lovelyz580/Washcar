package com.lovelyz.washcar.mapper;


import com.lovelyz.washcar.entity.WashPayment;

import java.util.List;

public interface WashPaymentMapper {
    int deleteByPrimaryKey(String paymentId);

    int insert(WashPayment record);

    int insertSelective(WashPayment record);

    WashPayment selectByPrimaryKey(String paymentId);

    int updateByPrimaryKeySelective(WashPayment record);

    int updateByPrimaryKey(WashPayment record);

//    精准查询返回集合
    List<WashPayment> selectByCondition(WashPayment record);
//    模糊查询返回集合
    List<WashPayment> selectByConditionData(WashPayment record);
//    查询报表集合
    List<WashPayment> selectByReport(WashPayment record);

    List<WashPayment> selectAll();

    List<WashPayment> selectByPage(WashPayment washPayment);
//   精准查询数量
    Integer count(WashPayment record);

    //   精准查询报表数量
    Integer countByReport(WashPayment record);

//    模糊查询数量
Integer countData(WashPayment record);
//   真删除
    int deleteByCondition(WashPayment record);
//    假删除
    int updatebydel(WashPayment record);
    List<WashPayment> selectByConditionVague(WashPayment record);
    Integer countsData(WashPayment record);
}