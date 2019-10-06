package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {

    void save(User user);

    void updateById(Integer id) throws Exception;
}
