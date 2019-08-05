package com.lovelyz.washcar.restful;

import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashStaff;
import com.lovelyz.washcar.service.WashStaffService;
import com.lovelyz.washcar.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Lovelyz on 2019/05/22.
 */
@Controller
@RequestMapping("/washStaff/")
public class WashStaffRestful {

    @Autowired
    WashStaffService washStaffService;


    /**
     * 登录
     *
     * @param washStaff
     * @return
     * @author ZY on 2019/03/12
     */
    @ResponseBody
    @RequestMapping("login")
    public LayuiDataTemplet<WashStaff> login(@RequestBody WashStaff washStaff) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        List<WashStaff> userList = null;
        // 查询用户数据，并判断用户名、密码是否正确
        if (washStaff.getTel() != null && !washStaff.getTel().isEmpty() &&
                washStaff.getUserpass() != null && !washStaff.getUserpass().isEmpty()) {
            // 根据手机号码查询数据
            WashStaff selectData = new WashStaff();
            selectData.setTel(washStaff.getTel()); // 用户登录名
            selectData.setUserpass(AESUtil. GetAESCode(washStaff.getUserpass(),"2580"));//加密后的密码
            // 查询数据
            userList = washStaffService.selectByCondition(selectData);

            // 判断用户名是否正确
            if (userList == null || userList.size() == 0) {
                // 没有该用户
                returnData.setMsg("没有该用户！");
            } else {
                // 判断密码是否正确
                if (!AESUtil.GetAESCode(washStaff.getUserpass(),"2580").equals(userList.get(0).getUserpass())) {
                    // 没有该用户
                    returnData.setMsg("密码错误！");
                } else {
                    returnData.setCode(200);
                    returnData.setCount(userList.size());
                    returnData.setData(userList);
                }
            }
        }
        return returnData;
    }


    @RequestMapping("selectAll")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> selectAll() {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setData(washStaffService.selectAll());
        return returnData;
    }

    @RequestMapping("select")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> selectByPrimaryKey(Integer id) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        returnData.setDataone(washStaffService.selectByPrimaryKey(id));
        return returnData;
    }

    @RequestMapping("selectByCondition")
    @ResponseBody
        public LayuiDataTemplet<WashStaff> selectByCondition(@RequestBody WashStaff record) {
            LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
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
                count = washStaffService.count(record); // 查询数量
                // 返回数据
                if (count == 0) {
                    returnData.setCode(260);
                    returnData.setCount(count);
                    returnData.setMsg("暂无数据！");
                } else {
                    returnData.setCode(200);
                    returnData.setCount(count);
                    returnData.setMsg("查询成功！");
                    List<WashStaff> washStaffs = washStaffService.selectByCondition(record);
                    for (int a = 0;a<washStaffs.size();a++){
                        String pass =  AESUtil.RevertAESCode(washStaffs.get(a).getUserpass(),"2580");
                        washStaffs.get(a).setUserpass(pass);
                    }
                    returnData.setData(washStaffs);
                }
            } else {
                returnData.setCode(460);
                returnData.setCount(0);
                returnData.setMsg("分页传递错误！");
            }
            return returnData;
        }

    @RequestMapping("count")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> count(@RequestBody WashStaff record) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setCount(washStaffService.count(record));
        return returnData;
    }


    /**
     * 添加管理员
     * @param record
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> insertSelective(@RequestBody WashStaff record) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        //检验重复
        WashStaff SelectData = new WashStaff();
        SelectData.setTel(record.getTel());//手机号
        int sum = 0;
        // 添加
        int count = 0;
        sum = washStaffService.count(SelectData); // 查询数量
        if (sum > 0) {
            returnData.setCode(300);
            returnData.setMsg("该手机号已添加、请更换！");
            return returnData;
        } else {
            record.setUserpass(AESUtil.GetAESCode(record.getUserpass(),"2580"));
            count = washStaffService.insertSelective(record);
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
     * 管理员更新密码
     *
     * @param record
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> update(@RequestBody WashStaff record) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 更新
        int count = 0;
        // 更新判断
        if (record.getId() == null) {
            returnData.setMsg("缺少更新条件，更新失败！");
            return returnData;
        } else {
            String pass = AESUtil.GetAESCode(record.getUserpass(),"2580");
           record.setUserpass(pass);
            count = washStaffService.updateByPrimaryKeySelective(record);
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
     * 本人修改密码
     * @param record
     * @return
     */
    @RequestMapping("updatepassword")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> updateByPrimaryKeySelective(@RequestBody WashStaff record) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        List<WashStaff> userList = null;
        WashStaff washStaff = new WashStaff();
        // 查询用户数据，并判断用户名、密码是否正确
        if (record.getTel() != null && !record.getTel().isEmpty() &&
                record.getOldpassword() != null && !record.getOldpassword().isEmpty()) {
            // 根据手机号码查询数据
            WashStaff selectData = new WashStaff();
            selectData.setTel(record.getTel()); // 用户登录名
            selectData.setUserpass(AESUtil. GetAESCode(record.getOldpassword(),"2580"));//加密后的密码
            // 查询数据
            userList = washStaffService.selectByCondition(selectData);

            // 判断用户名是否正确
            if (userList == null || userList.size() == 0) {
                // 没有该用户
                returnData.setCode(300);
                returnData.setMsg("原密码输入错误,请输入正确的密码！");
            } else {
                washStaff = userList.get(0);
                washStaff.setUserpass( AESUtil.GetAESCode(record.getUserpass(),"2580"));
                washStaffService.updateByPrimaryKeySelective(washStaff);
                returnData.setCode(200);
                returnData.setMsg("更新成功！");
            }
        }
        return returnData;
    }



    @RequestMapping("deleteByCondition")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> deleteByCondition(@RequestBody WashStaff record) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setCount(washStaffService.deleteByCondition(record));

        return returnData;
    }

    @RequestMapping("delete")
    @ResponseBody
    public LayuiDataTemplet<WashStaff> deleteByPrimaryKey(Integer id) {
        LayuiDataTemplet<WashStaff> returnData = new LayuiDataTemplet<WashStaff>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        returnData.setCount(washStaffService.deleteByPrimaryKey(id));

        return returnData;
    }

}
