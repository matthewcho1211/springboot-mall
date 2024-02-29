package com.matthew.springbootmall.service.impl;

import com.matthew.springbootmall.dao.UserDao;
import com.matthew.springbootmall.dto.UserRegisterRequest;
import com.matthew.springbootmall.model.User;
import com.matthew.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
