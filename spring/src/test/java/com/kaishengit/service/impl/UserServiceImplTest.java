package com.kaishengit.service.impl;


import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void save(){
        User user = new User();
        user.setUsername("隔壁老王");
        user.setPassword("123123");
        userService.save(user);
    }

    @Test
    public void findById(){
        User user = userService.findById(23);
        System.out.println(user);
    }

}
