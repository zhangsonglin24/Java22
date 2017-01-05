package com.kaishengit;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTestCase {

    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",2);
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void save(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        User user = new User();
        user.setUsername("kobe");
        user.setPassword("111111");
        sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);

        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void update(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession(true);

        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",10);
        user.setPassword("112");
        sqlSession.update("com.kaishengit.mapper.UserMapper.update",user);
        sqlSession.close();
    }
    @Test
    public void del(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        sqlSession.delete("com.kaishengit.mapper.UserMapper.del",10);
        sqlSession.close();
    }

    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
        for(User user : userList){
            System.out.println(user );
        }
        sqlSession.close();
    }
    @Test
    public void findByParam(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> param = new HashMap<>();
        param.put("username","tom");
        param.put("password","111");
        User user = userMapper.findByParam(param);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void findByParams(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByParams("小样","1101");
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void batchSave(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("kobe","776"));
        userList.add(new User("pule","333"));
        userList.add(new User("mike","745"));
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.batchSave(userList);
        sqlSession.commit();
    }

}
