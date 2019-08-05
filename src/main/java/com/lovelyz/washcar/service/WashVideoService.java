package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashVideo;
import com.lovelyz.washcar.mapper.WashVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/07/25.
 */
@Service
public class WashVideoService {

	@Autowired
    private WashVideoMapper washVideoMapper;
    
    public int deleteByPrimaryKey(Integer videoId){
        return washVideoMapper.deleteByPrimaryKey(videoId);
    }

    public int insert(WashVideo record){
        return washVideoMapper.insert(record);
    }

    public int insertSelective(WashVideo record){
        return washVideoMapper.insertSelective(record);
    }

    public WashVideo selectByPrimaryKey(Integer videoId){
        return washVideoMapper.selectByPrimaryKey(videoId);
    }

    public int updateByPrimaryKeySelective(WashVideo record){
        return washVideoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashVideo record){
        return washVideoMapper.updateByPrimaryKey(record);
    }

    public List<WashVideo> selectByCondition(WashVideo record){
        return washVideoMapper.selectByCondition(record);
    }

    public List<WashVideo> selectAll(){
        return washVideoMapper.selectAll();
    }


    public Integer count(WashVideo record){
        return washVideoMapper.count(record);
    }

    public int deleteByCondition(WashVideo record){
        return washVideoMapper.deleteByCondition(record);
    }

}
