package com.kaishengit.test;

import com.kaishengit.pojo.Student2;
import com.kaishengit.pojo.Teacher2;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class MangToMangTest {

    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Student2 s1 = new Student2();
        s1.setStuname("S1");

        Student2 s2 = new Student2();
        s2.setStuname("S2");

        Teacher2 t1 = new Teacher2();
        t1.setTeaname("T1");

        Teacher2 t2 = new Teacher2();
        t2.setTeaname("T2");

        Set<Teacher2> teacher2Set = new HashSet<>();
        teacher2Set.add(t1);
        teacher2Set.add(t2);

        s1.setTeacher2Set(teacher2Set);
        s2.setTeacher2Set(teacher2Set);

        session.save(t1);
        session.save(t2);
        session.save(s1);
        session.save(s2);

        session.getTransaction().commit();
    }

    @Test
    public void find(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Student2 student2 = (Student2) session.get(Student2.class,5);
        System.out.println(student2.getStuname());

        Set<Teacher2> teacher2Set = student2.getTeacher2Set();
        for(Teacher2 teacher : teacher2Set){
            System.out.println(teacher);

        }

        session.getTransaction().commit();
    }
}
