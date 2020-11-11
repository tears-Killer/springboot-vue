package com.chinasoft.service.impl;

import com.chinasoft.mapper.UserMapper;
import com.chinasoft.pojo.User;
import com.chinasoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String account, String password) {
        return userMapper.selectByAccountAndPassword(account,password);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
