package com.galleon.springboot.service;

import com.galleon.springboot.entity.User;
import com.galleon.springboot.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public List<User> queryAll(){
        PageHelper.startPage(1, 2);
        PageHelper.orderBy("role_id");
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Transactional
    public User queryByUsername(String username){
        User user = userMapper.queryByUsername(username);
        return user;
    }
}
