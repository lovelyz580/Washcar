package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashCoupon;
import com.lovelyz.washcar.mapper.WashCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lovelyz on 2019/06/26.
 */
@Service
public class WashCouponService {

	@Autowired
    private WashCouponMapper washCouponMapper;
    
    public int deleteByPrimaryKey(String couponId){
        return washCouponMapper.deleteByPrimaryKey(couponId);
    }

    public int insert(WashCoupon record){
        return washCouponMapper.insert(record);
    }

    public int insertSelective(WashCoupon record){
        return washCouponMapper.insertSelective(record);
    }

    public WashCoupon selectByPrimaryKey(String couponId){
        return washCouponMapper.selectByPrimaryKey(couponId);
    }

    public int updateByPrimaryKeySelective(WashCoupon record){
        return washCouponMapper.updateByPrimaryKeySelective(record);
    }

//    根据实体更新上下架
    public int updatestatic(WashCoupon record){
        Integer  count = 0;
        WashCoupon washCoupon = new WashCoupon();
        for (int a = 0;a<record.getIdlist().size();a++){
            washCoupon.setCouponId(record.getIdlist().get(a));
            washCoupon.setCouponIseffect(record.getCouponIseffect());
            washCoupon.setCouponUpdatetime(record.getCouponUpdatetime());
            count += washCouponMapper.updataBystatic(washCoupon);
        }
        return count;
    }

    public int updateByPrimaryKey(WashCoupon record){
        return washCouponMapper.updateByPrimaryKey(record);
    }

    public List<WashCoupon> selectByCondition(WashCoupon record){
        return washCouponMapper.selectByCondition(record);
    }

    public List<WashCoupon> selectAll(){
        return washCouponMapper.selectAll();
    }


    public Integer count(WashCoupon record){
        return washCouponMapper.count(record);
    }


    //    根据实体假删除
    public int updatebydel(WashCoupon record){
        Integer  count = 0;
        WashCoupon washCoupon = new WashCoupon();
        for (int a = 0;a<record.getIdlist().size();a++){
            washCoupon.setCouponId(record.getIdlist().get(a));
            washCoupon.setCouponIsdel(record.getCouponIsdel());
            washCoupon.setCouponUpdatetime(record.getCouponUpdatetime());
            count += washCouponMapper.updatebydel(washCoupon);
        }
        return count;
    }
//    根据实体真删除
    public int deleteByCondition(WashCoupon record){
        return washCouponMapper.deleteByCondition(record);
    }



}
