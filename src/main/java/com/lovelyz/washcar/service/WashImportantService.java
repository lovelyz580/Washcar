package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashImportant;
import com.lovelyz.washcar.mapper.WashImportantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/06/24.
 */
@Service
public class WashImportantService {

	@Autowired
    private WashImportantMapper washImportantMapper;
    
    public int deleteByPrimaryKey(Integer importantId){
        return washImportantMapper.deleteByPrimaryKey(importantId);
    }

    public int insert(WashImportant record){
        return washImportantMapper.insert(record);
    }

    public int insertSelective(WashImportant record){
        return washImportantMapper.insertSelective(record);
    }

    public WashImportant selectByPrimaryKey(Integer importantId){
        return washImportantMapper.selectByPrimaryKey(importantId);
    }

    public int updateByPrimaryKeySelective(WashImportant record){
        return washImportantMapper.updateByPrimaryKeySelective(record);
    }

//    根据实体更新
    public int updateByPrimaryKey(WashImportant record){
        return washImportantMapper.updateByPrimaryKey(record);
    }

//    根据实体假删除
    public int updatebydel(WashImportant record){
        return washImportantMapper.updatebydel(record);
    }

//    精准查询返回集合
    public List<WashImportant> selectByCondition(WashImportant record){
        return washImportantMapper.selectByCondition(record);
    }

//    模糊查询  返回数据
    public List<WashImportant> selectByConditionData(WashImportant record){
        return washImportantMapper.selectByConditionData(record);
    }

    public List<WashImportant> selectAll(){
        return washImportantMapper.selectAll();
    }


//    根据实体查询数量
    public Integer count(WashImportant record){
        return washImportantMapper.count(record);
    }

//根据实体模糊查询数量
    public Integer countData(WashImportant record){
        return washImportantMapper.countData(record);
    }

    public int deleteByCondition(WashImportant record){
        return washImportantMapper.deleteByCondition(record);
    }

}
