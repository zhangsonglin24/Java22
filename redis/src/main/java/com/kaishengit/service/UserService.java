package com.kaishengit.service;

import com.kaishengit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {


    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
    }

    public void saveJedis(){
        //redisTemplate.opsForValue().set("username24","kobe bryant");

        User user = new User(001,"李四","北京");
        redisTemplate.opsForValue().set("user:001",user);
    }








   /* @Autowired
    private JedisPool jedisPool;

    public void saveJedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("spring","jedis");
        jedis.close();
    }*/


}
