package com.kaishengit.service;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void save(User user);

    User findById(Integer id);

    void editUser(User user);

    void del(Integer id);
}
