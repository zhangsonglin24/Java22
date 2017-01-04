package com.kaishengit;

import com.kaishengit.mapper.SchoolMapper;
import com.kaishengit.pojo.School;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class SchoolMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);
        School school = schoolMapper.findById(1);
        System.out.println(school);
        sqlSession.close();
    }
}
