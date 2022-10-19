package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("projectgroup") //和数据库名一样
@Data
public class Group {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer projectid;
    private Integer userid;
    private float mark;
        private String handler;
    private String ProjectProgress;
        private Integer officeDirector;
    private Integer scientific;
    private float FirstMark;
    private float SecondMark;
    private float ThreeMark;
    private float first;
    private float second;
    private float three;
    private float GroupReward;
}
