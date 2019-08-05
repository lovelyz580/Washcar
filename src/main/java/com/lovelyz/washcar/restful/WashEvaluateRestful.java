package com.lovelyz.washcar.restful;


import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashEvaluate;
import com.lovelyz.washcar.entity.WashOrder;
import com.lovelyz.washcar.service.WashEvaluateService;
import com.lovelyz.washcar.service.WashOrderService;
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
 * 订单的评价管理
 */
@Controller
@RequestMapping("/washEvaluate/")
public class WashEvaluateRestful {

    @Autowired
    WashEvaluateService washEvaluateService;
    @Autowired
    WashOrderService washOrderService;


    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashEvaluate> selectByPrimaryKey(String evaluateId) {
        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setData(washEvaluateService.selectAll());
        return returnData;
    }

    /**
     * 条件查询
     * 精准查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashEvaluate> selectByCondition(@RequestBody WashEvaluate record) {
        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
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
            count = washEvaluateService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washEvaluateService.selectByCondition(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }

    /**
     * 条件查询
     * 模糊查询
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionData")
    @ResponseBody
    public LayuiDataTemplet<WashEvaluate> selectByConditionData(@RequestBody WashEvaluate record) {
        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
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
            count = washEvaluateService.countData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washEvaluateService.selectByConditionData(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }
    /**
     * 根基实体添加
     *
     * @param record
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public LayuiDataTemplet<WashEvaluate> insertSelective(@RequestBody WashEvaluate record) {
        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        //检验重复
        WashEvaluate SelectData = new WashEvaluate();
        SelectData.setEvaluateOrderid(record.getEvaluateOrderid());//评价订单id
        int sum = 0;
        // 添加
        int count = 0;
        sum = washEvaluateService.count(SelectData); // 查询数量
        if (sum > 0) {
//            存在执行更新
            SelectData.setPagenumber(-1);
            List<WashEvaluate> washEvaluates = washEvaluateService.selectByCondition(SelectData);
            WashEvaluate washEvaluate = washEvaluates.get(0);
            washEvaluate.setEvaluateIsgood(record.getEvaluateIsgood());//评分
            washEvaluate.setEvaluateContent(record.getEvaluateContent());//内容
            count = washEvaluateService.updateByPrimaryKeySelective(washEvaluate);
            //更新订单状态
            WashOrder order = new WashOrder();
            order.setOrderId(record.getEvaluateOrderid());
            order.setOrderStatic("YWC"); //已完成
            order.setOrderIseva(1);//已评价
            order.setOrderUpdatetime(new Date());
            washOrderService.updateByPrimaryKeySelective(order);
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("评价成功！");
            return returnData;
        } else {
            //            添加评价信息
            record.setEvaluateId(UUID.randomUUID().toString());
            record.setEvaluateCreatetime(new Date());
            //更新订单状态
            WashOrder order = new WashOrder();
            order.setOrderId(record.getEvaluateOrderid());
            order.setOrderStatic("YWC"); //已完成
            order.setOrderIseva(1);//已评价
            order.setOrderUpdatetime(new Date());
            washOrderService.updateByPrimaryKeySelective(order);
            count = washEvaluateService.insertSelective(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("评价失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("评价成功！");
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
    public LayuiDataTemplet<WashEvaluate> updateByPrimaryKeySelective(@RequestBody WashEvaluate record) {
        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getEvaluateId() == null || record.getEvaluateId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //检验是否有该条记录
            WashEvaluate SelectData = new WashEvaluate();
            SelectData.setEvaluateId(record.getEvaluateId());//评价订单ID
            int sum = 0;
            sum = washEvaluateService.count(SelectData); // 查询数量
            if (sum < 0) {
                returnData.setCode(300);
                returnData.setMsg("未找到该评价记录单！");
                return returnData;
            } else {
                count = washEvaluateService.updateByPrimaryKeySelective(record);
                // 返回数据
                if (count == 0) {
                    returnData.setCode(300);
                    returnData.setMsg("更新失败！");
                } else {
                    returnData.setCode(200);
                    returnData.setCount(count);
                    returnData.setMsg("更新成功！");
                }
            }
            returnData.setCount(count);
            return returnData;
        }
    }


    /**
     * 假删除
     *
     * @param record
     * @return
     */
    @RequestMapping("updatebydel")
    @ResponseBody
    public LayuiDataTemplet<WashEvaluate> updatebydel(@RequestBody WashEvaluate record) {
        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getEvaluateId() == null || record.getEvaluateId().isEmpty()) {
            returnData.setMsg("未找到该评价记录，删除失败！");
            return returnData;
        } else {
            count = washEvaluateService.updatebydel(record);
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
     * 根据实体真删除
     *
     * @param record
     * @return
     */
    @RequestMapping("deleteByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashEvaluate> deleteByCondition(@RequestBody WashEvaluate record) {

        LayuiDataTemplet<WashEvaluate> returnData = new LayuiDataTemplet<WashEvaluate>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getEvaluateId() == null || record.getEvaluateId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washEvaluateService.deleteByCondition(record);
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
