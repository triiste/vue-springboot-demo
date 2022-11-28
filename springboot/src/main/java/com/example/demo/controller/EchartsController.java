package com.example.demo.controller;


import cn.hutool.core.collection.CollUtil;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.Project;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

//简单demo
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    UserMapper userMapper;
    //每一个人三个阶段的总得分 也就是每年得年收入
        //个人查看的 不同项目不同的积分和
        @GetMapping("/mark")
        //一个人参与了几个项目
        public List<Group> member(Integer userid){
            List<Group> all = groupMapper.finditem(userid);

            return all;
          }
        //所有室奖励积分以及所奖励积分
        @GetMapping("/officemark")
        public int[] officemark(){
            int officemark1=userMapper.offciemark();
            int placemark1=userMapper.placemark();
            int [] project = new int [2];
            project[0]=officemark1;
            project[1]=placemark1;
            return project;
        }


//    @GetMapping("/officemarktotal")
//    //每个室的总积分
//    public Map<Integer, Integer> officemarktotal(){
//        int size=5;
//        int [] officeid1=new int[size];
//        int [] officeresult=new int[size];
//        int [] index =new int [size];
//        int max=0,k,value;
//        Map<Integer,Integer> res=new HashMap<>();
//        Map<Integer,Integer> res1=new LinkedHashMap<>();
//        //左边是key 右边是value 通过左边获得右边
//        officeid1[0]=1;
//        officeid1[1]=2;
//        officeid1[2]=3;
//        officeid1[3]=4;
//        officeid1[4]=5;
//        for(int i=0;i<size;i++){
//            officeresult[i]=userMapper.offcietotal(officeid1[i]);
////            System.out.println(officeresult[i]);
////            System.out.println(i+1);
//            res.put(officeresult[i],i+1);
//        }
//        //有bug 重复的值无法索引
//        res.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(entry->{res1.put(entry.getKey(), entry.getValue());});
//        return res;
//    }
    @GetMapping("/officemarktotal")
    public Object[] officemarktotal()
    {
        int size=5;
//        int[] array1 = new int[5];
        int[] array1 = {1, 2, 3, 4, 5};//记录下标用的
        Integer[] array2 = {0, 0, 0, 0, 0};
        Integer[] array3 = new Integer[size];
                for(int i=0;i<size;i++){
                array2[i]=userMapper.offcietotal(array1[i]);
                array3[i]=array2[i];
                }
             Arrays.sort(array2, Collections.reverseOrder());
//        for(int i=0;i<size;i++){
//            System.out.println(array2[i]);
//        }
        boolean[] flag={false,false,false,false,false};
        for(int i=0;i<size;i++){
            //已经排好序，从大到小遍历
            for(int j=0;j<size;j++){
                //也就是说j只能用一遍
                if(array2[i] == array3[j]&&!flag[j]){
                        array1[i]=j;
                        flag[j]=true;
                        break;
                }
            }
        }
        Object[] arrayObjects = new Object[2];
        arrayObjects [0] = array1;
        arrayObjects [1] = array2;
        return arrayObjects;
    }
    @GetMapping("/officetotal")
    public Object[] officetotal()
    {
        int size=5;
//        int[] array1 = new int[5];
        int[] array1 = {1, 2, 3, 4, 5};//记录下标用的
        Integer[] array2 = {0, 0, 0, 0, 0};
        Integer[] array3 = new Integer[size];
        for(int i=0;i<size;i++){

            array2[i]=userMapper.offcieperosontotal(array1[i]);
            array3[i]=array2[i];
        }
        Arrays.sort(array2, Collections.reverseOrder());
        for(int i=0;i<size;i++){
            System.out.println(array2[i]);//8 5 0 0 0
        }
        for(int i=0;i<size;i++){
            System.out.println(array3[i]); //5 8 0 0 0
        }
        boolean[] flag={false,false,false,false,false};
        for(int i=0;i<size;i++){
            //已经排好序，从大到小遍历
            for(int j=0;j<size;j++){
                //也就是说j只能用一遍,该轮赋值之后直接break,因为可能使得后面发生变化
                if(array2[i] == array3[j]&&!flag[j]){
                    array1[i]=j;
                    flag[j]=true;
                    break;
                }
            }
        }
        for(int i=0;i<size;i++){
            System.out.println(array1[i]); //1 0 2 3 4
        }
        Object[] arrayObjects = new Object[2];
        arrayObjects [0] = array1;
        arrayObjects [1] = array2;
        return arrayObjects;
    }
    @GetMapping("/officereward")
    public Object[] officereward()
    {
        int size=5;
//        int[] array1 = new int[5];
        int[] array1 = {1, 2, 3, 4, 5};//记录下标用的
        Integer[] array2 = {0, 0, 0, 0, 0};
        Integer[] array3 = new Integer[size];
        for(int i=0;i<size;i++){

            array2[i]=userMapper.offciereward(array1[i]);
            array3[i]=array2[i];
        }
        Arrays.sort(array2, Collections.reverseOrder());
        for(int i=0;i<size;i++){
            System.out.println(array2[i]);//8 5 0 0 0
        }
        for(int i=0;i<size;i++){
            System.out.println(array3[i]); //5 8 0 0 0
        }
        boolean[] flag={false,false,false,false,false};
        for(int i=0;i<size;i++){
            //已经排好序，从大到小遍历
            for(int j=0;j<size;j++){
                //也就是说j只能用一遍,该轮赋值之后直接break,因为可能使得后面发生变化
                if(array2[i] == array3[j]&&!flag[j]){
                    array1[i]=j;
                    flag[j]=true;
                    break;
                }
            }
        }
        for(int i=0;i<size;i++){
            System.out.println(array1[i]); //1 0 2 3 4
        }
        Object[] arrayObjects = new Object[2];
        arrayObjects [0] = array1;
        arrayObjects [1] = array2;
        return arrayObjects;
    }
    //所领导的奖励积分
    @GetMapping("/palcereward")
    public Object[] placereward()
    {
        int size=5;
//        int[] array1 = new int[5];
        int[] array1 = {1, 2, 3, 4, 5};//记录下标用的
        Integer[] array2 = {0, 0, 0, 0, 0};
        Integer[] array3 = new Integer[size];
        for(int i=0;i<size;i++){

            array2[i]=userMapper.placereward(array1[i]);
            array3[i]=array2[i];
        }
        Arrays.sort(array2, Collections.reverseOrder());
        for(int i=0;i<size;i++){
            System.out.println(array2[i]);//8 5 0 0 0
        }
        for(int i=0;i<size;i++){
            System.out.println(array3[i]); //5 8 0 0 0
        }
        boolean[] flag={false,false,false,false,false};
        for(int i=0;i<size;i++){
            //已经排好序，从大到小遍历
            for(int j=0;j<size;j++){
                //也就是说j只能用一遍,该轮赋值之后直接break,因为可能使得后面发生变化
                if(array2[i] == array3[j]&&!flag[j]){
                    array1[i]=j;
                    flag[j]=true;
                    break;
                }
            }
        }
        for(int i=0;i<size;i++){
            System.out.println(array1[i]); //1 0 2 3 4
        }
        Object[] arrayObjects = new Object[2];
        arrayObjects [0] = array1;
        arrayObjects [1] = array2;
        return arrayObjects;
    }


