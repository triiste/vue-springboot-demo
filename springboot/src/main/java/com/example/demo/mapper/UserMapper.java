package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where officeid= #{param1};")
    List<User> findAll(Integer officeid);
    @Select("select count(*) from user  where user.officeid= #{param1}")
    Integer officeTotal(long officeid);
    @Select("select count(*) from projectgroup  where projectgroup.projectid = #{param1}")
    Integer ItemTotal(long id);
}
