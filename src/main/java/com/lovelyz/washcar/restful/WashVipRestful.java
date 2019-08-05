package com.lovelyz.washcar.restful;

import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashVip;
import com.lovelyz.washcar.service.WashVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * Created by Lovelyz on 2019/07/04.
 */
@Controller
@RequestMapping("/washVip/")
public class WashVipRestful {

    @Autowired
    WashVipService washVipService;

    /**
     *会员id  查询会员
     * @param rankId
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashVip> selectByPrimaryKey(String rankId) {
        LayuiDataTemplet<WashVip> returnData = new LayuiDataTemplet<WashVip>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washVipService.selectByPrimaryKey(rankId));
        return returnData;
    }

    /**
     * 条件查询  返回会员集合
     * 准确查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashVip> selectByCondition(@RequestBody WashVip record) {
        LayuiDataTemplet<WashVip> returnData = new LayuiDataTemplet<WashVip>(); // 返回数据
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
            count = washVipService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washVipService.selectByCondition(record));
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
    public LayuiDataTemplet<WashVip> insertSelective(@RequestBody WashVip record) {
        LayuiDataTemplet<WashVip> returnData = new LayuiDataTemplet<WashVip>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashVip SelectData = new WashVip();
        SelectData.setVipMoney(record.getVipMoney());//名称
        int sum = 0;
        // 添加
        int count = 0;
        sum = washVipService.count(record); // 查询数量
        if (sum > 0) {
            returnData.setCode(300);
            returnData.setMsg("该会员套餐已添加、请更换！");
            return returnData;
        } else {
            record.setVipId(UUID.randomUUID().toString());
            record.setVipCreatetime(new Date());
            count = washVipService.insertSelective(record);
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
    public LayuiDataTemplet<WashVip> updateByPrimaryKeySelective(@RequestBody WashVip record) {
        LayuiDataTemplet<WashVip> returnData = new LayuiDataTemplet<WashVip>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getVipId() == null || record.getVipId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //添加创建时间
            record.setVipCreatetime(new Date());
            count = washVipService.updateByPrimaryKeySelective(record);
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
    public LayuiDataTemplet<WashVip> updatebydel(@RequestBody WashVip record) {
        LayuiDataTemplet<WashVip> returnData = new LayuiDataTemplet<WashVip>(); // 返回数据
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
            count = washVipService.updatebydel(record);
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
    public LayuiDataTemplet<WashVip> deleteByCondition(@RequestBody WashVip record) {
        LayuiDataTemplet<WashVip> returnData = new LayuiDataTemplet<WashVip>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getVipId() == null || record.getVipId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washVipService.deleteByCondition(record);
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
