package com.lovelyz.washcar.mapper;

import com.lovelyz.washcar.entity.WashImportanttype;

import java.util.List;

public interface WashImportanttypeMapper {

    //根据实体添加
    int insertSelective(WashImportanttype record);

    //根据ID 查询  返回实体
    WashImportanttype selectByPrimaryKey(String importanttypeId);

    //根据实体更新
    int updateByPrimaryKeySelective(WashImportanttype record);


}