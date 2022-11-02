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
}
