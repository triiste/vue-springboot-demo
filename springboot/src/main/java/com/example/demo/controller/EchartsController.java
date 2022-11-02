package com.example.demo.controller;


import cn.hutool.core.collection.CollUtil;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//简单demo
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    //    @GetMapping("/example")
    //   public Result get(){
    //       Map<String,Object> map=new HashMap<>();
    //       map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
    //       map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 26));
    //
    //       return Result.success(map);
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;
    //每一个人三个阶段的总得分 也就是每年得年收入
        //个人查看的 不同项目不同的积分和
        @GetMapping("/mark")
        //一个人参与了几个项目
        public List<Group> member(Integer userid){
            List<Group> all = groupMapper.finditem(userid);

            return all;
    }
//    @GetMapping("/members")
//    public Result members() {
//        List<User> list = userService.list();
//        int q1 = 0; // 第一季度
//        int q2 = 0; // 第二季度
//        int q3 = 0; // 第三季度
//        int q4 = 0; // 第四季度
//        for (User user : list) {
//            Date createTime = user.getCreateTime();
//            Quarter quarter = DateUtil.quarterEnum(createTime);
//            switch (quarter) {
//                case Q1: q1 += 1; break;
//                case Q2: q2 += 1; break;
//                case Q3: q3 += 1; break;
//                case Q4: q4 += 1; break;
//                default: break;
//            }
//        }
//        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
//    }
}
