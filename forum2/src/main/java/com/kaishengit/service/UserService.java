package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kaishengit.dao.LoginLogDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Loginlog;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.EmailUtil;
import com.kaishengit.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao = new UserDao();
    private LoginLogDao loginLogDao = new LoginLogDao();
    //发送激活邮件的token缓存
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(8, TimeUnit.HOURS)
            .build();

    /**
     * 验证用户名是否被占用
     * @param username
     * @return
     */
    public boolean validateUserName(String username) {
        //保留用户名验证
        String name = Config.get("no.signin.username");
        List<String> nameList = Arrays.asList(name.split(","));
        if(nameList.contains(username)){
            return false;
        }
        return userDao.findByUserName(username) == null;

    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 保存新注册的用户
     * @param username
     * @param password
     * @param email
     * @param phone
     */
    public void saveNewUser(String username, String password, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setAvatar(User.DEFAULT_AVATAR_NAME);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt") + password));
        user.setState(User.USERSTATE_UNACTIVE);

        userDao.save(user);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //发送激活邮件
                String token = UUID.randomUUID().toString();
                String url = "http://www.zsl.com/user/active?token="+token;
                //放入缓存  并带个值username
                cache.put(token,username);
                String html = "<h2>尊敬的"+username+":</h2>请点击<a href='"+url+"'>此链接</a>激活账户!!!! <br> 论坛";
                EmailUtil.sendHtmlEmail(email,"新用户激活邮件",html);

            }
        });

        thread.start();

    }

    /**
     * 根据token激活对应账户
     * @param token
     */

    public void activeUser(String token) {
        String username = cache.getIfPresent(token);
        if(username == null){
            throw new ServiceException("token无效或已过期");
        }else{
            User user = userDao.findByUserName(username);
            if(user == null){
                throw new ServiceException("无法找到对应的账号");
            }else{
                user.setState(User.USERSTATE_ACTIVE);
                userDao.update(user);

                //将缓存中的键值对删除
                cache.invalidate(token);
            }
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param ip
     * @return
     */

    public User login(String username, String password, String ip) {
        User user = userDao.findByUserName(username);
        if(user != null && DigestUtils.md5Hex(Config.get("user.password.salt") + password).equals(user.getPassword())){
            if(user.getState().equals(User.USERSTATE_ACTIVE)){
                //记录登录日志
                Loginlog log = new Loginlog();
                log.setIp(ip);
                log.setUserId(user.getId());
                loginLogDao.save(log);

                logger.info("{}登录了系统，IP：{}",username,ip);
                return user;


            }else if(user.getState().equals(User.USERSTATE_UNACTIVE)){
                throw new ServiceException("该账号未激活");
            }else{
                throw new ServiceException("该账号已被禁用");
            }
        }else{
            throw new ServiceException("账号或密码错误");
        }
    }
}
