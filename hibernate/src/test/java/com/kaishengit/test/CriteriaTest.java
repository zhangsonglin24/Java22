package com.kaishengit.test;


import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

public class CriteriaTest {

    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();

        for(User user : userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void where(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);
        //criteria.add(Restrictions.eq("userName","李"));

        //或（or）的关系
         Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("userName","李"));
        disjunction.add(Restrictions.eq("userName","hehe"));

        criteria.add(disjunction);

        //或（or）的关系
        //criteria.add(Restrictions.or(Restrictions.eq("userName","李"),Restrictions.eq("userName","hehe")));
        List<User> userList = criteria.list();


        for(User user : userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }


}
