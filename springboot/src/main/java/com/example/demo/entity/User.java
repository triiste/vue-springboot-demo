package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@TableName("user") //和数据库名一样
@Data
@ToString
public class User {
    @TableId(value = "userid",type = IdType.AUTO)
    private Integer userid;
    private String userjobid;
    private String username;
    private String password;
    private String telephone;
    private Integer age;
    private String sex;
    private String address;
    private String position;
    private String permission;
    private Integer role;
    private Integer mark;
    private Integer officeid;

}
