package com.lovelyz.washcar.service;

import com.lovelyz.washcar.mapper.WashPaymentMapper;
import com.lovelyz.washcar.entity.WashPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashPaymentService {

	@Autowired
    private WashPaymentMapper washPaymentMapper;
    
    public int deleteByPrimaryKey(String paymentId){
        return washPaymentMapper.deleteByPrimaryKey(paymentId);
    }

    public int insert(WashPayment record){
        return washPaymentMapper.insert(record);
    }

    public int insertSelective(WashPayment record){
        return washPaymentMapper.insertSelective(record);
    }

    public WashPayment selectByPrimaryKey(String paymentId){
        return washPaymentMapper.selectByPrimaryKey(paymentId);
    }

    public int updateByPrimaryKeySelective(WashPayment record){
        return washPaymentMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashPayment record){
        return washPaymentMapper.updateByPrimaryKey(record);
    }

//    精准查询 返回集合
    public List<WashPayment> selectByCondition(WashPayment record){
        return washPaymentMapper.selectByCondition(record);
    }
//    模糊查询返回集合

    public List<WashPayment> selectByConditionData(WashPayment record){
        return washPaymentMapper.selectByConditionData(record);
    }

//    查询报表集合
    public List<WashPayment> selectByReport(WashPayment record){
        return washPaymentMapper.selectByReport(record);
    }


    public List<WashPayment> selectAll(){
        return washPaymentMapper.selectAll();
    }

    public List<WashPayment> selectByPage(WashPayment washPayment){
        return washPaymentMapper.selectByPage(washPayment);
    }
//精准查询数量
    public Integer count(WashPayment record){
        return washPaymentMapper.count(record);
    }

    //精准查询报表数量
    public Integer countByReport(WashPayment record){
        return washPaymentMapper.countByReport(record);
    }
//    模糊查询数量
    public Integer countData(WashPayment record){
        return washPaymentMapper.countData(record);
    }
//    假删除

    public int updatebydel(WashPayment record){
        return washPaymentMapper.updatebydel(record);
    }
//   真删除
    public int deleteByCondition(WashPayment record){
        return washPaymentMapper.deleteByCondition(record);
    }
    public List<WashPayment> selectByConditionVague(WashPayment record){
        return washPaymentMapper.selectByConditionVague(record);
    }
    public Integer countsData(WashPayment record){
        return washPaymentMapper.countsData(record);
    }
}
