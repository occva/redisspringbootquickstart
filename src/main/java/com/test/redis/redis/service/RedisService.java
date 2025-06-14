package com.test.redis.redis.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * String类型操作：设置键值对
     */
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * String类型操作：获取键对应的值
     */
    public String getString(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * String类型操作：设置带过期时间的键值对
     */
    public void setStringWithExpiration(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * Hash类型操作：设置字段值
     */
    public void putHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * Hash类型操作：获取字段值
     */
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * List类型操作：从左侧插入元素
     */
    public Long leftPushList(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * List类型操作：从右侧弹出元素
     */
    public Object rightPopList(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * Set类型操作：添加一个或多个元素
     */
    public Long addSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * Set类型操作：判断元素是否存在
     */
    public Boolean isMemberSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * ZSet类型操作：添加带分数的元素
     */
    public Boolean addZSet(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * ZSet类型操作：移除一个或多个元素
     */
    public Long removeZSet(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 通用操作：删除指定的键
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}