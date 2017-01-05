package com.kaishengit;

import com.kaishengit.mapper.SchoolMapper;
import com.kaishengit.pojo.School;
import com.kaishengit.pojo.Student;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SchoolMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);
        School school = schoolMapper.findById(1);
        System.out.println(school);

        List<Student> studentList = school.getStudentList();
        for(Student student : studentList){
            System.out.println(student);
        }

        sqlSession.close();
    }

    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);
        List<School> schoolList = schoolMapper.findAll();
        for(School school : schoolList){
            System.out.println(school);

            List<Student> studentList = school.getStudentList();
            for(Student student : studentList){
                System.out.println(student);
            }
            System.out.println("-------------------------");
        }
        sqlSession.close();
    }
}
