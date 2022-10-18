package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.UserMapper;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            UserMapper userMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;

    @PostMapping("/login")  //登录
    public Result<?> login(@RequestBody User user){//把前台json转换为java对象  //前台和数据库进行比对
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserjobid,user.getUserjobid()).eq(User::getPassword,user.getPassword()).eq(User::getRole,user.getRole()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }

        return Result.success(res);
    }
    /////////////////////////////////////////////////////////
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
    ///////////////////////////////////////////////////////////

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
            wrapper.like(User::getUsername, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
