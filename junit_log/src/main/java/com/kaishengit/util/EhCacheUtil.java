package com.kaishengit.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * Ehcache的工具类
 */

public class EhCacheUtil {

    private static CacheManager cacheManager = new CacheManager();

    public Ehcache getEhcache(String cacheName){
        return cacheManager.getEhcache(cacheName);
    }
    //添加
    public void set(Ehcache ehcache, Serializable key,Serializable value){
        Element element = new Element(key,value);
        ehcache.put(element );
    }

    public void set(String cacheName,Serializable key,Serializable value){
        Element element = new Element(key,value);
        getEhcache(cacheName).put(element);
    }

    public void set(String cacheName,Object key,Object value){
        Element element = new Element(key,value);
        getEhcache(cacheName).put(element);
    }
    //取值
    public Object get(Ehcache ehcache,Serializable key){
        Element element = ehcache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Object get(String cacheName,Serializable key){
        Element element = getEhcache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    //删除
    public void removeAll(Ehcache ehcache){
        ehcache.removeAll();
    }

    public void removeAll(String cacheName){
        getEhcache(cacheName).removeAll();
    }

    public void remove(String cacheName,Serializable key){
        getEhcache(cacheName).remove(key);
    }

    public void remove(Ehcache ehcache,Serializable key){
        ehcache.remove(key);
    }
}
