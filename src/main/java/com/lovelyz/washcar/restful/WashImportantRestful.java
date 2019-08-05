package com.lovelyz.washcar.restful;

import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashImportant;
import com.lovelyz.washcar.entity.WashImportanttype;
import com.lovelyz.washcar.entity.WashVip;
import com.lovelyz.washcar.service.WashImportantService;
import com.lovelyz.washcar.service.WashImportanttypeService;
import com.lovelyz.washcar.service.WashVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * 会员充值记录
 * Created by Lovelyz on 2019/06/24.
 */
@Controller
@RequestMapping("/washImportant/")
public class WashImportantRestful {

    @Autowired
    WashImportantService washImportantService;

    @Autowired
    WashVipService washVipService;

    @Autowired
    WashImportanttypeService washImportanttypeService;

    /**
     * @param rankId
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashImportant> selectByPrimaryKey(Integer rankId) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setDataone(washImportantService.selectByPrimaryKey(rankId));
        return returnData;
    }

    /**
     * 条件查询  会员
     * 准确查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashImportant> selectByCondition(@RequestBody WashImportant record) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
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
            count = washImportantService.count(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<WashImportant> washImportants = new ArrayList<WashImportant>();
                washImportants = washImportantService.selectByCondition(record);
                returnData.setData(washImportants);
            }
        } else {
            returnData.setCode(460);
            returnData.setCount(0);
            returnData.setMsg("分页传递错误！");
        }
        return returnData;
    }


    /**
     * 条件查询 会员集合
     * 模糊查询
     *
     * @param record
     * @return
     */
    @RequestMapping("selectByConditionData")
    @ResponseBody
    public LayuiDataTemplet<WashImportant> selectByConditionData(@RequestBody WashImportant record) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
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
            record.setImportantIsdel(1);
            count = washImportantService.countData(record); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setCode(260);
                returnData.setCount(count);
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<WashImportant> washImportants = new ArrayList<>();
                washImportants = washImportantService.selectByConditionData(record);
                for (int a = 0; a < washImportants.size(); a++) {

                }

                returnData.setData(washImportants);
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
    public LayuiDataTemplet<WashImportant> insertSelective(@RequestBody WashImportant record) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashImportant SelectData = new WashImportant();
        SelectData.setImportantUserid(record.getImportantUserid());//用户id
        int sum = 0;
        // 添加
        int count = 0;
        sum = washImportantService.count(SelectData); // 查询数量
        if (sum > 0) {
            WashImportant reData = new WashImportant();
            reData.setImportantUserid(record.getImportantUserid());
            reData.setPagenumber(-1);
            List<WashImportant> washImportantlist = washImportantService.selectByCondition(reData);
            reData = washImportantlist.get(0);
            returnData.setDataone(reData);
            returnData.setMsg("您已是会员了，请在我的套餐中查看详情！");
            returnData.setCode(300);
            return returnData;
        } else {
//            查询vip 套餐
            WashVip washVip = new WashVip();
            washVip = washVipService.selectByPrimaryKey(record.getImportantTypeid());
//            添加会员
            WashImportant washImportant = new WashImportant();
            washImportant.setImportantUserid(record.getImportantUserid());  //会员套餐使用人
            washImportant.setImportantCreatetime(new Date());
            washImportant.setImportantCreatebyuserid(record.getImportantUserid());//会员创建人ID
            washImportant.setImportantTypeid("TC" + UUID.randomUUID().toString());//  会员套餐记录ID
            washImportant.setImportantCreatebyuserid(record.getImportantUserid());  //会员套餐使用人
            washImportant.setImportantCreatetime(new Date());//创建时间
            washImportant.setImportantMark(String.valueOf(washVip.getVipMoney()));
            WashImportanttype washImportanttype = new WashImportanttype();  //会员套餐记录表
            washImportanttype.setImportanttypeId(washImportant.getImportantTypeid()); //会员套餐ID
            washImportanttype.setImportanttypeName(washVip.getVipName());//会员套餐名称
            washImportanttype.setImportanttypeType(washVip.getVipType()); //会员套餐类型
            washImportanttype.setImportanttypeUserid(record.getImportantUserid());//会员套餐创建人ID
            washImportanttype.setImportanttypePtnum(washVip.getVipPtnum());//普通洗车次数
            washImportanttype.setImportanttypeFdnum(washVip.getVipFdnum());//洗发动机次数
            washImportanttype.setImportanttypeKtnum(washVip.getVipKtnum());//洗空调车次数
            washImportanttype.setImportanttypeNsnum(washVip.getVipNsnum());//内饰次数
            washImportanttype.setImportanttypeCreatetime(new Date());//创建时间
            washImportanttype.setImportanttypeCreatebyuserid(record.getImportantUserid());//会员套餐创建人ID
            if (washVip.getVipType() == "C") {
                Date createtime = washImportanttype.getImportanttypeCreatetime();
                Calendar cal = Calendar.getInstance();
                cal.setTime(createtime);//设置起时间
                cal.add(Calendar.YEAR, 1);//增加一年  //套餐C 一年内免费洗车
                //至尊无限免费洗车有效期一年
                washImportanttype.setImportanttypeValidtime(cal.getTime());
            }
            sum = washImportanttypeService.insertSelective(washImportanttype);  //添加一条会员套餐
            count = washImportantService.insertSelective(washImportant);
            // 返回数据
            if (count + sum != 2) {
                returnData.setCode(300);
                returnData.setMsg("充值会员失败！");
            } else {
                WashImportant reData = new WashImportant();
                reData.setImportantUserid(washImportant.getImportantUserid());
                reData.setPagenumber(-1);
                List<WashImportant> washImportantlist = washImportantService.selectByCondition(reData);
                returnData.setDataone(washImportantlist.get(0));
                returnData.setCode(200);
                returnData.setCount(count);
                returnData.setMsg("充值会员成功！");
            }
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
    public LayuiDataTemplet<WashImportant> updateByPrimaryKeySelective(@RequestBody WashImportant record) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getImportantId() == null) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            //添加创建时间
            WashImportanttype washImportanttype = new WashImportanttype();
            washImportanttype.setImportanttypeId(record.getImportantTypeid());
            washImportanttype.setImportanttypePtusenum(record.getImportanttypePtusenum());//普通洗车使用次数
            washImportanttype.setImportanttypeFdusenum(record.getImportanttypeFdusenum());//洗发动机使用次数
            washImportanttype.setImportanttypeKtusenum(record.getImportanttypeKtusenum());//洗空调使用次数
            washImportanttype.setImportanttypeNsusenum(record.getImportanttypeNsusenum());//内饰使用次数
            washImportanttype.setImportanttypeUpdatetime(new Date());
            count = washImportanttypeService.updateByPrimaryKeySelective(washImportanttype);
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
    public LayuiDataTemplet<WashImportant> updatebydel(@RequestBody WashImportant record) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getImportantId() == null) {
            returnData.setMsg("缺少参数，删除失败！");
            return returnData;
        } else {
            count = washImportantService.updatebydel(record);
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
    public LayuiDataTemplet<WashImportant> deleteByCondition(@RequestBody WashImportant record) {
        LayuiDataTemplet<WashImportant> returnData = new LayuiDataTemplet<WashImportant>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getImportantId() == null) {
            returnData.setMsg("缺少删除条件，删除失败！");
            return returnData;
        } else {
            count = washImportantService.deleteByCondition(record);
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