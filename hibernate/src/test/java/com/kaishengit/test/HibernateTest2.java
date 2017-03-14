package com.kaishengit.test;


import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateTest2 {

    @Test
    public void getAndLoad(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.load(User.class,1);
        System.out.println(user);


        session.getTransaction().commit();
    }

    @Test
    public void saveAndUpdate(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        User user = new User();
        user.setUserName("呵");
        user.setPassword("111");
        session.save(user);

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        user.setUserName("wang");
        user.setPassword("111");
        session1.save(user);
        System.out.println(user);

        session1.getTransaction().commit();
    }
}
