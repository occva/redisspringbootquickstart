package com.test.redis.redis.controller.string;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis/string")
@Tag(name = "String 类型", description = "字符串类型操作接口")
public class StringController {

    private final RedisService redisService;

    public StringController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(summary = "设置字符串")
    @PostMapping
    public Result<Map<String, Object>> setString(@RequestParam String key, @RequestParam String value) {
        redisService.setString(key, value);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value
        );
        return Result.success(data);
    }

    @Operation(summary = "获取字符串")
    @GetMapping
    public Result<Map<String, Object>> getString(@RequestParam String key) {
        String value = redisService.getString(key);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value
        );
        return Result.success(data);
    }

    @Operation(summary = "设置带过期时间的字符串")
    @PostMapping("/expire")
    public Result<Map<String, Object>> setStringWithExpiration(@RequestParam String key, @RequestParam String value, 
            @RequestParam long timeout, @RequestParam String unit) {
        TimeUnit timeUnit = TimeUnit.valueOf(unit.toUpperCase());
        redisService.setStringWithExpiration(key, value, timeout, timeUnit);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value,
            "timeout", (Object)timeout,
            "unit", (Object)unit
        );
        return Result.success(data);
    }
} 