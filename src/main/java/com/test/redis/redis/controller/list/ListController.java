package com.test.redis.redis.controller.list;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/list")
@Tag(name = "List 类型", description = "列表类型操作接口")
public class ListController {

    private final RedisService redisService;

    public ListController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(summary = "从左侧插入列表元素")
    @PostMapping("/left-push")
    public Result<Map<String, Object>> leftPushList(@RequestParam String key, @RequestParam String value) {
        Long size = redisService.leftPushList(key, value);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value,
            "size", (Object)size
        );
        return Result.success(data);
    }

    @Operation(summary = "从右侧弹出列表元素")
    @GetMapping("/right-pop")
    public Result<Map<String, Object>> rightPopList(@RequestParam String key) {
        Object value = redisService.rightPopList(key);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", value
        );
        return Result.success(data);
    }
} 