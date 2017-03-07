package com.kaishengit;


import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class RedisTest {

    @Test
    public void stringSet(){
        Jedis jedis = new Jedis("182.92.119.219",6379,50000);
        jedis.set("name","李明明");
        jedis.close();
    }

    @Test
    public void stringGet(){
        Jedis jedis = new Jedis("182.92.119.219",6379);
        String name = jedis.get("name");
        jedis.close();
        Assert.assertEquals("李明明",name);
    }

    @Test
    public void testPool(){
        JedisPool pool = new JedisPool("182.92.119.219");
        Jedis jedis = pool.getResource();
        jedis.lpush("name2","tom","rose","jack");
        jedis.close();
        pool.destroy();
    }

    @Test
    public void testList(){
        Jedis jedis = new Jedis("182.92.119.219",6379);
        List<String> names = jedis.lrange("name2",0,-1);
        for(String name : names){
            System.out.println(name);
        }
        jedis.close();
    }
}
