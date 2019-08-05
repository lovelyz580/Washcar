package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashCouponuse;
import com.lovelyz.washcar.mapper.WashCouponuseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/07/16.
 */
@Service
public class WashCouponuseService {

	@Autowired
    private WashCouponuseMapper washCouponuseMapper;
    
    public int deleteByPrimaryKey(String couponuseId){
        return washCouponuseMapper.deleteByPrimaryKey(couponuseId);
    }

    public int insert(WashCouponuse record){
        return washCouponuseMapper.insert(record);
    }

    public int insertSelective(WashCouponuse record){
        return washCouponuseMapper.insertSelective(record);
    }

    public WashCouponuse selectByPrimaryKey(String couponuseId){
        return washCouponuseMapper.selectByPrimaryKey(couponuseId);
    }

    public int updateByPrimaryKeySelective(WashCouponuse record){
        return washCouponuseMapper.updateByPrimaryKeySelective(record);
    }


    public int updatestatic(WashCouponuse record){
        return washCouponuseMapper.updatestatic(record);
    }


    public int updatebydel(WashCouponuse record){
        return washCouponuseMapper.updatebydel(record);
    }

    public int updateByPrimaryKey(WashCouponuse record){
        return washCouponuseMapper.updateByPrimaryKey(record);
    }

    public List<WashCouponuse> selectByCondition(WashCouponuse record){
        return washCouponuseMapper.selectByCondition(record);
    }

    public List<WashCouponuse> selectAll(){
        return washCouponuseMapper.selectAll();
    }


    public Integer count(WashCouponuse record){
        return washCouponuseMapper.count(record);
    }

    public int deleteByCondition(WashCouponuse record){
        return washCouponuseMapper.deleteByCondition(record);
    }

}
