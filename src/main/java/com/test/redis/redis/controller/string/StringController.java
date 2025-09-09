package com.test.redis.redis.controller.string;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis/string")
@Tag(name = "String 类型", description = "字符串类型操作接口")
public class StringController {

    private final RedisTemplate<String, Object> redisTemplate;

    public StringController(RedisService redisService) {
        this.redisTemplate = redisService.getRedisTemplate();
    }

    @Operation(summary = "String - 增：设置字符串")
    @PostMapping("/add")
    public Result<String> stringAdd(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return Result.success("设置成功");
    }

    @Operation(summary = "String - 增：设置带过期时间的字符串")
    @PostMapping("/add/expire")
    public Result<String> stringAddWithExpiration(@RequestParam String key, @RequestParam String value, 
            @RequestParam long timeout, @RequestParam String unit) {
        TimeUnit timeUnit = TimeUnit.valueOf(unit.toUpperCase());
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        return Result.success("设置成功，过期时间: " + timeout + " " + unit);
    }

    @Operation(summary = "String - 删：删除字符串")
    @DeleteMapping("/delete")
    public Result<Boolean> stringDelete(@RequestParam String key) {
        Boolean deleted = redisTemplate.delete(key);
        return Result.success(deleted);
    }

    @Operation(summary = "String - 改：更新字符串")
    @PutMapping("/update")
    public Result<String> stringUpdate(@RequestParam String key, @RequestParam String newValue) {
        redisTemplate.opsForValue().set(key, newValue);
        return Result.success("更新成功");
    }

    @Operation(summary = "String - 查：获取字符串")
    @GetMapping("/query")
    public Result<Object> stringQuery(@RequestParam String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return Result.success(value);
    }
} 