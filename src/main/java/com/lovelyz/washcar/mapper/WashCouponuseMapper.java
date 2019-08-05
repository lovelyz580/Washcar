package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashCouponuse;

import java.util.List;

public interface WashCouponuseMapper {
    int deleteByPrimaryKey(String couponuseId);

    int insert(WashCouponuse record);

    int insertSelective(WashCouponuse record);

    WashCouponuse selectByPrimaryKey(String couponuseId);

    int updateByPrimaryKeySelective(WashCouponuse record);


    int updatestatic(WashCouponuse record);


    int updatebydel(WashCouponuse record);

    int updateByPrimaryKey(WashCouponuse record);

    List<WashCouponuse> selectByCondition(WashCouponuse record);

    List<WashCouponuse> selectAll();


    Integer count(WashCouponuse record);

    int deleteByCondition(WashCouponuse record);
}