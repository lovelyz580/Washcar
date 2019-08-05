package com.lovelyz.washcar.restful;

import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashOrder;
import com.lovelyz.washcar.entity.WashPayment;
import com.lovelyz.washcar.service.WashOrderService;
import com.lovelyz.washcar.service.WashPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lovelyz on 2019/05/22.
 */
@Controller
@RequestMapping("/washPayment/")
public class WashPaymentRestful {

    @Autowired
    WashPaymentService washPaymentService;

    @Autowired
    WashOrderService washOrderService;

    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashPayment> selectByPrimaryKey(String paymentId) {

        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washPaymentService.selectByPrimaryKey(paymentId));
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
    public LayuiDataTemplet<WashPayment> selectByCondition(@RequestBody WashPayment record) {
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
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
        count = washPaymentService.count(record); // 查询数量
        // 返回数据
        if (count == 0) {
            returnData.setCode(260);
            returnData.setCount(count);
            returnData.setMsg("暂无数据！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("查询成功！");
            List<WashPayment> washPayments = washPaymentService.selectByCondition(record);
            for (int a = 0;a<washPayments.size();a++){
                WashOrder washOrder = washOrderService.selectByPrimaryKey(washPayments.get(a).getPaymentOrderid());
                washPayments.get(a).setWashOrder(washOrder);
            }
            returnData.setData(washPayments);
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
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionData")
    @ResponseBody
    public LayuiDataTemplet<WashPayment> selectByConditionData(@RequestBody WashPayment record) {
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
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
        count = washPaymentService.countData(record); // 查询数量
        // 返回数据
        if (count == 0) {
            returnData.setCode(260);
            returnData.setCount(count);
            returnData.setMsg("暂无数据！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("查询成功！");
            returnData.setData(washPaymentService.selectByConditionData(record));
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
    public LayuiDataTemplet<WashPayment> insertSelective(@RequestBody WashPayment record) {
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashPayment SelectData = new WashPayment();
        SelectData.setPaymentOrderid(record.getPaymentOrderid());//订单id
        SelectData.setPaymentCreatebyid(record.getPaymentCreatebyid()); //付款人id
        int sum = 0;
        // 添加
        int count = 0;
        sum = washPaymentService.count(record); // 查询数量
        if (sum > 0) {
            returnData.setCode(300);
            returnData.setMsg("该订单已付款！");
            return returnData;
        } else {
            record.setPaymentId(UUID.randomUUID().toString());
            count = washPaymentService.insertSelective(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("付款失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("付款成功！");
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
    public LayuiDataTemplet<WashPayment> updateByPrimaryKeySelective(@RequestBody WashPayment record) {
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getPaymentId() == null || record.getPaymentId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //检验是否有该条记录
            WashPayment SelectData = new WashPayment();
            SelectData.setPaymentId(record.getPaymentId());//付款订单ID
            int sum = 0;
            sum = washPaymentService.count(SelectData); // 查询数量
            if (sum < 0) {
                returnData.setCode(300);
                returnData.setMsg("未找到该付款记录单！");
                return returnData;
            } else {
                count = washPaymentService.updateByPrimaryKeySelective(record);
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
    public LayuiDataTemplet<WashPayment> updatebydel(@RequestBody WashPayment record) {
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getPaymentId() == null || record.getPaymentId().isEmpty()) {
            returnData.setMsg("未找到该收款记录单，删除失败！");
            return returnData;
        } else {
            count = washPaymentService.updatebydel(record);
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
     * @param record
     * @return
     */
    @RequestMapping("deleteByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashPayment> deleteByCondition(@RequestBody WashPayment record) {

        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getPaymentId() == null || record.getPaymentId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washPaymentService.deleteByCondition(record);
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
     * 报表查询  查询报表
     * 精准查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectreport")
    @ResponseBody
    public LayuiDataTemplet<WashPayment> selectreport(@RequestBody WashPayment record){
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
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
            count = washPaymentService.countByReport(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<WashPayment> washPayments = washPaymentService.selectByReport(record);
                WashPayment washPayment = new WashPayment();
                double profittotalmoney = 0;
                for (int a = 0;a<washPayments.size();a++){
                    WashOrder washOrder = washOrderService.selectByPrimaryKey(washPayments.get(a).getPaymentOrderid());
                    profittotalmoney = profittotalmoney+washPayments.get(a).getOrderAllmoney();
                    washPayments.get(a).setWashOrder(washOrder);
                }
                washPayment.setOrderNumber("1");
                washPayment.setOrderAllmoney(profittotalmoney);
                washPayments.add(washPayment);
                returnData.setData(washPayments);
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }
    /**
     * 条件查询洗车人姓名，洗车人电话，付款时间
     * 模糊查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionVague")
    @ResponseBody
    public LayuiDataTemplet<WashPayment> selectByConditionVague(@RequestBody WashPayment record) {
        LayuiDataTemplet<WashPayment> returnData = new LayuiDataTemplet<WashPayment>(); // 返回数据
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
            count = washPaymentService.countsData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washPaymentService.selectByConditionVague(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }
}




