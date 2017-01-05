package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findById(Integer id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void del(Integer id);
    User findByParam(Map<String,Object> param);
    User findByParams(@Param("username")String username,@Param("pwd") String password);
    void batchSave(List<User> userList);

}
