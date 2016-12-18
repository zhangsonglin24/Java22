package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static String DRIVER;
    private static String URL ;
    private static String USERNAME ;
    private static String PASSWORD ;
    private static BasicDataSource dataSource = new BasicDataSource();

    static {

        DRIVER = Config.get("jdbc.driver");
        URL =  Config.get("jdbc.url");
        USERNAME =  Config.get("jdbc.username");
        PASSWORD =  Config.get("jdbc.password");

        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(10);
        dataSource.setMinIdle(6);
        dataSource.setMaxIdle(30);
        dataSource.setMaxWaitMillis(5000);

    }

    /**
     * 获取数据库连接池
     */

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
                throw new DataAccessException("获取数据库连接异常",e);
        }
        return connection;
    }
}
