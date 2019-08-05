package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashCoupon;

import java.util.List;

public interface WashCouponMapper {
    int deleteByPrimaryKey(String couponId);

    int insert(WashCoupon record);

    int insertSelective(WashCoupon record);

    WashCoupon selectByPrimaryKey(String couponId);

    int updateByPrimaryKeySelective(WashCoupon record);

//    根据实体更新状态
    int updataBystatic (WashCoupon record);

    int updateByPrimaryKey(WashCoupon record);

    List<WashCoupon> selectByCondition(WashCoupon record);

    List<WashCoupon> selectAll();


    Integer count(WashCoupon record);

//    根据实体真删除
    int deleteByCondition(WashCoupon record);
//    根据实体假删除
    int updatebydel(WashCoupon record);
}