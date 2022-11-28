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
    private String MartialStatus;
    private Integer role;
    private Integer mark;
    private Integer officeid;
    private Integer PlaceMark;
    private Integer totalMark;
    private Integer joincount; //参与个数
    private Integer joinmark; //参与积分和
    private Integer hostcount; //主持个数
    private Integer hostmark; //主持积分和
    private Integer projectmark; //项目积分
}
