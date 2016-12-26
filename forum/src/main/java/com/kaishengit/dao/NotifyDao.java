package com.kaishengit.dao;

import com.kaishengit.entity.Notify;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class NotifyDao {


    public List<Notify> findNotifyByUserId(Integer id) {
        String sql = "select * from t_notify where userid = ?";
        return DbHelp.query(sql,new BeanListHandler<>(Notify.class),id);
    }

    public void save(Notify notify) {
        String sql = "insert into t_notify (userid,content,state) values(?,?,?)";
        DbHelp.update(sql,notify.getUserid(),notify.getContent(),notify.getState());
    }
}
