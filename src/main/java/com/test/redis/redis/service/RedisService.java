package com.test.redis.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Redis 服务类 - 直接提供 RedisTemplate 实例
 * 控制器可以直接使用 RedisTemplate 进行 Redis 操作
 */
@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取 RedisTemplate 实例
     * 控制器可以直接使用此实例进行 Redis 操作
     */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}