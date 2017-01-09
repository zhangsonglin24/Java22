package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {
        User user = new User("aaa","0000");
        userDao.save(user);

    }

    @Test
    public void del() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void findByUserName() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}