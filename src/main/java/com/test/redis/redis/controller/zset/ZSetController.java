package com.test.redis.redis.controller.zset;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/redis/zset")
@Tag(name = "ZSet 类型", description = "有序集合类型操作接口")
public class ZSetController {

    private final RedisTemplate<String, Object> redisTemplate;

    public ZSetController(RedisService redisService) {
        this.redisTemplate = redisService.getRedisTemplate();
    }

    @Operation(summary = "ZSet - 增：添加有序集合元素")
    @PostMapping("/add")
    public Result<Boolean> zsetAdd(@RequestParam String key, @RequestParam String value, @RequestParam double score) {
        Boolean added = redisTemplate.opsForZSet().add(key, value, score);
        return Result.success(added);
    }

    @Operation(summary = "ZSet - 删：移除有序集合元素")
    @DeleteMapping("/delete")
    public Result<Long> zsetDelete(@RequestParam String key, @RequestParam String value) {
        Long removed = redisTemplate.opsForZSet().remove(key, value);
        return Result.success(removed);
    }

    @Operation(summary = "ZSet - 改：更新有序集合元素分数")
    @PutMapping("/update")
    public Result<Boolean> zsetUpdate(@RequestParam String key, @RequestParam String value, @RequestParam double newScore) {
        Boolean updated = redisTemplate.opsForZSet().add(key, value, newScore);
        return Result.success(updated);
    }

    @Operation(summary = "ZSet - 查：获取指定范围的元素")
    @GetMapping("/query")
    public Result<Set<Object>> zsetQuery(@RequestParam String key, @RequestParam long start, @RequestParam long end) {
        Set<Object> values = redisTemplate.opsForZSet().range(key, start, end);
        return Result.success(values);
    }

    @Operation(summary = "ZSet - 查：获取指定范围的元素（带分数）")
    @GetMapping("/query/withscores")
    public Result<Set<ZSetOperations.TypedTuple<Object>>> zsetQueryWithScores(@RequestParam String key, @RequestParam long start, @RequestParam long end) {
        Set<ZSetOperations.TypedTuple<Object>> valuesWithScores = redisTemplate.opsForZSet().rangeWithScores(key, start, end);
        return Result.success(valuesWithScores);
    }
} 