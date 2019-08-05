package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashImpuse;
import com.lovelyz.washcar.mapper.WashImpuseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lovelyz on 2019/06/24.
 */
@Service
public class WashImpuseService {

    @Autowired
    private WashImpuseMapper washImpuseMapper;

    public int deleteByPrimaryKey(Integer impuseId) {
        return washImpuseMapper.deleteByPrimaryKey(impuseId);
    }

    public int insert(WashImpuse record) {
        return washImpuseMapper.insert(record);
    }

    public int insertSelective(WashImpuse record) {
        return washImpuseMapper.insertSelective(record);
    }

    public WashImpuse selectByPrimaryKey(Integer impuseId) {
        return washImpuseMapper.selectByPrimaryKey(impuseId);
    }


    //    根据实体更新
    public int updateByPrimaryKeySelective(WashImpuse record) {
        return washImpuseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashImpuse record) {
        return washImpuseMapper.updateByPrimaryKey(record);
    }

    //    实体查询 返回集合
    public List<WashImpuse> selectByCondition(WashImpuse record) {
        return washImpuseMapper.selectByCondition(record);
    }

    //    实体模糊查询  返回集合
    public List<WashImpuse> selectByConditionData(WashImpuse record) {
        return washImpuseMapper.selectByConditionData(record);
    }

    public List<WashImpuse> selectAll() {
        return washImpuseMapper.selectAll();
    }

    //实体查询  数量
    public Integer count(WashImpuse record) {
        return washImpuseMapper.count(record);
    }

    //    模糊实体查询数量
    public Integer countData(WashImpuse record) {
        return washImpuseMapper.countData(record);
    }

    //    真删除
    public int deleteByCondition(WashImpuse record) {
        return washImpuseMapper.deleteByCondition(record);
    }

    //    假删除
    public int updatebydel(WashImpuse record) {
        return washImpuseMapper.updatebydel(record);
    }

}
