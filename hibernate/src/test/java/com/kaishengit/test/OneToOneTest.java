package com.kaishengit.test;

import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOneTest {

    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Person person = new Person();
        person.setName("rose");

        Card card = new Card();
        card.setCardName("12312312321");
        card.setPerson(person);

        session.save(person);
        session.save(card);

        session.getTransaction().commit();
    }

    @Test
    public void find(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Person person = (Person) session.get(Person.class,1);
        System.out.println(person.getName());

        session.getTransaction().commit();
    }
}
