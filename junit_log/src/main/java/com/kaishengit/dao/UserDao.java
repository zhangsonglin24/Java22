package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class UserDao {

    public void save(User user){
        String sql = "insert into t_user(username,address,age,tel) values(?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getAddress(),user.getAge(),user.getTel());
    }


    public User findById(Integer id) {
        String sql = "select * from t_user where id = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),id);
    }

    public List<User> findAll() {
        String sql = "select * from t_user";
        return DbHelp.query(sql, new BeanListHandler<>(User.class));

    }
    public void del(int id) {
        String sql = "delete from t_user where id = ?";
        DbHelp.update(sql,id);
    }
       }
