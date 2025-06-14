package com.test.redis.redis.controller.set;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/set")
@Tag(name = "Set 类型", description = "集合类型操作接口")
public class SetController {

    private final RedisService redisService;

    public SetController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(summary = "添加集合元素")
    @PostMapping("/add")
    public Result<Map<String, Object>> addSet(@RequestParam String key, @RequestParam String value) {
        Long size = redisService.addSet(key, value);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value,
            "size", (Object)size
        );
        return Result.success(data);
    }

    @Operation(summary = "判断集合元素")
    @GetMapping("/ismember")
    public Result<Map<String, Object>> isMemberSet(@RequestParam String key, @RequestParam String value) {
        Boolean isMember = redisService.isMemberSet(key, value);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "value", (Object)value,
            "isMember", (Object)isMember
        );
        return Result.success(data);
    }
} 