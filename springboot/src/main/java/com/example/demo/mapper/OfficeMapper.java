package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Office;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OfficeMapper extends BaseMapper<Office> {
    @Select("select office.id,office.officename,office.officeuserid,user.username from office,user  where user.userid=office.officeuserid  LIMIT #{arg0} ,#{arg1};")
    List<Office> findAll(Integer pageNum, Integer pageSize);
    @Select("select count(*) from office,user  where user.userid=office.officeuserid;")
    Integer selectTotal();
}
