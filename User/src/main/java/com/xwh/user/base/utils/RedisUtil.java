package com.xwh.user.base.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public <T extends Serializable> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T extends Serializable> void set(String key, T value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public <T extends Serializable> T get(String key, Class<T> clazz) {
        Serializable data = redisTemplate.opsForValue().get(key);
        if (data == null) return null;
        return clazz.cast(data);
    }

}
