package com.test.redis.redis.controller.hash;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis/hash")
@Tag(name = "Hash 类型", description = "哈希表类型操作接口")
public class HashController {

    private final RedisTemplate<String, Object> redisTemplate;

    public HashController(RedisService redisService) {
        this.redisTemplate = redisService.getRedisTemplate();
    }

    @Operation(summary = "Hash - 增：设置哈希表字段")
    @PostMapping("/add")
    public Result<String> hashAdd(@RequestParam String key, @RequestParam String hashKey, @RequestParam String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return Result.success("设置成功");
    }

    @Operation(summary = "Hash - 删：删除哈希表字段")
    @DeleteMapping("/delete")
    public Result<Long> hashDelete(@RequestParam String key, @RequestParam String hashKey) {
        Long deleted = redisTemplate.opsForHash().delete(key, hashKey);
        return Result.success(deleted);
    }

    @Operation(summary = "Hash - 改：更新哈希表字段")
    @PutMapping("/update")
    public Result<String> hashUpdate(@RequestParam String key, @RequestParam String hashKey, @RequestParam String newValue) {
        redisTemplate.opsForHash().put(key, hashKey, newValue);
        return Result.success("更新成功");
    }

    @Operation(summary = "Hash - 查：获取哈希表字段")
    @GetMapping("/query")
    public Result<Object> hashQuery(@RequestParam String key, @RequestParam String hashKey) {
        Object value = redisTemplate.opsForHash().get(key, hashKey);
        return Result.success(value);
    }

    @Operation(summary = "Hash - 查：获取哈希表所有字段")
    @GetMapping("/query/all")
    public Result<Map<Object, Object>> hashQueryAll(@RequestParam String key) {
        Map<Object, Object> allFields = redisTemplate.opsForHash().entries(key);
        return Result.success(allFields);
    }
} 