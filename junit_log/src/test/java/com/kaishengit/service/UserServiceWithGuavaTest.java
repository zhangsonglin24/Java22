package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserServiceWithGuavaTest {

    private UserServiceWithGuava userServiceWithGuava = new UserServiceWithGuava();

    @Test
    public void findById() throws Exception {
        User user = userServiceWithGuava.findById(3);
        user = userServiceWithGuava.findById(3);

        System.out.println(user);

    }

}