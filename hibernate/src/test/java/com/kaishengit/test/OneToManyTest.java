package com.kaishengit.test;


import com.kaishengit.pojo.School;
import com.kaishengit.pojo.Student;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OneToManyTest {

    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        School school = new School();
        school.setName("师范");

        Student student = new Student();
        student.setStuname("张三");
        student.setSchool(school);

        Student student1 = new Student();
        student1.setStuname("李四");
        student1.setSchool(school);

        //inverse="true"时，.让一端放弃关系维护（一的那端）
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(student);
        studentSet.add(student1);

        school.setStudentSet(studentSet);

        //先存一，再存多
        session.save(school);
        session.save(student);
        session.save(student1);


        session.getTransaction().commit();
    }


    @Test
    public void findOneToMang(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        School school = (School) session.get(School.class,3);
        System.out.println(school.getName());


        //延时加载
        Set<Student> studentSet = school.getStudentSet();
        for(Student student : studentSet){
            System.out.println(student.getStuname());
        }

        session.getTransaction().commit();

    }

    @Test
    public void findMangToOne(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        //N+1
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> studentList = criteria.list();

        for(Student student : studentList){
            System.out.println(student.getStuname()+ "->" + student.getSchool().getName());
        }

        session.getTransaction().commit();

    }

    @Test
    public void delete(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        //cascade="delete" 级联删除
        School school = (School) session.get(School.class,3);
        session.delete(school);

        session.getTransaction().commit();

    }
}


