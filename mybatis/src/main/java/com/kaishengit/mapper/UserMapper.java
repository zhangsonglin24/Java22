package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from t_user where id = #{id}")
    User findById(Integer id);
    List<User> findAll();
    void save(User user);
    @Update("update t_user set username = #{username},password = #{password} where id = #{id}")
    void update(User user);
    void del(Integer id);
    User findByParam(Map<String,Object> param);
    User findByParams(@Param("username")String username,@Param("pwd") String password);
    void batchSave(List<User> userList);
    @Select("select * from t_user where username = #{param1} and password = #{param2}")
    User findNameAndPwd(String username,String password);

}
