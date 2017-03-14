package com.kaishengit.test;


import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;


public class HqlTest {

    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "from User where userName = :name";

        Query query = session.createQuery(hql);
        query.setParameter("name","rose");

        User user = (User) query.uniqueResult();

            System.out.println(user);


        session.getTransaction().commit();
    }

    @Test
    public void findProperty(){
    Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "select id,userName,password from User";
        Query query = session.createQuery(hql);
        //获取不完整对象时 一个时List<Object>   多个时List<Object[]>
        List<Object[]> nameList = query.list();

        for(Object[] obj : nameList){
            System.out.println(obj[0] +"->" +obj[1] +"->" + obj[2]);

        }


        session.getTransaction().commit();

    }
}

