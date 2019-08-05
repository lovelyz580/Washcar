package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashStaff;
import com.lovelyz.washcar.entity.WashUser;

import java.util.List;

public interface WashUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(WashUser record);

    int insertSelective(WashUser record);

    WashUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WashUser record);

    int updateByPrimaryKey(WashUser record);

    List<WashUser> selectByCondition(WashUser record);

    List<WashUser> selectByConditionData(WashUser record);

    List<WashUser> selectAll();

    List<WashUser> selectByPage(WashUser record);

    Integer count(WashUser record);
    Integer countData(WashUser record);

    int deleteByCondition(WashUser record);
//   假删除
    int updatebydel(WashUser record);
}