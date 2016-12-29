package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.util.List;

public class UserDao {


    public User findByUserName(String username) {
        String sql = "select * from t_user where username = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),username);
    }

    public User findByEmail(String email) {
        String sql = "select * from t_user where email = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),email);
    }

    public void save(User user) {
        String sql = "insert into t_user(username, password, email, phone, state, avatar) values (?,?,?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar());
    }

    public void update(User user) {
        String sql = "update t_user set password=?,email=?,phone=?,state=?,avatar=? where id = ?";
        DbHelp.update(sql,user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar(),user.getId());
    }

    public User findById(Integer id) {
        String sql = "select * from t_user where id = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),id);
    }

    public List<User> findUserList() {
        String sql = "SELECT MAX(tll.logintime),tu.username,tu.createtime,tll.ip\n" +
                "FROM t_user tu,t_login_log tll\n" +
                "WHERE tu.id = tll.userid\n" +
                "GROUP BY tu.id";
        return DbHelp.query(sql,)
    }
}
