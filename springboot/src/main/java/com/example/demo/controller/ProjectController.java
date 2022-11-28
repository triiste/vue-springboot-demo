package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.mapper.MarkMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            ProjectMapper projectMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    MarkMapper markMapper;
    @Resource
    GroupMapper groupMapper;
    @GetMapping("/test")
    public Map<String, Object> index(@RequestParam Integer userid,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize
                               ) {
        pageNum = (pageNum -1) *pageSize;
        List<Project> all = projectMapper.findAll(userid,pageNum,pageSize);
        Integer total = projectMapper.selectTotal(userid);
        Map<String, Object> res = new HashMap<>();
        res.put("data", all);
        res.put("total", total);
        return res;
    }
    @PostMapping //新增
    public Result<?> save(@RequestBody Project project) {//把前台json转换为java对象
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid, project.getProjectgroupId()));

        if (res == null) {
            return Result.error("-1", "该用户不存在!!!");
        }
//        Mark reb = markMapper.selectOne(Wrappers.<Mark>lambdaQuery().eq(Mark::getProjectType,project.getProjectType()).eq(Mark::getProjectLevel,project.getProjectLevel()));
//        if(reb == null){
//            return Result.error("-3","未选择项目类别/项目级别");
//        }
//        System.out.println(reb);
//        project.setProjectMark(reb.getProjectMark());
//        if(project.getProjectType() == null)   return Result.error("-3","未选择项目类别/项目级别");
//          System.out.println("看看传来的东西"+project.getProjectType());
         String projectkind= projectMapper.findkind(project.getProjectType());
         Integer projectmark=projectMapper.findmark(project.getProjectType());
//              System.out.println("看看查询的东西"+projectkind);
//               System.out.println("看看查询的东西"+projectmark);
                project.setProjectkind(project.getProjectType());
               project.setProjectMark(projectmark);
               project.setProjectType(projectkind);
        Group group =new Group();
        group.setProjectid(project.getProjectId());
        group.setUserid(project.getProjectgroupId());
//        System.out.println(project.getFirstMark());
        projectMapper.insert(project);
        groupMapper.insert(group);
        return Result.success();
    }

    @PutMapping  //编辑放数据回数据库
    public Result<?> update(@RequestBody Project project) {//把前台json转换为java对象
        //在User表中查找有没有该组长
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid, project.getProjectgroupId()));

        if (res == null) {
            return Result.error("-1", "该用户不存在!!!");
        }
//        Mark reb = markMapper.selectOne(Wrappers.<Mark>lambdaQuery().eq(Mark::getProjectType,project.getProjectType()).eq(Mark::getProjectLevel,project.getProjectLevel()));
//        if(reb == null){
//            return Result.error("-3","未选择项目类别/项目级别");
//        }
//        project.setProjectMark(reb.getProjectMark());
        float res1 = project.getFirst() + project.getSecond() + project.getThree();
        if(res1 >100 || res1 < 0){
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

    @GetMapping("/finddetail") //project 引用
    public Map<String, String> finddetail(@RequestParam String projectkind) {//把前台json转换为java对象
               String res = projectMapper.findsingle(projectkind);
               System.out.println(res);
        Map<String, String> res1 = new HashMap<>();
        res1.put("title", "data");
        res1.put("data", res);
        return res1;
//    return res1;
    }


//    @DeleteMapping("/{id}")
//    public Result<?> delete(@PathVariable long id) {//把前台json转换为java对象
//        projectMapper.deleteById(id);
//        return Result.success();
//    }
    @DeleteMapping("/{id}") //通过id查询的接口
    public boolean delete(@PathVariable long id) {//把前台json转换为java对象

        //先查此id内部有没有人
        Integer total = userMapper.ItemTotal(id);
        if(total>0){
            return  false;
        }
        else{
            System.out.println(id);
            projectMapper.deleteById(id);
            return true;
        }
    }

    @GetMapping("/findnamePage")//按项目名称查询
    public Result<?> findnamePage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Project::getProjectName, search);
        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
    @GetMapping("/findproPage")////按项目进度查询
    public Result<?> findproPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Project::getProjectProgress,search);
        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }

    @GetMapping //查找接口
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {

            wrapper.eq(Project::getProjectgroupId, search);

        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
    @GetMapping("/{id}") //通过id查询的接口
    public Result<?> getById(@PathVariable long id){//把前台json转换为java对象
        // System.out.println(id);
        return Result.success(projectMapper.selectById(id));
    }


    @GetMapping("/p1") //第一种项目的加载
    public Result<?> findPage1(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));

        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        wrapper.eq(Project::getProjectType,"计划科研项目");
        if (StrUtil.isNotBlank(search)) {

            wrapper.eq(Project::getProjectType,"计划科研项目").eq(Project::getProjectgroupId, search).or().eq(Project::getProjectId, search);

        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
    @GetMapping("/p2") //第一种项目的加载
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));

        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        wrapper.eq(Project::getProjectType,"专项任务");
        if (StrUtil.isNotBlank(search)) {

            wrapper.eq(Project::getProjectType,"专项任务").eq(Project::getProjectgroupId, search).or().eq(Project::getProjectId, search);

        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
    @GetMapping("/p3") //第一种项目的加载
    public Result<?> findPage3(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));

        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        wrapper.eq(Project::getProjectType,"临时性研究任务");
        if (StrUtil.isNotBlank(search)) {

            wrapper.eq(Project::getProjectType,"临时性研究任务").eq(Project::getProjectgroupId, search).or().eq(Project::getProjectId, search);

        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
    @GetMapping("/p4") //第一种项目的加载
    public Result<?> findPage4(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));

        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        wrapper.eq(Project::getProjectType,"服务支撑业务");
        if (StrUtil.isNotBlank(search)) {

            wrapper.eq(Project::getProjectType,"服务支撑业务").eq(Project::getProjectgroupId, search).or().eq(Project::getProjectId, search);

        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
    //这是主持项目调用的接口，可以查找有多少个主持项目
    @GetMapping("/host") //查找接口
    public Result<?> findhostPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        //Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Project>lambdaQuery().like(Project::getNickName,search));
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.eq(Project::getProjectgroupId, search);
        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }

}