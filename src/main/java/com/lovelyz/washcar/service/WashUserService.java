package com.lovelyz.washcar.service;

import com.lovelyz.washcar.mapper.WashUserMapper;
import com.lovelyz.washcar.entity.WashUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashUserService {

	@Autowired
    private WashUserMapper washUserMapper;
    
    public int deleteByPrimaryKey(String userId){
        return washUserMapper.deleteByPrimaryKey(userId);
    }

    public int insert(WashUser record){
        return washUserMapper.insert(record);
    }

    public int insertSelective(WashUser record){
        return washUserMapper.insertSelective(record);
    }

    public WashUser selectByPrimaryKey(String userId){
        return washUserMapper.selectByPrimaryKey(userId);
    }

    public int updateByPrimaryKeySelective(WashUser record){
        return washUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashUser record){
        return washUserMapper.updateByPrimaryKey(record);
    }

//    精准查询数量
    public List<WashUser> selectByCondition(WashUser record){
        return washUserMapper.selectByCondition(record);
    }
//    模糊查询数量

    public List<WashUser> selectByConditionData(WashUser record){
        return washUserMapper.selectByConditionData(record);
    }
    public List<WashUser> selectAll(){
        return washUserMapper.selectAll();
    }

    public List<WashUser> selectByPage(WashUser washUser){
        return washUserMapper.selectByPage(washUser);
    }

//    精准查询数量
    public Integer count(WashUser record){
        return washUserMapper.count(record);
    }
//    模糊查询数量
    public Integer countData(WashUser record){
        return washUserMapper.countData(record);
    }
//根据实体假删除

    public int updatebydel(WashUser record){
        return washUserMapper.updatebydel(record);
    }
//    根据实体真删除
    public int deleteByCondition(WashUser record){
        return washUserMapper.deleteByCondition(record);
    }

}
