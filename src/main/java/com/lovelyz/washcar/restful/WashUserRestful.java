package com.lovelyz.washcar.restful;


import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashOrder;
import com.lovelyz.washcar.entity.WashUser;
import com.lovelyz.washcar.service.WashOrderService;
import com.lovelyz.washcar.service.WashUserService;
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
@RequestMapping("/washUser/")
public class WashUserRestful {

    @Autowired
    WashUserService washUserService;
    @Autowired
    WashOrderService washOrderService;

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashUser> selectByPrimaryKey(String userId) {
        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washUserService.selectByPrimaryKey(userId));
        return returnData;
    }

    /**
     * 精准查询
     * 根据实体查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashUser> selectByCondition(@RequestBody WashUser record) {

        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
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
            count = washUserService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washUserService.selectByCondition(record));
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }

    /**
     * 精准查询、接单统计查询
     * 根据实体查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionWashReport")
    @ResponseBody
    public LayuiDataTemplet<WashUser> selectByConditionWashReport(@RequestBody WashUser record) {

        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
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
            count = washUserService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                //根据条件查询
                List<WashUser> washUserList = washUserService.selectByCondition(record);
                if (washUserList.size() > 0){
                    for (int i =0; i< washUserList.size();i++) {
                        //查询接单数量
                        WashOrder washOrder = new WashOrder();
                        //已结束的订单
                        washOrder.setOrderStatic("YWC");
                        washOrder.setOrderServiceid(washUserList.get(i).getUserId());
                        washOrder.setStarttime(record.getStartTime());
                        washOrder.setEndtime(record.getEndTime());
                        Integer washCount = washOrderService.count(washOrder);
                        washUserList.get(i).setUserWashCount(washCount);
                        //查询实收总金额
                        double userWashTotalMoney = 0;
                        washOrder.setPagenumber(-1);//不分页
                        List<WashOrder> washOrderList = washOrderService.selectByCondition(washOrder);
                        for (WashOrder washOrder1 : washOrderList) {
                            if (null != washOrder1.getOrderPaymoney()){
                                userWashTotalMoney = userWashTotalMoney + washOrder1.getOrderPaymoney();
                            }
                        }
                        washUserList.get(i).setUserWashTotalMoney(userWashTotalMoney);
                    }
                }
                returnData.setData(washUserList);
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
     * 根据实体查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionData")
    @ResponseBody
    public LayuiDataTemplet<WashUser> selectByConditionData(@RequestBody WashUser record) {

        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
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
            count = washUserService.countData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                returnData.setData(washUserService.selectByConditionData(record));
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
    public LayuiDataTemplet<WashUser> insertSelective(@RequestBody WashUser record) {
        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashUser SelectData = new WashUser();
        WashUser washUser = new WashUser();
        SelectData.setUserOpenid(record.getUserOpenid()); //用户openid
        int sum = 0;
        // 添加
        int count = 0;
        sum = washUserService.count(SelectData); // 查询数量
        if (sum > 0) {
            SelectData.setPagenumber(-1);
          List<WashUser> users =   washUserService.selectByCondition(SelectData);
        returnData.setDataone( users.get(0));
            returnData.setCode(220);
            returnData.setMsg("该用户已添加，请登录！");
            return returnData;
        } else {
            record.setUserId(UUID.randomUUID().toString());
            record.setUserRole("KH"); //添加默认为用户
            record.setUserCreatetime(new Date());//注册时间
            record.setUserState("PZ");
            count = washUserService.insertSelective(record);
            washUser = washUserService.selectByPrimaryKey(record.getUserId());
        }
        // 返回数据
        if (count == 0) {
            returnData.setCode(300);
            returnData.setMsg("添加失败！");
        } else {
            returnData.setCode(200);
            returnData.setCount(count);
            returnData.setDataone(washUser);
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
    public LayuiDataTemplet<WashUser> updateByPrimaryKeySelective(@RequestBody WashUser record) {

        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getUserId() == null || record.getUserId().isEmpty()) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //检验是否有该条记录
            WashUser SelectData = new WashUser();
            SelectData.setUserId(record.getUserId());//用户ID
            int sum = 0;
            sum = washUserService.count(SelectData); // 查询数量
            if (sum < 0) {
                returnData.setCode(300);
                returnData.setMsg("未找到该评价记录单！");
                return returnData;
            } else {
                record.setUserUpdatetime(new Date()); //更新时间
                record.setUserUpdateuserid(record.getUserId()); //更新人
                count = washUserService.updateByPrimaryKeySelective(record);
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
     * 根据实体分页查询
     *
     * @param washUser
     * @return
     */
    @RequestMapping("selectByPage")
    @ResponseBody
    public LayuiDataTemplet<WashUser> selectByPage(@RequestBody WashUser washUser) {
        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        if (washUser.getPagenumber() != null) {
            // 计算偏移量
            if (washUser.getPagenumber() != -1) {
                if (washUser.getPagesize() == null) {
                    returnData.setMsg("传递的分页数据(每页数量)错误！");
                    return returnData;
                }
                // 获取传递过来的数据
                int pageNumber = washUser.getPagenumber();
                int pageSize = washUser.getPagesize();
                washUser.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
                washUser.setPagesize(pageSize); // 每页的数据量
            }

            returnData.setCount(washUserService.count(washUser));

        }
        return returnData;

    }

    /**
     * 根据实体删除
     * 假删除
     *
     * @param record
     * @return
     */
    @RequestMapping("updatebydel")
    @ResponseBody
    public LayuiDataTemplet<WashUser> updatebydel(@RequestBody WashUser record) {
        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getUserId() == null || record.getUserId().isEmpty()) {
            returnData.setMsg("未找到该用户，删除失败！");
            return returnData;
        } else {
            count = washUserService.updatebydel(record);
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
    public LayuiDataTemplet<WashUser> deleteByCondition(@RequestBody WashUser record) {

        LayuiDataTemplet<WashUser> returnData = new LayuiDataTemplet<WashUser>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getUserId() == null || record.getUserId().isEmpty()) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washUserService.deleteByCondition(record);
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