//    @GetMapping("/officetotal")
//    //每个室的总积分
//    public Map<Integer, Integer> officetotal(){
//        int size=5;
//        int [] officeid1=new int[size];
//        int [] officeresult=new int[size];
//        Map<Integer,Integer> res=new HashMap<>();
//        Map<Integer,Integer> res1=new LinkedHashMap<>();
//        //左边是key 右边是value 通过左边获得右边
//        officeid1[0]=1;
//        officeid1[1]=2;
//        officeid1[2]=3;
//        officeid1[3]=4;
//        officeid1[4]=5;
//        for(int i=0;i<size;i++){
//            officeresult[i]=userMapper.offcieperosontotal(officeid1[i]);
//            System.out.println(officeresult[i]);
//            System.out.println(i+1);
//            res.put(officeresult[i],i+1);
//        }
////        有重复的值无法索引
////        for(int i=0;)
//
//        System.out.println(res);
//        res.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(entry->{res1.put(entry.getKey(), entry.getValue());});
//        System.out.println(res1);
//        return res;
//    }




        //每个室的总积分
//    @GetMapping("/officemarktotal")
//        //每个室的总积分
//        public int[] officemarktotal(){
//            int size=5;
//            int [] officeid1=new int[size];
//            int [] officeresult=new int[size];
//            int [] index =new int [size];
//            int max=0,k,value;
//            officeid1[0]=1;
//            officeid1[1]=2;
//            officeid1[2]=3;
//            officeid1[3]=4;
//            officeid1[4]=5;
//            for(int i=0;i<size;i++){
//
//                officeresult[i]=userMapper.offcietotal(officeid1[i]);
//            }
//            return officeresult;
//        }
        //单个人参与的项目积分总和
        @GetMapping("/{id}") //通过id查询的接口
        public int getById(@PathVariable long id){//把前台json转换为java对象
            // System.out.println(id);
            //先查group表里有没有
            //如果没有再去判断
            int grouptotal =groupMapper.findcount(id);
            int total=0;
            if(grouptotal ==0){
                return  grouptotal;
            }else{
                total=userMapper.findpersontotal(id);
                return total;
            }
        }

        //单个人参与的每类项目的积分和
        @GetMapping("/personmark")
        public Integer[] personmark(Integer id){

            String [] s=new String[4];
            s[0]="计划科研项目";
            s[1]="专项任务";
            s[2]="临时性研究任务";
            s[3]="服务支撑业务";
            Integer [] personresult=new Integer[4];
            for(int i=0;i<4;i++){
                personresult[i]=userMapper.personlkind(id,s[i]);
            }
            return personresult;
        }



         @GetMapping("/projectkind")
        public int[] number(){
             List<Project> project1=projectMapper.find1();
             List<Project> project2=projectMapper.find2();
             List<Project> project3=projectMapper.find3();
             List<Project> project4=projectMapper.find4();
             int [] projectkind = new int [4];
//             project1.get(0).getProjectMark();
             int sum1=0,sum2=0,sum3=0,sum4=0;
             for(int i=0;i<project1.size();i++){
                 sum1+=project1.get(i).getProjectMark();
             }
             for(int i=0;i<project2.size();i++){
                 sum2+=project2.get(i).getProjectMark();
             }
             for(int i=0;i<project3.size();i++){
                 sum3+=project3.get(i).getProjectMark();
             }
             for(int i=0;i<project4.size();i++){
                 sum4+=project4.get(i).getProjectMark();
             }
             projectkind [0] = sum1;
             projectkind [1] = sum2;
             projectkind [2] = sum3;
             projectkind [3] = sum4;
            return projectkind;
        }
}
