package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where officeid= #{param1};")
    List<User> findAll(Integer officeid);
    @Select("select count(*) from user  where user.officeid= #{param1}")
    Integer officeTotal(long officeid);
    @Select("select count(*) from projectgroup  where projectgroup.projectid = #{param1}")
    Integer ItemTotal(long id);
    @Select("SELECT SUM(mark) FROM user;")
    int offciemark();
    @Select("SELECT SUM(Place_mark) FROM user;")
    int placemark();
    @Select(" SELECT SUM(projectgroup.second_mark)+SUM(projectgroup.first_Mark)+SUM(projectgroup.three_Mark) FROM projectgroup,user  where user.officeid=#{i} AND user.userid=projectgroup.userid")
    int offcietotal(@Param("i") int i);


    @Select("  SELECT count(*) FROM project,user  where user.officeid=#{i} AND user.userid=project.Projectgroup_id")
    int offcieperosontotal(@Param("i") int i);


    @Select("SELECT SUM(projectgroup.second_Mark)+SUM(projectgroup.first_Mark)+SUM(projectgroup.three_Mark) FROM projectgroup where projectgroup.userid=#{id}")
    int findpersontotal(@Param("id") long id);
    @Select("SELECT SUM(projectgroup.second_Mark)+SUM(projectgroup.first_Mark)+SUM(projectgroup.three_Mark) FROM projectgroup,project where projectgroup.userid=#{id} AND projectgroup.projectid =project.Project_id AND project.Project_type=#{s} ")
    Integer personlkind( @Param("id")Integer id,@Param("s") String s);
    @Select("select * from user where (user.userid like  #{param3} or user.username like #{param3})  AND user.officeid = #{param4}  LIMIT #{param1} ,#{param2};")
//    select * from user where  (user.userid like  '%%' or user.username like '%%') AND user.officeid =1;
    List<User> findalluser(Integer pageNum, Integer pageSize, String search,Integer officeid);
    @Select("select count(*) from user where (user.userid like  #{param1} or user.username like #{param1})  AND user.officeid = #{param2} ;")
    Integer selectTotal(String search,Integer officeid);
    @Select("select * from user where (user.userid like  #{param3} or user.username like #{param3})   LIMIT #{param1} ,#{param2};")
//    select * from user where  (user.userid like  '%%' or user.username like '%%') AND user.officeid =1;
    List<User> findalluser1(Integer pageNum, Integer pageSize, String search);
    @Select("select count(*) from user where (user.userid like  #{param1} or user.username like #{param1}) ;")
    Integer selectTotal1(String search);
    //查找个人参与项目
    //查找个人积分和
//    Integer findsumtotal(Integer userid);
    @Select("select count(*) from project where project.Projectgroup_id= #{param1}")
    Integer findsumhost(Integer userid);
    @Select("select count(*) from projectgroup where projectgroup.userid= #{param1}")
    Integer findsumjoin(Integer userid);
    @Select("SELECT SUM(projectgroup.second_Mark)+SUM(projectgroup.first_Mark)+SUM(projectgroup.three_Mark) FROM projectgroup,project where projectgroup.userid=project.Projectgroup_id AND project.Projectgroup_id =#{param1} AND project.Project_id=projectgroup.projectid")
    Integer findzhuchi(Integer userid);
    @Select("select sum(user.mark) from user where user.officeid = #{param1}")
    Integer offciereward(int i);
    @Select("select sum(user.Place_mark) from user where user.officeid = #{param1}")
    Integer placereward(int i);
}
