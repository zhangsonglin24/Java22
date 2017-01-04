package com.kaishengit;

import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.pojo.Student;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findById(1);
        System.out.println(student);
        sqlSession.close();
    }
}
