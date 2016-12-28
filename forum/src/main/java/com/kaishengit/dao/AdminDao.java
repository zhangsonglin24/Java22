package com.kaishengit.dao;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class AdminDao {
    public Admin findAdminByAdminName(String adminName) {
        String sql = "select * from t_admin where username = ?";
        return DbHelp.query(sql,new BeanHandler<>(Admin.class),adminName);
    }
}
