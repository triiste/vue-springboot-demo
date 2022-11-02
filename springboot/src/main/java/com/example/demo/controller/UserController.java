package com.example.demo.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            UserMapper userMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/login")  //登录
    public Result<?> login(@RequestBody User user){//把前台json转换为java对象  //前台和数据库进行比对
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserjobid,user.getUserjobid()).eq(User::getPassword,user.getPassword()).eq(User::getRole,user.getRole()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }

        return Result.success(res);
    }

    @GetMapping("/edit")  //登录
    public Result<?> find(@RequestParam String userjobid,@RequestParam String password0,@RequestParam String password1) {//把前台json转换为java对象
        //在Project表中查找本项目ID
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserjobid,userjobid).eq(User::getPassword,password0));
        if (res == null) {
            return Result.error("-1", "原密码错误！！！");
        }
        else{
            res.setPassword(password1);

            System.out.println(res.getPassword());
            userMapper.updateById(res);
            return Result.success();
        }
    }
    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //List<User> list = reader.readAll(User.class);
        //System.out.println(list);
               //     // 方式2：忽略表头的中文，直接读取表的内容
                    List<List<Object>> list = reader.read(1);
                    List<User> users = CollUtil.newArrayList();
                    for (List<Object> row : list) {
                        User user = new User();
                        user.setOfficeid(Integer.parseInt(row.get(0).toString()));
              //         user.setUserid(Integer.parseInt(row.get(9).toString()));
                        user.setUserjobid(row.get(1).toString());
                        user.setUsername(row.get(2).toString());
                        user.setTelephone(row.get(3).toString());
                        user.setAge(Integer.parseInt(row.get(4).toString()));
                        user.setSex(row.get(5).toString());
                        user.setAddress(row.get(6).toString());
                        user.setPosition(row.get(7).toString());
                        user.setPermission(row.get(8).toString());
                        user.setMark(Integer.parseInt(row.get(9).toString()));
                        users.add(user);
                    }
                     userService.saveBatch(users);
        return true;
    }
    //导出可以
    @GetMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Integer userid) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userMapper.findAll(userid);
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        自定义标题别名
        writer.setOnlyAlias(true);
        writer.addHeaderAlias("officeid", "所属科室");
        writer.addHeaderAlias("userid", "用户id");
        writer.addHeaderAlias("userjobid", "用户工号");
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("telephone", "手机号");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("position", "职位");
        writer.addHeaderAlias("permission", "部门");
        writer.addHeaderAlias("mark", "个人积分");
        writer.write(list, true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]

        return userService.removeByIds(ids);

    }
    @PostMapping //新增，赋初始密码
    public Result<?> save(@RequestBody User user){//把前台json转换为java对象
        if(user.getPassword() == null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping  //更新
    public Result<?> update(@RequestBody User user){//把前台json转换为java对象
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}") //通过id查询的接口
    public Result<?> delete(@PathVariable long id) {//把前台json转换为java对象

        userMapper.deleteById(id);
        return Result.success();

    }



    @GetMapping("/{id}") //通过id查询的接口
    public Result<?> getById(@PathVariable long id){//把前台json转换为java对象
       // System.out.println(id);
        return Result.success(userMapper.selectById(id));
    }

    @PostMapping("/register") //注册接口
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserjobid, user.getUserjobid()));
        if(res !=null){
            return Result.error("-1","用户名重复");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success(); //返回代码code为0
    }
    @GetMapping //按用户名查找
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //Page<User> userPage = userMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<User>lambdaQuery().like(User::getNickName,search));
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getOfficeid, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
