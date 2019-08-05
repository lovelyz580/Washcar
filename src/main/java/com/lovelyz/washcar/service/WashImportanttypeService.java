package com.lovelyz.washcar.service;

import com.lovelyz.washcar.entity.WashImportanttype;
import com.lovelyz.washcar.mapper.WashImportanttypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lovelyz on 2019/07/04.
 */
@Service
public class WashImportanttypeService {

    @Autowired
    private WashImportanttypeMapper washImportanttypeMapper;

    //根据实体添加
    public int insertSelective(WashImportanttype record) {
        return washImportanttypeMapper.insertSelective(record);
    }

    //    根据ID查询实体
    public WashImportanttype selectByPrimaryKey(String importanttypeId) {
        return washImportanttypeMapper.selectByPrimaryKey(importanttypeId);
    }

    //    根据实体更新
    public int updateByPrimaryKeySelective(WashImportanttype record) {
        return washImportanttypeMapper.updateByPrimaryKeySelective(record);
    }


}
