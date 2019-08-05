//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lovelyz.washcar.restful;

import com.alibaba.fastjson.JSONObject;
import com.lovelyz.washcar.entity.LayuiDataTemplet;
import com.lovelyz.washcar.entity.WashCoupon;
import com.lovelyz.washcar.service.WashCouponService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/shangchuan/"})
@MultipartConfig
public class UploadRestful {

    @RequestMapping({"test"})
    @ResponseBody
    public String selectByPrimaryKey(String couponId) {
        LayuiDataTemplet<WashCoupon> returnData = new LayuiDataTemplet();
        returnData.setCode(0);
        returnData.setCount(0);
        returnData.setData((List)null);
        return "aaassssddd";
    }

    /**
     * 用户上传图片
     * @param file
     * @param request
     * @param stream
     * @return
     * @throws IOException
     * @author ZY on 2018/09/22
     */
    @RequestMapping("/image")
    @ResponseBody
    public JSONObject uploadHeadImage(@RequestParam("file") MultipartFile file,
                                      HttpServletRequest request, InputStream stream) throws IOException {
        Assert.notNull(file, "上传文件不能为空");
        String src = "upload";
        // 获取真实路径
        String path = request.getServletContext().getRealPath("/" + src);
        JSONObject json = new JSONObject();
        // System.currentTimeMillis() 根据系统时间产生随机数，保证上传的图片名字不一样
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name =  System.currentTimeMillis() + suffix;
        File dir = new File(path, name);
        src =  src + "/" + name;
        if (!dir.exists()) {
            dir.mkdirs();
            json.put("msg", "上传成功");
            json.put("code", 0);
            json.put("srcImg", src);
            json.put("name", name);
            json.put("path", path);
        } else {
            json.put("msg", "上传失败");
            json.put("code", 1);
        }
        file.transferTo(dir);

        return json;
    }


    /**
     * 用户上传视频
     *
     * @param file
     * @param request
     * @param stream
     * @return
     * @throws IOException
     * @author ZY on 2018/09/22
     */
    @RequestMapping("/uploadVideo")
    @ResponseBody
    public JSONObject uploadHeadVideo(@RequestParam("file") MultipartFile file,
                                      HttpServletRequest request, InputStream stream) throws IOException {
        Assert.notNull(file, "上传文件不能为空");
        String src = "upload";
        // 获取真实路径
        String path = request.getServletContext().getRealPath("/" + src);
        String currentProjectName = request.getContextPath(); // 获取当前项目名称
        JSONObject json = new JSONObject();
        // System.currentTimeMillis() 根据系统时间产生随机数，保证上传的图片名字不一样
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name = UUID.randomUUID().toString() + System.currentTimeMillis() + suffix;
        File dir = new File(path, name);
        src = currentProjectName +"/" + src + "/" + name;
        if (!dir.exists()) {
            dir.mkdirs();
            json.put("msg", "上传成功");
            json.put("code", 200);
            json.put("srcVideo", src);
        } else {
            json.put("msg", "上传失败");
            json.put("code", 1);
        }
        file.transferTo(dir);

        return json;
    }

}
