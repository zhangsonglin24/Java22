package com.kaishengit.service;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void save(User user);

    User findById(Integer id);


    void del(Integer id);

    List<Role> findAllRole();

    void saveNewUser(User user, Integer[] roleIds);

    void editUser(User user, Integer[] roleIds);
}
