package com.test.redis.redis.controller.common;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis/common")
@Tag(name = "通用操作", description = "通用操作接口")
public class CommonController {

    private final RedisTemplate<String, Object> redisTemplate;

    public CommonController(RedisService redisService) {
        this.redisTemplate = redisService.getRedisTemplate();
    }

    @Operation(summary = "通用 - 删：删除键")
    @DeleteMapping("/delete")
    public Result<Boolean> deleteKey(@RequestParam String key) {
        Boolean deleted = redisTemplate.delete(key);
        return Result.success(deleted);
    }

    @Operation(summary = "通用 - 查：检查键是否存在")
    @GetMapping("/exists")
    public Result<Boolean> hasKey(@RequestParam String key) {
        Boolean exists = redisTemplate.hasKey(key);
        return Result.success(exists);
    }

    @Operation(summary = "通用 - 改：设置键的过期时间")
    @PutMapping("/expire")
    public Result<Boolean> expire(@RequestParam String key, @RequestParam long timeout, @RequestParam String unit) {
        TimeUnit timeUnit = TimeUnit.valueOf(unit.toUpperCase());
        Boolean success = redisTemplate.expire(key, timeout, timeUnit);
        return Result.success(success);
    }

    @Operation(summary = "通用 - 查：获取键的剩余过期时间")
    @GetMapping("/ttl")
    public Result<Long> getExpire(@RequestParam String key) {
        Long ttl = redisTemplate.getExpire(key);
        return Result.success(ttl);
    }
} 