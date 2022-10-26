package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.Mark;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.MarkMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            ProjectMapper projectMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    MarkMapper markMapper;

    @PostMapping //新增
    public Result<?> save(@RequestBody Project project) {//把前台json转换为java对象
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid, project.getProjectgroupId()));

        if (res == null) {
            return Result.error("-1", "该用户不存在!!!");
        }
        Mark reb = markMapper.selectOne(Wrappers.<Mark>lambdaQuery().eq(Mark::getProjectType,project.getProjectType()).eq(Mark::getProjectLevel,project.getProjectLevel()));
        if(reb == null){
            return Result.error("-3","未选择项目类别/项目级别");
        }
//        System.out.println(reb);
        project.setProjectMark(reb.getProjectMark());
        projectMapper.insert(project);
        return Result.success();
    }

    @PutMapping  //编辑放数据回数据库
    public Result<?> update(@RequestBody Project project) {//把前台json转换为java对象
        //在User表中查找有没有该组长
//        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid, project.getProjectgroupId()));
//
//        if (res == null) {
//            return Result.error("-1", "该用户不存在!!!");
//        }
        float res = project.getFirst() + project.getSecond() + project.getThree();
        if(res >100 || res < 0){
            return  Result.error("-1","分配比例总和不能超过100%");
        }
        projectMapper.updateById(project);
        return Result.success();
    }

    @GetMapping("/find")
    public int find(@RequestParam Integer userid) {//把前台json转换为java对象
        //在Project表中查找本项目ID
        Project res = projectMapper.selectOne(Wrappers.<Project>lambdaQuery().eq(Project::getProjectgroupId, userid));
        if (res == null) {
            return -1;
        }
        return res.getProjectId();
        //       System.out.println(res.getProjectid());

    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id) {//把前台json转换为java对象
        projectMapper.deleteById(id);
        return Result.success();
    }


    @GetMapping //查找接口
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {

            wrapper.like(Project::getProjectgroupId, search).or().like(Project::getProjectId, search);

        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }

}