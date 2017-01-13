package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> findAll();

    Role findById(Integer roleId);

    void saveNewUserRole(@Param("userId") Integer id,@Param("roleId") Integer roleId);
}
