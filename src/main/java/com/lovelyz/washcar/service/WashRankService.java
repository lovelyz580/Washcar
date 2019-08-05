package com.lovelyz.washcar.service;


import com.lovelyz.washcar.mapper.WashRankMapper;
import com.lovelyz.washcar.entity.WashRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashRankService {

	@Autowired
    private WashRankMapper washRankMapper;
    
    public int deleteByPrimaryKey(String rankId){
        return washRankMapper.deleteByPrimaryKey(rankId);
    }

    public int insert(WashRank record){
        return washRankMapper.insert(record);
    }

    public int insertSelective(WashRank record){
        return washRankMapper.insertSelective(record);
    }

    public WashRank selectByPrimaryKey(String rankId){
        return washRankMapper.selectByPrimaryKey(rankId);
    }

    public int updateByPrimaryKeySelective(WashRank record){
        return washRankMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashRank record){
        return washRankMapper.updateByPrimaryKey(record);
    }

    public List<WashRank> selectByCondition(WashRank record){
        return washRankMapper.selectByCondition(record);
    }

    public List<WashRank> selectByConditionData(WashRank record){
        return washRankMapper.selectByConditionData(record);
    }

    public List<WashRank> selectAll(){
        return washRankMapper.selectAll();
    }

    public List<WashRank> selectByPage(WashRank washRank){
        return washRankMapper.selectByPage(washRank);
    }

    public Integer count(WashRank record){
        return washRankMapper.count(record);
    }
    public Integer countData(WashRank record){
        return washRankMapper.countData(record);
    }
//    假删除
    public int updatebydel(WashRank record){
        int count = 0;
        for (int a = 0;a<record.getIdlist().size();a++){
            count += washRankMapper.updatebydel(record.getIdlist().get(a));
        }
        return count;
    }
//   真删除
    public int deleteByCondition(WashRank record){
        return washRankMapper.deleteByCondition(record);
    }

}
