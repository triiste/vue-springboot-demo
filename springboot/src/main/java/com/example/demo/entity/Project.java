package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("project") //和数据库名一样
@Data
public class Project {
    @TableId(value = "Project_id",type = IdType.AUTO)
    private Integer ProjectId;
    private Integer ProjectItemid;
    private Integer ProjectgroupId;
    private String ProjectName;
    private Integer GroupId;
    private String ProjectDetial;
    private String ProjectCycle;
    private String ProjectProgress;
    private String ProjectProduct;
    private String ProjectType;
    private String ProjectLevel;
    private String Projectkind;
    private Date date;

    private float ProjectMark;
    private float FirstMark;
    private float SecondMark;
    private float ThreeMark;
    private float first;
    private float second;
    private float three;
    private Integer ProjectReward;
    private Integer sumTotal;//取目前已经得到的总分

}
