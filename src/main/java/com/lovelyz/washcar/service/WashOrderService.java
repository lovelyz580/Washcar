package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashOrder;
import com.lovelyz.washcar.mapper.WashOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashOrderService {

    @Autowired
    private WashOrderMapper washOrderMapper;

    public int deleteByPrimaryKey(String orderId) {
        return washOrderMapper.deleteByPrimaryKey(orderId);
    }

    public int insert(WashOrder record) {
        return washOrderMapper.insert(record);
    }

    public int insertSelective(WashOrder record) {
        return washOrderMapper.insertSelective(record);
    }

    public WashOrder selectByPrimaryKey(String orderId) {
        return washOrderMapper.selectByPrimaryKey(orderId);
    }

    public int updateByPrimaryKeySelective(WashOrder record) {
        return washOrderMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashOrder record) {
        return washOrderMapper.updateByPrimaryKey(record);
    }

    //    精准查询返回集合
    public List<WashOrder> selectByCondition(WashOrder record) {
        return washOrderMapper.selectByCondition(record);
    }
//    模糊查询返回集合
    public List<WashOrder> selectByConditionData(WashOrder record) {
        return washOrderMapper.selectByConditionData(record);
    }
//    精准查询报表 返回集合

    public List<WashOrder> selectByReport(WashOrder record) {
        return washOrderMapper.selectByReport(record);
    }

    public List<WashOrder> selectAll() {
        return washOrderMapper.selectAll();
    }

    public List<WashOrder> selectByPage(WashOrder washOrder) {
        return washOrderMapper.selectByPage(washOrder);
    }

    //    精准查询数量
    public Integer count(WashOrder record) {
        return washOrderMapper.count(record);
    }


//    报表查询数量

    public Integer countReport(WashOrder record) {
        return washOrderMapper.countReport(record);
    }


//    报表查询具体数量

    public Integer countReportCount(WashOrder record) {
        return washOrderMapper.countReportCount(record);
    }
//    模糊查询数量


    public Integer countData(WashOrder record) {
        return washOrderMapper.countData(record);
    }


//    查询未处理的订单
    public Integer untreated(WashOrder record) {
        return washOrderMapper.untreated(record);
    }


    //    假删除
    public int updatebydel(WashOrder record) {
        return washOrderMapper.updatebydel(record);
    }

    //    真删除
    public int deleteByCondition(WashOrder record) {
        return washOrderMapper.deleteByCondition(record);
    }

    public List<WashOrder> selectByConditionVague(WashOrder record){
        return washOrderMapper.selectByConditionVague(record);
    }
    public Integer countsData(WashOrder record){
        return washOrderMapper.countsData(record);
    }
}
