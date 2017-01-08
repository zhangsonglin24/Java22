package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.impl.UserDaoImpl;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestCase {

    @Test
    public void find(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
       /* UserDao userDao = (UserDaoImpl) applicationContext.getBean("userDaoImpl");

        userDao.findAll();
        userDao.update();*/

        UserService userService = (UserService) applicationContext.getBean("userService");

        userService.findAll();
        userService.update();

    }

}
