package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    @Select("select * from project,projectgroup  where projectgroup.userid=#{userid} AND projectgroup.projectid = project.Project_id LIMIT #{param2} ,#{param3};")
    List<Project> findAll(@Param("userid") Integer userid,Integer pageNum, Integer pageSize);
    @Select("select count(*) from project,projectgroup  where projectgroup.userid=#{userid} AND projectgroup.projectid = project.Project_id")
    Integer selectTotal(Integer userid);
    @Select("select *  from project where Project_type = \"计划科研项目\";")
    List<Project> find1();
    @Select("select *  from project where Project_type = \"专项任务\";")
    List<Project> find2();
    @Select("select *  from project where Project_type = \"临时性研究任务\";")
    List<Project> find3();
    @Select("select *  from project where Project_type = \"支撑机关、服务部队任务\";")
    List<Project> find4();
    @Select("select mark.Project_type from mark where mark.Project_seq = #{projectType}")
    String findkind(@Param("projectType") String projectType);
    @Select("select mark.Project_mark from mark where mark.Project_seq = #{projectType}")
    Integer findmark(@Param("projectType") String projectType);
    @Select("select mark.Project_details  from mark where mark.Project_seq=#{projectkind}")
    String findsingle(String projectkind);
}
