package com.kaishengit.service.impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Transactional
    public void editUser(User user, Integer[] roleIds) {
        //删除原有角色
        roleMapper.delRoleByUserId(user.getId());
        //添加新角色
        addUserRole(user, roleIds);
        //更新用户
        if(StringUtils.isNotEmpty(user.getPassword())) {
        }
        userMapper.update(user);
    }

    private void addUserRole(User user, Integer[] roleIds) {
        if(roleIds != null){
            for(Integer roleId : roleIds){
                Role role = roleMapper.findById(roleId);
                if(role != null){
                    roleMapper.saveNewUserRole(user.getId(),roleId);
                }
            }
        }
    }

    @Override
    @Transactional
    public void del(Integer id) {
        //删除用户的角色
        roleMapper.delRoleByUserId(id);
        //删除用户
        userMapper.del(id);
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAll();

    }

    @Override
    @Transactional
    public void saveNewUser(User user, Integer[] roleIds) {
        //user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
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
