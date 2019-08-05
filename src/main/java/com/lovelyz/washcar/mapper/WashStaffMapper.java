package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashStaff;

import java.util.List;

public interface WashStaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WashStaff record);

    int insertSelective(WashStaff record);

    WashStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WashStaff record);

    int updateByPrimaryKey(WashStaff record);

    List<WashStaff> selectByCondition(WashStaff record);

    List<WashStaff> selectAll();


    Integer count(WashStaff record);

    int deleteByCondition(WashStaff record);
}