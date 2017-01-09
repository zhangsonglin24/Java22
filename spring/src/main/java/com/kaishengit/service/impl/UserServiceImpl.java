package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

   /* public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }*/

    private String name;
    private Integer age;
    private List<String> list;
    private Set<Double> set;
    private Map<String,Object> map;
    private Properties properties;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<Double> set) {
        this.set = set;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void save(User user) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
