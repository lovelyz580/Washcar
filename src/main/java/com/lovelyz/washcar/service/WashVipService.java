package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashVip;
import com.lovelyz.washcar.mapper.WashVipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/07/04.
 */
@Service
public class WashVipService {

	@Autowired
    private WashVipMapper washVipMapper;
    
    public int deleteByPrimaryKey(String vipId){
        return washVipMapper.deleteByPrimaryKey(vipId);
    }

    public int insert(WashVip record){
        return washVipMapper.insert(record);
    }

    public int insertSelective(WashVip record){
        return washVipMapper.insertSelective(record);
    }

    public WashVip selectByPrimaryKey(String vipId){
        return washVipMapper.selectByPrimaryKey(vipId);
    }

    public int updateByPrimaryKeySelective(WashVip record){
        return washVipMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashVip record){
        return washVipMapper.updateByPrimaryKey(record);
    }

    public List<WashVip> selectByCondition(WashVip record){
        return washVipMapper.selectByCondition(record);
    }

    public List<WashVip> selectAll(){
        return washVipMapper.selectAll();
    }


    public Integer count(WashVip record){
        return washVipMapper.count(record);
    }

    public int deleteByCondition(WashVip record){
        return washVipMapper.deleteByCondition(record);
    }

//    假删除
    public int updatebydel(WashVip record){
        int count = 0;
        for (int a = 0;a<record.getIdlist().size();a++){
            count += washVipMapper.updatebydel(record.getIdlist().get(a));
        }
        return count;
    }

}
