package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
//import sun.security.acl.GroupImpl;

import javax.annotation.Resource;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            UserMapper userMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            ProjectMapper projectMapper;

    @PostMapping  //新增
    public Result<?> save(@RequestBody Group group){//把前台json转换为java对象
//
        Project res1 = projectMapper.selectOne(Wrappers.<Project>lambdaQuery().eq(Project::getProjectId,group.getProjectid()));
        if (res1 == null) {
            return Result.error("-1", "该项目不存在!!!");
        }
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid,group.getUserid()));
        if (res == null) {
            return Result.error("-1", "该用户不存在!!!");
        }
        Group res2 = groupMapper.selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserid, group.getUserid()).eq(Group::getProjectid,group.getProjectid()));
        System.out.println(res2);
        if (res2 != null) {
            return Result.error("-1", "该成员已存在于项目中!!!");
        }

        groupMapper.insert(group);
        return Result.success();

////        group.setOfficeDirector(0);
//        group.setScientific(0);

    }

    @PutMapping//新增,@RequestBody User user //这是编辑调用的
    public Result<?> update(@RequestBody Group group){//把前台json转换为java对象
//        Group res = groupMapper.selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getProjectid,group.getProjectid()).eq(Group::getUserid,group.getUserid()));
//        if (res == null) {
//            return Result.error("-1", "不存在该工号以及项目号！！！");
//        }
        //对字段进行了修改&& user.getRole() == 5
        //    System.out.println(param.getProject().getProjectId());
//        Project res1 = projectMapper.selectOne(Wrappers.<Project>lambdaQuery().eq(Project::getProjectId,group.getProjectid()));
//        if (res1 == null) {
//            return Result.error("-1", "该项目不存在!!!");
//        }
//        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid,group.getUserid()));
//        if (res == null) {
//            return Result.error("-1", "该用户不存在!!!");
//        }
////        if(group.getOfficeDirector() == 1){  //1是属于正常
////            group.setOfficeDirector(0);
////        }
//        if(group.getScientific() == 1){  //1是属于正常，
//            group.setScientific(0);
//        };


//        float res = group.getFirst() + group.getSecond() + group.getThree();
//        if(res >100 || res < 0){
//            return  Result.error("-1","分配比例总和不能超过100%");
//        }
        groupMapper.updateById(group);
        return Result.success();

    }

    @PutMapping("/confirm")//新增,@RequestBody User user //这是组长调用的
    public Result<?> confirm(@RequestBody Group group){//把前台json转换为java对象
//        if(group.getOfficeDirector() == 0){  //1是属于正常
//            group.setOfficeDirector(1);
//        }else{
//            group.setOfficeDirector(0);
//        }
        //   System.out.println(group.getScientific());
        groupMapper.updateById(group);//写进数据库中
        return Result.success();
    }
    @PutMapping("/science" )
    //新增,@RequestBody User user //这是组长调用的
    public Result<?> science(@RequestBody Group group){//把前台json转换为java对象
        if(group.getScientific() == 0){  //1是属于正常
            group.setScientific(1);
        }else{
            group.setScientific(0);
        }
        //   System.out.println(group.getScientific());
        groupMapper.updateById(group);//写进数据库中
        return Result.success();
    }

    @GetMapping("/find")
    public int find(@RequestParam Integer userid){//把前台json转换为java对象
        //在group表中查找本项目ID
        Group res = groupMapper.selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserid,userid));
        if(res == null){
            return  -1;
        }
        return  res.getProjectid();
        //       System.out.println(res.getProjectid());

    }


    @GetMapping("/finddata")
    //2082 103错误 因为数据库在group弄了多个字段出错
    public Result<?> finddata(@RequestParam Integer userid,@RequestParam Integer projectId ){//把前台json转换为java对象
        //在group表中查找本项目ID
        Group res = groupMapper.selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getUserid,userid).eq(Group::getProjectid,projectId));
        System.out.println(res);
        if(res == null){
            return Result.error("-1", "你不是该项目的成员!!!");
        }
        return  Result.success(res);
        //       System.out.println(res.getProjectid());

    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){//把前台json转换为java对象
        groupMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping //分页查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //Page<Group> groupPage = groupMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Group>lambdaQuery().like(Group::getNickName,search));
//        如果项目ID和userid都在group表中就打印！！！
        LambdaQueryWrapper<Group> wrapper = Wrappers.<Group>lambdaQuery();

        if (StrUtil.isNotBlank(search)) {
            wrapper.eq(Group::getUserid, search).or().eq(Group::getProjectid,search);
        }

        Page<Group> groupPage = groupMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(groupPage);
    }
    @GetMapping("master") //带组长的分页查询
    public Result<?> finditem(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //Page<Group> groupPage = groupMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Group>lambdaQuery().like(Group::getNickName,search));
//        如果项目ID和userid都在group表中就打印！！！
        LambdaQueryWrapper<Group> wrapper = Wrappers.<Group>lambdaQuery();

        if (StrUtil.isNotBlank(search)) {
            wrapper.eq(Group::getUserid, search).or().eq(Group::getProjectid,search);
        }

        Page<Group> groupPage = groupMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        //search
        //找组长
        Object[] arrayObjects = new Object[2];
        int x=projectMapper.finduserid(search);
//        System.out.println(x);
//        传值可以直接用object类型
        arrayObjects [0] = groupPage;
        arrayObjects [1] = x;


        return Result.success(arrayObjects);
    }
}
