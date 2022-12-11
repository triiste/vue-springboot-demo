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
    private String OfficeFile;//室内领导文件名称
    private String OfficeAddress;//室内下载地址
    private String PlaceFile;//所领导文件名称
    private String PlaceAddress;//所领导文件下载地址
}
