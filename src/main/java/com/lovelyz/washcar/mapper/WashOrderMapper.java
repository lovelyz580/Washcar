package com.lovelyz.washcar.mapper;


import com.lovelyz.washcar.entity.WashOrder;

import java.util.List;

public interface WashOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(WashOrder record);

    int insertSelective(WashOrder record);

    WashOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(WashOrder record);

    int updateByPrimaryKey(WashOrder record);

    //    精准查询返回集合
    List<WashOrder> selectByCondition(WashOrder record);

    //    报表查询  返回集合
    List<WashOrder> selectByReport(WashOrder record);

    //    模糊查询返回集合
    List<WashOrder> selectByConditionData(WashOrder record);

    List<WashOrder> selectAll();

    List<WashOrder> selectByPage(WashOrder washOrder);

    //    精准查询数量
    Integer count(WashOrder record);

    //    精准查询报表数量
    Integer countByReport(WashOrder record);

    //    查询报表数量
    Integer countReport(WashOrder record);

    //    查询报表具体数量
    Integer countReportCount(WashOrder record);

    //    模糊查询数量
    Integer countData(WashOrder record);


    //    查询未处理的订单
    Integer untreated(WashOrder record);

//    根据实体假删除

    int updatebydel(WashOrder record);

    //    根据实体真删除
    int deleteByCondition(WashOrder record);

    List<WashOrder> selectByConditionVague(WashOrder record);

    Integer countsData(WashOrder record);
}