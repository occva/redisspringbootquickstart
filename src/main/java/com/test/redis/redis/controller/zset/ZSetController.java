package com.test.redis.redis.controller.zset;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/zset")
@Tag(name = "ZSet 类型", description = "有序集合类型操作接口")
public class ZSetController {

    private final RedisService redisService;

    public ZSetController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(summary = "添加有序集合元素")
    @PostMapping("/add")
    public Result<Map<String, Object>> addZSet(@RequestParam String key, @RequestParam String value, @RequestParam double score) {
        Boolean added = redisService.addZSet(key, value, score);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value,
            "score", (Object)score,
            "added", (Object)added
        );
        return Result.success(data);
    }
} 