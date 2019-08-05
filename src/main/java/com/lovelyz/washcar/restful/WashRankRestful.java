package com.lovelyz.washcar.restful;


import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashRank;
import com.lovelyz.washcar.service.WashRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lovelyz on 2019/05/22.
 */
@Controller
@RequestMapping("/washRank/")
public class WashRankRestful {

    @Autowired
    WashRankService washRankService;


    /**
     * 根据车辆级别id 查询车辆级别信息
     *
     * @param rankId
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashRank> selectByPrimaryKey(String rankId) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washRankService.selectByPrimaryKey(rankId));
        return returnData;
    }

    /**
     * 条件查询  返回车辆级别集合
     * 准确查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashRank> selectByCondition(@RequestBody WashRank record) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        if (record.getPagenumber() != null) {
            // 计算偏移量
            if (record.getPagenumber() != -1) {
                if (record.getPagesize() == null) {
                    returnData.setMsg("传递的分页数据(每页数量)错误！");
                    return returnData;
                }
                // 获取传递过来的数据
                int pageNumber = record.getPagenumber();
                int pageSize = record.getPagesize();
                record.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
                record.setPagesize(pageSize); // 每页的数据量
            }
            // 查询数量
            int count = 0;
            count = washRankService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washRankService.selectByCondition(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }

    /**
     * 条件查询 返回车辆级别集合
     * 模糊查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionData")
    @ResponseBody
    public LayuiDataTemplet<WashRank> selectByConditionData(@RequestBody WashRank record) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        if (record.getPagenumber() != null) {
            // 计算偏移量
            if (record.getPagenumber() != -1) {
                if (record.getPagesize() == null) {
                    returnData.setMsg("传递的分页数据(每页数量)错误！");
                    return returnData;
                }
                // 获取传递过来的数据
                int pageNumber = record.getPagenumber();
                int pageSize = record.getPagesize();
                record.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
                record.setPagesize(pageSize); // 每页的数据量
            }
            // 查询数量
            int count = 0;
            record.setRankIsdel(1);
            count = washRankService.countData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washRankService.selectByConditionData(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }

    /**
     * 根据实体添加
     *
     * @param record
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public LayuiDataTemplet<WashRank> insertSelective(@RequestBody WashRank record) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashRank SelectData = new WashRank();
        SelectData.setRankName(record.getRankName());//名称
        int sum = 0;
        // 添加
        int count = 0;
        sum = washRankService.count(record); // 查询数量
        if (sum > 0) {
            returnData.setCode(300);
            returnData.setMsg("该车辆级别已添加、请更换！");
            return returnData;
        } else {
            record.setRankId(UUID.randomUUID().toString());
            record.setRankCreatetime(new Date());
            count = washRankService.insertSelective(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("添加失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("添加成功！");
        }
        return returnData;
    }

    /**
     * 根据实体更新
     *
     * @param record
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public LayuiDataTemplet<WashRank> updateByPrimaryKeySelective(@RequestBody WashRank record) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getRankId() == null || record.getRankId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //添加创建时间
            record.setRankCreatetime(new Date());
            count = washRankService.updateByPrimaryKeySelective(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("更新失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("更新成功！");
        }

        returnData.setCount(count);
        return returnData;
    }


    /**
     * 假删除
     *
     * @param record
     * @return
     */
    @RequestMapping("updatebydel")
    @ResponseBody
    public LayuiDataTemplet<WashRank> updatebydel(@RequestBody WashRank record) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getIdlist().size() == 0 || record.getIdlist().isEmpty()) {
            returnData.setMsg("缺少参数，删除失败！");
            return returnData;
        } else {
            count = washRankService.updatebydel(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("删除失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("删除成功！");
        }
        return returnData;
    }

    /**
     * 真删除
     *
     * @param record
     * @return
     */
    @RequestMapping("deleteByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashRank> deleteByCondition(@RequestBody WashRank record) {
        LayuiDataTemplet<WashRank> returnData = new LayuiDataTemplet<WashRank>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getRankId() == null || record.getRankId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washRankService.deleteByCondition(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("删除失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("删除成功！");
        }
        return returnData;
    }


}
