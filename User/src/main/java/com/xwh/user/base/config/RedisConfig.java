package com.xwh.user.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory rcf) {
        RedisTemplate<String, Serializable> ort = new RedisTemplate<>();
        ort.setConnectionFactory(rcf);
        ort.setKeySerializer(RedisSerializer.string());
        ort.setValueSerializer(RedisSerializer.json());
        ort.setHashKeySerializer(RedisSerializer.json());
        ort.setHashValueSerializer(RedisSerializer.json());
        return ort;
    }


}
