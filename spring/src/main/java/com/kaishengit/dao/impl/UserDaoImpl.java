package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;

public class UserDaoImpl implements UserDao {

    public UserDaoImpl(){
        System.out.println("UserDaoImpl is create");
    }

    @Override
    public void update() {

        System.out.println("呵呵");

    }

    @Override
    public void findAll() {
        System.out.println("哈哈");

    }
}
