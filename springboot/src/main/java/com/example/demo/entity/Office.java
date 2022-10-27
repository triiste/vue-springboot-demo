package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("office") //和数据库名一样
@Data
public class Office {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer officeuserid;
//    private Integer officeID;
    private String username;
    private String officename;
}
