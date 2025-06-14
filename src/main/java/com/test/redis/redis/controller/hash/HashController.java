package com.test.redis.redis.controller.hash;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/hash")
@Tag(name = "Hash 类型", description = "哈希表类型操作接口")
public class HashController {

    private final RedisService redisService;

    public HashController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(summary = "设置哈希表字段")
    @PostMapping
    public Result<Map<String, Object>> setHash(@RequestParam String key, @RequestParam String hashKey, @RequestParam String value) {
        redisService.putHash(key, hashKey, value);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "hashKey", (Object)hashKey,
            "value", (Object)value
        );
        return Result.success(data);
    }

    @Operation(summary = "获取哈希表字段")
    @GetMapping
    public Result<Map<String, Object>> getHash(@RequestParam String key, @RequestParam String hashKey) {
        Object value = redisService.getHash(key, hashKey);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "hashKey", (Object)hashKey,
            "value", value
        );
        return Result.success(data);
    }
} 