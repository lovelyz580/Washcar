package com.lovelyz.washcar.restful;


import com.lovelyz.washcar.entity.*;
import com.lovelyz.washcar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lovelyz on 2019/05/22.
 */
@Controller
@RequestMapping("/washOrder/")
public class WashOrderRestful {

    @Autowired
    WashOrderService washOrderService;
    @Autowired
    WashUserService washUserService;

    @Autowired
    WashImportantService washImportantService;

    @Autowired
    WashImportanttypeService washImportanttypeService;
    @Autowired
    WashImpuseService washImpuseService;

    /**
     * 根据订单id查询订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> selectByPrimaryKey(String orderId) {

        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washOrderService.selectByPrimaryKey(orderId));
        return returnData;
    }

    /**
     * 精准查询
     * 根据实体查询 返回集合
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> selectByCondition(@RequestBody WashOrder record) {

        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
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
            count = washOrderService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<WashOrder> washOrders = washOrderService.selectByCondition(record);
//            查询是否有洗车人员
                for (int a = 0; a < washOrders.size(); a++) {
                    if (washOrders.get(a).getOrderServiceid() != null) {
                        WashUser washUser = washUserService.selectByPrimaryKey(washOrders.get(a).getOrderServiceid());
                        washOrders.get(a).setUserRName(washUser.getUserRname());
                        System.out.println( washOrders.get(a).getUserRName());
                    }
                }

                returnData.setData(washOrders);
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }

    /**
     * 模糊查询
     * 根据实体查询 返回集合
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionData")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> selectByConditionData(@RequestBody WashOrder record) {

        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
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
            count = washOrderService.countData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washOrderService.selectByConditionData(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }

    /**
     * 模糊查询数量
     *
     * @param record
     * @return
     */
    @RequestMapping("countData")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> countData(@RequestBody WashOrder record) {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        int count = 0;
        count = washOrderService.countData(record); // 查询数量
        // 返回数据
        if (count == 0) {
            returnData.setCode(260);
            returnData.setCount(count);
            returnData.setMsg("暂无数据！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("查询成功！");
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
    public LayuiDataTemplet<WashOrder> insertSelective(@RequestBody WashOrder record) {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashOrder SelectData = new WashOrder();  //订单
        WashUser washUser = new WashUser();  //人
        SelectData.setOrderId(record.getOrderId()); //订单id
        int sum = 0;
        // 添加
        int count = 0;
        sum = washOrderService.count(record); // 查询数量
        if (sum > 0) {
            returnData.setCode(220);
            returnData.setMsg("该订单已创建，请重新！");
            return returnData;
        } else {
            if (record.getUserIsfirst() == 1) {
                washUser.setUserId(record.getOrderUserid());
                washUser.setUserIsfirst(0);
                washUser.setUserRemark(record.getOrderNumber());//车牌号
                washUser.setUserPhone(record.getOrderUserphone());
                washUser.setUserRname(record.getOrderUsername());
                System.out.println(washUser.getUserRemark());
                washUserService.updateByPrimaryKeySelective(washUser);
            }
            record.setOrderId("JD" + UUID.randomUUID().toString());
            record.setOrderStatic("TJ"); //订单状态
            record.setOrderCreatetime(new Date());//注册时间
            count = washOrderService.insertSelective(record);
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
    public LayuiDataTemplet<WashOrder> updateByPrimaryKeySelective(@RequestBody WashOrder record) {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getOrderId() == null || record.getOrderId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //检验是否有该条记录
            WashOrder SelectData = new WashOrder();
            SelectData.setOrderId(record.getOrderId());//用户ID
            int sum = 0;
            sum = washOrderService.count(SelectData); // 查询数量
            if (sum < 0) {
                returnData.setCode(300);
                returnData.setMsg("未找到该订单！");
                return returnData;
            } else {
                record.setOrderUpdatetime(new Date()); //更新时间
                WashUser washUser = new WashUser();
                washUser.setUserId(record.getOrderUserid());
                washUser.setUserRemark(record.getOrderNumber());//车牌号
                washUserService.updateByPrimaryKeySelective(washUser);
                count = washOrderService.updateByPrimaryKeySelective(record);
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
     * 订单派单洗车人员
     * @param record
     * @return
     */
    @RequestMapping("sendorder")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> sendorder(@RequestBody WashOrder record) {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getOrderId() == null || record.getOrderId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //检验是否有该条记录
            WashOrder SelectData = new WashOrder();
            SelectData.setOrderId(record.getOrderId());//用户ID
            int sum = 0;
            sum = washOrderService.count(SelectData); // 查询数量
            if (sum < 0) {
                returnData.setCode(300);
                returnData.setMsg("未找到该订单！");
                return returnData;
            } else {
                record.setOrderUpdatetime(new Date()); //更新时间
                record.setOrderUpdatebyuserid(record.getOrderUserid()); //更新人
                if (record.getOrderUserid() != null) {
                    WashUser washUser = new WashUser();
                    washUser.setUserId(record.getOrderUserid());
                    washUser.setUserRemark(record.getOrderNumber());
                    washUserService.updateByPrimaryKeySelective(washUser);
                    /**
                     *  通过洗车人员ID  发送短信通知  他有订单
                     */

                    /**
                     *  通过洗车人员ID  发送短信通知  他有订单
                     */

                }
                count = washOrderService.updateByPrimaryKeySelective(record);
                // 返回数据
                if (count == 0) {
                    returnData.setCode(300);
                    returnData.setMsg("派单失败！");
                } else {
                    returnData.setCode(200);
                    returnData.setCount(count);
                    returnData.setMsg("派单成功！");
                }
            }
            returnData.setCount(count);
            return returnData;
        }
    }




    /**
     * 根据实体
     * 假删除
     *
     * @param record
     * @return
     */
    @RequestMapping("updatebydel")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> updatebydel(@RequestBody WashOrder record) {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getOrderId() == null || record.getOrderId().isEmpty()) {
            returnData.setMsg("未找到该用户，删除失败！");
            return returnData;
        } else {
            count = washOrderService.updatebydel(record);
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
    public LayuiDataTemplet<WashOrder> deleteByCondition(@RequestBody WashOrder record) {

        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getOrderId() == null || record.getOrderId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washOrderService.deleteByCondition(record);
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
     * 报表查询
     * 报表查询  返回实体集合
     * 根据实体查询 返回集合
     *
     * @param record
     * @return
     */
    @RequestMapping("selectbyreport")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> selectbyreport(@RequestBody WashOrder record) throws ParseException {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
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
            count = washOrderService.countReport(record); // 查询数量

            System.out.println(count);
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<WashOrder> washOrders = washOrderService.selectByReport(record);
                WashOrder washOrder = new WashOrder();
                double profittotalmoney = 0;
                if (washOrders.size() > 0) {
                    for (int a = 0; a < washOrders.size(); a++) {
                        profittotalmoney = profittotalmoney + washOrders.get(a).getOrderPaymoney();
                    }
                    washOrder.setOrderIspay(washOrders.size());
                    washOrder.setOrderPaymoney(profittotalmoney);
                }
                washOrder.setOrderStatic("共计");
                washOrder.setOrderIspay(null);
                washOrders.add(washOrder);
                returnData.setData(washOrders);
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }


    /**
     * 查询各个状态下的数量
     * 根据实体查询 返回集合
     *
     * @param record
     * @return
     */
    @RequestMapping("selecreportcount")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> selecreportcount(@RequestBody WashOrder record) throws ParseException {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        WashOrder washOrder = new WashOrder();
        // 查询数量
        int count = 0;
        int ZLnum = 0;  //总量
        int YWCnum = 0;  //已完成量
        int WJDnum = 0;  //未接单
        int JXZnum = 0;  //进行中量
        int YQXnum = 0;  //已取消量
        int YJSnum = 0;   //已结束

        count = washOrderService.countReportCount(record); // 查询数量
        System.out.println(count);
        // 返回数据
        if (count == 0) {
            returnData.setCode(260);
            returnData.setCount(count);
            returnData.setMsg("暂无数据！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("查询成功！");
            record.setOrderStatic(null);
            ZLnum = washOrderService.countReportCount(record); //总量
            record.setOrderStatic("TJ");
            WJDnum = washOrderService.countReportCount(record);  //未接单
            record.setOrderStatic("JD");
            JXZnum = washOrderService.countReportCount(record); ///进行中
            record.setOrderStatic("WC");
            YWCnum = washOrderService.countReportCount(record); ///已完成量
            record.setOrderStatic("YWC");
            YJSnum = washOrderService.countReportCount(record); ///已结束量
            record.setOrderStatic("YQX");
            YQXnum = washOrderService.countReportCount(record); //已取消量

        }
        washOrder.setZLnum(ZLnum);
        washOrder.setWJDnum(WJDnum);
        washOrder.setJXZnum(JXZnum);
        washOrder.setYWCnum(YWCnum);
        washOrder.setYQXnum(YQXnum);
        washOrder.setYJSnum(YJSnum);
        returnData.setDataone(washOrder);

        return returnData;
    }

    /**
     * 模糊查询
     * 根据实体查询 返回集合
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionVague")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> selectByConditionVague(@RequestBody WashOrder record) {
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
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
            count = washOrderService.countsData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washOrderService.selectByConditionVague(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }


    //    查询未处理的订单
    @RequestMapping("untreated")
    @ResponseBody
    public LayuiDataTemplet<WashOrder> untreated(@RequestBody WashOrder record) {
        record.setOrderIsdel(1);
        record.setOrderStatic("TJ");
        LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        int count = 0;
        count = washOrderService.untreated(record); // 查询数量
        // 返回数据
        if (count == 0) {
            returnData.setCode(260);
            returnData.setCount(count);
            returnData.setMsg("暂无数据！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setMsg("查询成功！");
        }
        return returnData;
    }

/**
 * 会员点击付款，更新订单信息，更新会员使用信息，添加会员使用记录
 */
@RequestMapping("payupdateorder")
@ResponseBody
public LayuiDataTemplet<WashOrder> payupdateorder(@RequestBody WashOrder record) {
    LayuiDataTemplet<WashOrder> returnData = new LayuiDataTemplet<WashOrder>(); // 返回数据
    returnData.setCode(0); // 默认为0
    returnData.setCount(0); // 数据的数量，默认为0
    returnData.setData(null); // 数据List，默认为null
//    更新会员使用次数
    WashImportant washImportant = new WashImportant();
    WashImportanttype washImportanttype = new WashImportanttype();
    WashImpuse washImpuse = new WashImpuse();
    washImportant.setPagenumber(-1);
    washImportant.setImportanttypeUserid(record.getOrderUserid());
    List<WashImportant> washImportants =   washImportantService.selectByCondition(washImportant);
    washImportant = washImportants.get(0);
    washImportanttype = washImportanttypeService.selectByPrimaryKey(washImportant.getImportanttypeId());
    if (record.getOrderFeeid().equals("importanttypePtusenum")){ //普通洗车
        washImportanttype.setImportanttypePtusenum(washImportanttype.getImportanttypePtusenum()+1);
        washImpuse.setImpuseOther1("普通洗车");
    } else if (record.getOrderFeeid().equals("importanttypeFdusenum")){  //洗发动机
        washImportanttype.setImportanttypeFdusenum(washImportanttype.getImportanttypeFdusenum()+1);
        washImpuse.setImpuseOther1("发动机清洗");
    }else if (record.getOrderFeeid().equals("importanttypeKtusenum")){   //洗空调
        washImportanttype.setImportanttypeKtusenum(washImportanttype.getImportanttypeKtusenum()+1);
        washImpuse.setImpuseOther1("空调清洗");
    }else if (record.getOrderFeeid().equals("importanttypeNsusenum")){   //内饰
        washImportanttype.setImportanttypeNsusenum(washImportanttype.getImportanttypeNsusenum()+1);
        washImpuse.setImpuseOther1("内饰清洗");
    }
  int a =   washImportanttypeService.updateByPrimaryKeySelective(washImportanttype);
    // 添加会员使用记录
    washImpuse.setImpuseImportantid(String.valueOf(washImportant.getImportantId()));//会员ID
    washImpuse.setImpuseOrderid(record.getOrderId());//订单ID
    washImpuse.setImpuseUserid(record.getOrderUserid());//用户ID
    washImpuse.setImpuseCreatebyuserid(record.getOrderUserid());//创建人ID
    washImpuse.setImpuseCreatetime(new Date());//创建时间
    int b=  washImpuseService.insertSelective(washImpuse);

    // 更新订单状态
    WashOrder washOrder = new WashOrder();
    washOrder.setOrderId(record.getOrderId());//订单ID
    washOrder.setOrderStatic("YFK");
    washOrder.setOrderIspay(1);
    int c = washOrderService.updateByPrimaryKeySelective(washOrder);
    if (a+b+c==3){
        returnData.setCode(200);
        returnData.setMsg("更新成功");
    }else {
        returnData.setCode(500);
        returnData.setMsg("更新失败");
    }
    return returnData;
}
}

