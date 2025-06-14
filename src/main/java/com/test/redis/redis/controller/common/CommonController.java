package com.test.redis.redis.controller.common;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/common")
@Tag(name = "通用操作", description = "通用操作接口")
public class CommonController {

    private final RedisService redisService;

    public CommonController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(summary = "删除键")
    @DeleteMapping("/delete")
    public Result<Map<String, Object>> deleteKey(@RequestParam String key) {
        Boolean deleted = redisService.delete(key);
        Map<String, Object> data = Map.of(
            "key", (Object)key,
            "deleted", (Object)deleted
        );
        return Result.success(data);
    }
} 