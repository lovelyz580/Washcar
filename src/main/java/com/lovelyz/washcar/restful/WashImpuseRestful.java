package com.lovelyz.washcar.restful;


import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashImpuse;
import com.lovelyz.washcar.service.WashImpuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.UUID;

/**
 *会员使用记录
 * Created by Lovelyz on 2019/06/24.
 */
@Controller
@RequestMapping("/washImpuse/")
public class WashImpuseRestful {

    @Autowired
    WashImpuseService washImpuseService;

    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashImpuse> selectByPrimaryKey(Integer paymentId) {

        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washImpuseService.selectByPrimaryKey(paymentId));
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
    public LayuiDataTemplet<WashImpuse> selectByCondition(@RequestBody WashImpuse record) {
        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
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
            count = washImpuseService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<WashImpuse> washImpuses = washImpuseService.selectByCondition(record);
                returnData.setData(washImpuses);
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
    public LayuiDataTemplet<WashImpuse> selectByConditionData(@RequestBody WashImpuse record) {
        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
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
            count = washImpuseService.countData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washImpuseService.selectByConditionData(record));
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
    public LayuiDataTemplet<WashImpuse> insertSelective(@RequestBody WashImpuse record) {
        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashImpuse SelectData = new WashImpuse();
        SelectData.setImpuseOrderid(record.getImpuseOrderid());//订单id
        SelectData.setImpuseImportantid(record.getImpuseImportantid()); //优惠id
        int sum = 0;
        // 添加
        int count = 0;
        sum = washImpuseService.count(record); // 查询数量
        if (sum > 0) {
            returnData.setCode(300);
            returnData.setMsg("该订单已付款！");
            return returnData;
        } else {
            record.setImpuseImportantid(UUID.randomUUID().toString());
            count = washImpuseService.insertSelective(record);
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
    public LayuiDataTemplet<WashImpuse> updateByPrimaryKeySelective(@RequestBody WashImpuse record) {
        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getImpuseImportantid() == null || record.getImpuseImportantid().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //检验是否有该条记录
            WashImpuse SelectData = new WashImpuse();
            SelectData.setImpuseImportantid(record.getImpuseImportantid());//付款订单ID
            int sum = 0;
            sum = washImpuseService.count(SelectData); // 查询数量
            if (sum < 0) {
                returnData.setCode(300);
                returnData.setMsg("未找到该付款记录单！");
                return returnData;
            } else {
                count = washImpuseService.updateByPrimaryKeySelective(record);
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
    public LayuiDataTemplet<WashImpuse> updatebydel(@RequestBody WashImpuse record) {
        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getImpuseImportantid() == null || record.getImpuseImportantid().isEmpty()) {
            returnData.setMsg("未找到该收款记录单，删除失败！");
            return returnData;
        } else {
            count = washImpuseService.updatebydel(record);
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
    public LayuiDataTemplet<WashImpuse> deleteByCondition(@RequestBody WashImpuse record) {

        LayuiDataTemplet<WashImpuse> returnData = new LayuiDataTemplet<WashImpuse>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getImpuseImportantid() == null || record.getImpuseImportantid().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washImpuseService.deleteByCondition(record);
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
