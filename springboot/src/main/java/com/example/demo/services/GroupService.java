package com.example.demo.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Group;
import com.example.demo.mapper.GroupMapper;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends ServiceImpl<GroupMapper, Group> {
}
