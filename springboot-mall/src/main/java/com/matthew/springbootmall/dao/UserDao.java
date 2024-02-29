package com.matthew.springbootmall.dao;

import com.matthew.springbootmall.dto.UserRegisterRequest;
import com.matthew.springbootmall.model.User;
import org.springframework.stereotype.Component;


public interface UserDao {
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
