package com.kaishengit.Dao;


import com.kaishengit.pojo.User;

public class UserDao {

    public void save(User user){
        System.out.println(user.getUserName() + "保存");
    }
}
