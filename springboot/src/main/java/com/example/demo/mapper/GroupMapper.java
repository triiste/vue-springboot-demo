package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Group;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface GroupMapper extends BaseMapper<Group> {

    @Select("select * from projectgroup where projectgroup.userid =#{param1};")
    List<Group> finditem(Integer userid);
}
