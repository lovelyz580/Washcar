package com.lovelyz.washcar.restful;

import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashCoupon;
import com.lovelyz.washcar.service.WashCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lovelyz on 2019/06/26.
 */
@Controller
@RequestMapping("/washCoupon/")
public class WashCouponRestful {

    @Autowired
    WashCouponService washCouponService;


    /**
     * 查询单个优惠活动
     *
     * @param couponId
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> selectByPrimaryKey(String couponId) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washCouponService.selectByPrimaryKey(couponId));
        return returnData;
    }

    /**
     * 查询优惠活动集合
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> selectByCondition(@RequestBody WashCoupon record) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
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
            count = washCouponService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washCouponService.selectByCondition(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }


    /**
     * 创建优惠券
     *
     * @param record
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> insertSelective(@RequestBody WashCoupon record) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        //检验重复
        WashCoupon SelectData = new WashCoupon();

        SelectData.setCouponMoney(record.getCouponMoney());//优惠金额
        SelectData.setCouponRemoney(record.getCouponRemoney());//金额限制
        SelectData.setCouponStarttime(record.getCouponStarttime()); //有效开始时间
        SelectData.setCouponEndtime(record.getCouponEndtime());//有效结束时间
        int sum = 0;
        // 添加
        int count = 0;
        sum = washCouponService.count(SelectData); // 查询数量
        if (sum > 0) {
            returnData.setCode(220);
            returnData.setMsg("该优惠已创建，请更换！");
            return returnData;
        } else {
            //            添加优惠券信息
            record.setCouponId(UUID.randomUUID().toString());
            record.setCouponCreatetime(new Date());
            count = washCouponService.insertSelective(record);
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("优惠添加失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("优惠添加成功！");
        }
        return returnData;
    }


    /**
     * 更新优惠券
     *
     * @param record
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> updateByPrimaryKeySelective(@RequestBody WashCoupon record) {

        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getCouponId() == null || record.getCouponId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            record.setCouponUpdatetime(new Date()); //更新时间
            count = washCouponService.updateByPrimaryKeySelective(record);
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



    /**
     * 批量更新优惠券
     *
     * @param record
     * @return
     */
    @RequestMapping("updatestatic")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> updatestatic(@RequestBody WashCoupon record) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getIdlist().size()<1) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            record.setCouponUpdatetime(new Date()); //更新时间
            count = washCouponService.updatestatic(record);
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



    /**
     * 根据实体假删除
     *
     * @param record
     * @return
     */
    @RequestMapping("updatebydel")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> updatebydel(@RequestBody WashCoupon record) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getIdlist().size()<1) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            record.setCouponUpdatetime(new Date());
            count = washCouponService.updatebydel(record);
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
     * 根据实体删除
     *
     * @param record
     * @return
     */
    @RequestMapping("deleteByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashCoupon> deleteByCondition(@RequestBody WashCoupon record) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet<WashCoupon>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getCouponId() == null || record.getCouponId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washCouponService.deleteByCondition(record);
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
