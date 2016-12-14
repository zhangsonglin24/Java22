package com.kaishengit.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;

import java.util.concurrent.TimeUnit;

public class UserServiceWithGuava {

    private static LoadingCache<String,User> cache = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterAccess(60, TimeUnit.SECONDS)
            .build(new CacheLoader<String, User>() {
                @Override
                public User load(String key) throws Exception {
                    UserDao userDao = new UserDao();
                    return userDao.findById(Integer.valueOf(key));
                }
            });


    public User findById(Integer id){
        User user = cache.getUnchecked(id.toString());
        return user;
    }
}
