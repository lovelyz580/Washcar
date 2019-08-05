package com.lovelyz.washcar.service;


import com.lovelyz.washcar.mapper.WashFeeMapper;
import com.lovelyz.washcar.entity.WashFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashFeeService {

	@Autowired
    private WashFeeMapper washFeeMapper;
    
    public int deleteByPrimaryKey(String feeId){
        return washFeeMapper.deleteByPrimaryKey(feeId);
    }

    public int insert(WashFee record){
        return washFeeMapper.insert(record);
    }

    public int insertSelective(WashFee record){
        return washFeeMapper.insertSelective(record);
    }

    public WashFee selectByPrimaryKey(String feeId){
        return washFeeMapper.selectByPrimaryKey(feeId);
    }

    public int updateByPrimaryKeySelective(WashFee record){
        return washFeeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashFee record){
        return washFeeMapper.updateByPrimaryKey(record);
    }

//    准确查询
    public List<WashFee> selectByCondition(WashFee record){
        return washFeeMapper.selectByCondition(record);
    }

//    模糊查询
    public List<WashFee> selectByConditionData(WashFee record){
        return washFeeMapper.selectByConditionData(record);
    }

    public List<WashFee> selectAll(){
        return washFeeMapper.selectAll();
    }

    public List<WashFee> selectByPage(WashFee  washFee){
        return washFeeMapper.selectByPage(washFee);
    }
//精准查询
    public Integer count(WashFee record){
        return washFeeMapper.count(record);
    }

//    模糊查询
    public Integer countData(WashFee record){
        return washFeeMapper.countData(record);
    }



//    根据实体假删除
public int updatebydel(WashFee record){
        int count = 0;
        for (int a = 0;a<record.getIdlist().size();a++){
            count += washFeeMapper.updatebydel(record.getIdlist().get(a));
        }
    return count;
}
//    真删除
    public int deleteByCondition(WashFee record){
        return washFeeMapper.deleteByCondition(record);
    }

}
