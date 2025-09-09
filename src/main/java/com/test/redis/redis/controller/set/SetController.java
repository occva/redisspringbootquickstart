package com.test.redis.redis.controller.set;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/redis/set")
@Tag(name = "Set 类型", description = "集合类型操作接口")
public class SetController {

    private final RedisTemplate<String, Object> redisTemplate;

    public SetController(RedisService redisService) {
        this.redisTemplate = redisService.getRedisTemplate();
    }

    @Operation(summary = "Set - 增：添加集合元素")
    @PostMapping("/add")
    public Result<Long> setAdd(@RequestParam String key, @RequestParam String value) {
        Long size = redisTemplate.opsForSet().add(key, value);
        return Result.success(size);
    }

    @Operation(summary = "Set - 删：移除集合元素")
    @DeleteMapping("/delete")
    public Result<Long> setDelete(@RequestParam String key, @RequestParam String value) {
        Long removed = redisTemplate.opsForSet().remove(key, value);
        return Result.success(removed);
    }

    @Operation(summary = "Set - 改：更新集合元素")
    @PutMapping("/update")
    public Result<String> setUpdate(@RequestParam String key, @RequestParam String oldValue, @RequestParam String newValue) {
        redisTemplate.opsForSet().remove(key, oldValue);
        redisTemplate.opsForSet().add(key, newValue);
        return Result.success("更新成功");
    }

    @Operation(summary = "Set - 查：获取所有集合成员")
    @GetMapping("/query")
    public Result<Set<Object>> setQuery(@RequestParam String key) {
        Set<Object> members = redisTemplate.opsForSet().members(key);
        return Result.success(members);
    }

    @Operation(summary = "Set - 查：判断集合元素是否存在")
    @GetMapping("/query/ismember")
    public Result<Boolean> setIsMember(@RequestParam String key, @RequestParam String value) {
        Boolean isMember = redisTemplate.opsForSet().isMember(key, value);
        return Result.success(isMember);
    }
} 