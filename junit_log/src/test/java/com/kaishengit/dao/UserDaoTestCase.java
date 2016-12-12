package com.kaishengit.dao;

import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserDaoTestCase {
    UserDao userDao = new UserDao();

    @Test
    public void testSave(){

        User user = new User();

        user.setUsername("小名");
        user.setAddress("焦作");
        user.setAge(23);
        user.setTel("123456");

        userDao.save(user);
    }
    @Test
    public void testFindById(){
        User user = userDao.findById(6);
        Assert.assertNotNull(user);

    }
    @Test
    public void testFindAll(){
        List<User> userList = userDao.findAll();
        Assert.assertEquals(8,userList.size());
    }
    @Test
    public void testDel() {
        userDao.del(7);
    }
}
