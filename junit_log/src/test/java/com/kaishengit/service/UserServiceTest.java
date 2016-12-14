package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void findById() throws Exception {
        User user = userService.findById(5);
        user = userService.findById(5);
        System.out.println(user);

    }
    @Test
    public void findAll(){
        List<User> userList = userService.findAll();
        userList = userService.findAll();
        System.out.println(userList);

    }

    @Test
    public void save(){
        List<User> userList = userService.findAll();
        int beforeSize = userList.size();
        System.out.println("SIZE:" + userList.size());

        User user = new User();
        user.setUsername("小王");
        user.setAge(21);
        user.setAddress("北京");
        user.setTel("11111");
        userService.save(user);

        userList = userService.findAll();
        int afterSize = userList.size();
        System.out.println("SIZE:" + userList.size());

        Assert.assertEquals(beforeSize+1,afterSize);
    }

}