package com.matthew.springbootmall.service;

import com.matthew.springbootmall.dto.UserLoginRequest;
import com.matthew.springbootmall.dto.UserRegisterRequest;
import com.matthew.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
