package com.kaishengit.service.impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void editUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void del(Integer id) {
        userMapper.del(id);
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAll();

    }

    @Override
    @Transactional
    public void saveNewUser(User user, Integer[] roleIds) {
        userMapper.save(user);
        if(roleIds != null){
            for(Integer roleId : roleIds){
                Role role = roleMapper.findById(roleId);
                if(role != null){
                    roleMapper.saveNewUserRole(user.getId(),roleId);
                }
            }
        }
    }
}
