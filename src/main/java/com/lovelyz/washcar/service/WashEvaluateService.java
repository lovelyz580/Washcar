package com.lovelyz.washcar.service;


import com.lovelyz.washcar.mapper.WashEvaluateMapper;
import com.lovelyz.washcar.entity.WashEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashEvaluateService {

	@Autowired
    private WashEvaluateMapper washEvaluateMapper;
    
    public int deleteByPrimaryKey(String evaluateId){
        return washEvaluateMapper.deleteByPrimaryKey(evaluateId);
    }

    public int insert(WashEvaluate record){
        return washEvaluateMapper.insert(record);
    }

    public int insertSelective(WashEvaluate record){
        return washEvaluateMapper.insertSelective(record);
    }

    public WashEvaluate selectByPrimaryKey(String evaluateId){
        return washEvaluateMapper.selectByPrimaryKey(evaluateId);
    }

    public int updateByPrimaryKeySelective(WashEvaluate record){
        return washEvaluateMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashEvaluate record){
        return washEvaluateMapper.updateByPrimaryKey(record);
    }
//精准查询返回集合
    public List<WashEvaluate> selectByCondition(WashEvaluate record){
        return washEvaluateMapper.selectByCondition(record);
    }
//    模糊查询返回集合

    public List<WashEvaluate> selectByConditionData(WashEvaluate record){
        return washEvaluateMapper.selectByConditionData(record);
    }
    public List<WashEvaluate> selectAll(){
        return washEvaluateMapper.selectAll();
    }

//    精准查询数量
    public Integer count(WashEvaluate record){
        return washEvaluateMapper.count(record);
    }

//    模糊查询数量
    public Integer countData(WashEvaluate record){
        return washEvaluateMapper.countData(record);
    }

//    根据实体假删除

    public int updatebydel(WashEvaluate record){
        return washEvaluateMapper.updatebydel(record);
    }
//    根据实体删除
    public int deleteByCondition(WashEvaluate record){
        return washEvaluateMapper.deleteByCondition(record);
    }

}
