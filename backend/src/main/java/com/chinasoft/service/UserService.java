package com.chinasoft.service;

import com.chinasoft.pojo.User;

public interface UserService {

    User login(String account,String password);

    int updateUser(User user);
}
