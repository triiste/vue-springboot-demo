package com.example.demo.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Office;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.OfficeMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/office")
public class OfficeController {

    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            OfficeMapper officeMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            UserMapper userMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;

    @GetMapping("/test")
    public Map<String, Object> findoffice(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "") String search
                                          ) {
         pageNum = (pageNum -1) *pageSize;
        search = "%" + search + "%";
        List<Office> all = officeMapper.findAll(pageNum,pageSize,search);
        Integer total = officeMapper.selectTotal(search);
        Map<String, Object> res = new HashMap<>();
        res.put("data", all);
        res.put("total", total);
        return res;
    }

    /**
     * 室主任奖励附件
     * @param officeid
     * @param filename
     * @param url
     * @return
     */
    @GetMapping("/files")//按项目名称查询
    public Result<?> putfiles(@RequestParam Integer officeid,
                              @RequestParam String filename,
                              @RequestParam String url){
//        System.out.println(projectid);
//        System.out.println(filename);
//        System.out.println(url);
        //先查出来之前存的，删掉之后再赋值
        //先查地址就可以了
//        两个函数另写
        String beforeurl =officeMapper.findofficeurl(officeid);
        officeMapper.updatefiles(officeid,filename,url);
//        System.out.println(beforeurl);
        //查找beforeurl
        //查找
//        System.out.println(beforeurl.length());
//        System.out.println(beforeurl);

//        if(beforeurl != null)
        if(beforeurl != null){
            String prefix="http://localhost:9090/files/";
            String flag=beforeurl.substring(prefix.length());
            String basePath= System.getProperty("user.dir") + "/springboot/src/main/resources/files/";//定义文件上传的根路径
            List<String> fileNames = FileUtil.listFileNames(basePath); //获取所有文件的名称
            String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到跟参数一致的文件
//            System.out.println("文件名称"+fileName);
            //fileName =9b36c446fc724555b11e54ade2f9d8f2_mark.sql
            File file = new File(basePath+fileName);
            if (file.exists()) {
                file.delete();
            }
        }

        return Result.success();
    }

    /**
     * 所领导奖励附件
     * @param officeid
     * @param filename
     * @param url
     * @return
     */
    @GetMapping("/filesplace")//按项目名称查询
    public Result<?> putfilesplace(@RequestParam Integer officeid,
                              @RequestParam String filename,
                              @RequestParam String url){
//        System.out.println(projectid);
//        System.out.println(filename);
//        System.out.println(url);
        //先查出来之前存的，删掉之后再赋值
        //先查地址就可以了
//        两个函数另写
        String beforeurl =officeMapper.findofficeurl1(officeid);
        officeMapper.updatefiles1(officeid,filename,url);
//        System.out.println(beforeurl);
        //查找beforeurl
        //查找
//        System.out.println(beforeurl.length());
//        System.out.println(beforeurl);

//        if(beforeurl != null)
        if(beforeurl != null){
            String prefix="http://localhost:9090/files/";
            String flag=beforeurl.substring(prefix.length());
            String basePath= System.getProperty("user.dir") + "/springboot/src/main/resources/files/";//定义文件上传的根路径
            List<String> fileNames = FileUtil.listFileNames(basePath); //获取所有文件的名称
            String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到跟参数一致的文件
//            System.out.println("文件名称"+fileName);
            //fileName =9b36c446fc724555b11e54ade2f9d8f2_mark.sql
            File file = new File(basePath+fileName);
            if (file.exists()) {
                file.delete();
            }
        }

        return Result.success();
    }



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


    @PostMapping //新增，赋初始密码
    public Result<?> save(@RequestBody Office office){//把前台json转换为java对象
        System.out.println(office.getUsername());
        officeMapper.insert(office);
        return Result.success();
    }

    @PutMapping  //更新
    public Result<?> update(@RequestBody Office office){//把前台json转换为java对象
        officeMapper.updateById(office);
        return Result.success();
    }

    @DeleteMapping("/{id}") //通过id查询的接口
    public boolean delete(@PathVariable long id) {//把前台json转换为java对象

        //先查此id内部有没有人
        Integer total = userMapper.officeTotal(id);
        if(total>0){
           return  false;
        }
        else{
            officeMapper.deleteById(id);
            return true;
        }
    }

    //查出整个室
    @GetMapping("/{id}") //通过id查询的接口
    public Result<?> getById(@PathVariable long id){//把前台json转换为java对象
        return Result.success(officeMapper.selectById(id));
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
    @GetMapping //按室主任id和室内id查找
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //Page<User> userPage = userMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<User>lambdaQuery().like(User::getNickName,search));
        LambdaQueryWrapper<Office> wrapper = Wrappers.<Office>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Office::getId, search).or().like(Office::getOfficeuserid,search);
        }
        Page<Office> userPage = officeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
