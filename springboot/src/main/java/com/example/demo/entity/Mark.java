package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("mark") //和数据库名一样
@Data
public class Mark {
//    public static boolean getProjectType;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String ProjectType;
//    private String ProjectLevel;
    private String ProjectSeq;
    private String ProjectDetails;
    private float ProjectMark;

}