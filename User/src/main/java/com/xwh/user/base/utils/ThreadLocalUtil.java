package com.xwh.user.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ThreadLocalUtil {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String data) {
        if (data == null) return;
        THREAD_LOCAL.set(data);
    }

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static <T> T get(Class<T> clazz) {
        String data = THREAD_LOCAL.get();
        if (data == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        T var;
        try {
            var = objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("反序列化异常");
        }
        return var;
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

}

