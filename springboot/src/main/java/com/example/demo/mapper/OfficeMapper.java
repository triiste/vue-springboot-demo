package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Office;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface OfficeMapper extends BaseMapper<Office> {
    @Select("select office.id,office.officename,office.officeuserid,user.username from office,user  where user.userid=office.officeuserid  and office.id  like #{param3}  LIMIT #{param1} ,#{param2};")
    List<Office> findAll(Integer pageNum, Integer pageSize,String search);
    @Select("select count(*) from office,user  where user.userid=office.officeuserid and office.id  like #{param1};")
    Integer selectTotal(String search);

    //查找室主任奖励的附件
    @Select("select office.Office_address FROM office where office.id =#{officeid}")
    String findofficeurl(@Param("officeid") Integer officeid);
    //更新室主任的地址等
    @Update("UPDATE office set office.Office_file =#{filename},office.Office_address= #{url} where office.id = #{officeid}")
    void updatefiles(@Param("officeid") Integer projectid,@Param("filename") String filename,@Param("url") String url);

    //下面是所领导奖励的两个

    @Select("select office.Place_address FROM office where office.id =#{officeid}")
    String findofficeurl1(@Param("officeid") Integer officeid);
    //更新室主任的地址等
    @Update("UPDATE office set office.Place_file =#{filename},office.Place_address= #{url} where office.id = #{officeid}")
    void updatefiles1(@Param("officeid") Integer projectid,@Param("filename") String filename,@Param("url") String url);


}
