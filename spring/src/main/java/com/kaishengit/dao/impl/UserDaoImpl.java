package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void update(User user) {

    }

    @Override
    public void save(User user) {
        String sql = "insert into t_user(username,password) values(?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public void del(Integer id) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public User findByUserName(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
