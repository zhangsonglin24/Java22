package com.kaishengit.test;

import com.kaishengit.dao.impl.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestCase {

    @Test
    public void find(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDaoImpl userDao = (UserDaoImpl) applicationContext.getBean("userDaoImpl");
        UserDaoImpl userDao1 = (UserDaoImpl) applicationContext.getBean("userDaoImpl");
        System.out.println(userDao == userDao1);
        userDao.findAll();
        userDao.update();

    }

}
