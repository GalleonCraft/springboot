package com.galleon.springboot.service;

import com.galleon.springboot.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void  queryAll(){
        PageHelper.startPage(1, 2);
        PageHelper.orderBy("id");
        userMapper.selectAll();
    }
}
