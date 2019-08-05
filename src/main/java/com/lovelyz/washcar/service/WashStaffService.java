package com.lovelyz.washcar.service;

import com.lovelyz.washcar.mapper.WashStaffMapper;
import com.lovelyz.washcar.entity.WashStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/05/22.
 */
@Service
public class WashStaffService {

	@Autowired
    private WashStaffMapper washStaffMapper;
    
    public int deleteByPrimaryKey(Integer id){
        return washStaffMapper.deleteByPrimaryKey(id);
    }

    public int insert(WashStaff record){
        return washStaffMapper.insert(record);
    }

    public int insertSelective(WashStaff record){
        return washStaffMapper.insertSelective(record);
    }

    public WashStaff selectByPrimaryKey(Integer id){
        return washStaffMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WashStaff record){
        return washStaffMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WashStaff record){
        return washStaffMapper.updateByPrimaryKey(record);
    }

    public List<WashStaff> selectByCondition(WashStaff record){
        return washStaffMapper.selectByCondition(record);
    }

    public List<WashStaff> selectAll(){
        return washStaffMapper.selectAll();
    }



    public Integer count(WashStaff record){
        return washStaffMapper.count(record);
    }

    public int deleteByCondition(WashStaff record){
        return washStaffMapper.deleteByCondition(record);
    }

}
