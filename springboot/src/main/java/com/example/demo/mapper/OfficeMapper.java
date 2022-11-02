package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Office;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OfficeMapper extends BaseMapper<Office> {
    @Select("select office.id,office.officename,office.officeuserid,user.username from office,user  where user.userid=office.officeuserid  and office.id  like #{param3}  LIMIT #{param1} ,#{param2};")
    List<Office> findAll(Integer pageNum, Integer pageSize,String search);
    @Select("select count(*) from office,user  where user.userid=office.officeuserid and office.id  like #{param1};")
    Integer selectTotal(String search);
}
