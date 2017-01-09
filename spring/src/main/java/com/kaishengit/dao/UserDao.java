package com.kaishengit.dao;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserDao {

    void update(User user);
    void save(User user);
    void del(Integer id);
    User findById(Integer id);
    User findByUserName(String username);
    List<User> findAll();


}
