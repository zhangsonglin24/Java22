package com.kaishengit.test;


import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class HibernateTest {

    @Test
    public void save(){
       //1.创建SessionFactory
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //2.创建Session
        Session session = sessionFactory.getCurrentSession();

        //3.事务
        Transaction transaction = session.getTransaction();
        transaction.begin();

        User user = new User();
        user.setUserName("王文文");
        user.setPassword("122312");
        session.save(user);

        //4.结束
        transaction.commit();
        //会自动session.close();
    }

    @Test
    public void findById(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,6);
        System.out.println(user);


        session.getTransaction().commit();
    }

    @Test
    public void update(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,6);
        user.setUserName("王杰魁");


        session.getTransaction().commit();
    }

    @Test
    public void delte(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,6);
        session.delete(user);

        session.getTransaction().commit();
    }


    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

       //String hql = "from User where userName = ?";
        String hql = "from User";
        Query query = session.createQuery(hql);
        //query.setParameter(0,"rose");

        List<User> userList = query.list();

        for(User user : userList){
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
}
