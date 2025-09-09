package com.test.redis.redis.controller.list;

import com.test.redis.redis.common.Result;
import com.test.redis.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis/list")
@Tag(name = "List 类型", description = "列表类型操作接口")
public class ListController {

    private final RedisTemplate<String, Object> redisTemplate;

    public ListController(RedisService redisService) {
        this.redisTemplate = redisService.getRedisTemplate();
    }

    @Operation(summary = "List - 增：从左侧插入列表元素")
    @PostMapping("/add/left")
    public Result<Long> listAddLeft(@RequestParam String key, @RequestParam String value) {
        Long size = redisTemplate.opsForList().leftPush(key, value);
        return Result.success(size);
    }

    @Operation(summary = "List - 增：从右侧插入列表元素")
    @PostMapping("/add/right")
    public Result<Long> listAddRight(@RequestParam String key, @RequestParam String value) {
        Long size = redisTemplate.opsForList().rightPush(key, value);
        return Result.success(size);
    }

    @Operation(summary = "List - 删：从左侧弹出列表元素")
    @DeleteMapping("/delete/left")
    public Result<Object> listDeleteLeft(@RequestParam String key) {
        Object value = redisTemplate.opsForList().leftPop(key);
        return Result.success(value);
    }

    @Operation(summary = "List - 删：从右侧弹出列表元素")
    @DeleteMapping("/delete/right")
    public Result<Object> listDeleteRight(@RequestParam String key) {
        Object value = redisTemplate.opsForList().rightPop(key);
        return Result.success(value);
    }

    @Operation(summary = "List - 删：移除指定元素")
    @DeleteMapping("/delete")
    public Result<Long> listDelete(@RequestParam String key, @RequestParam long count, @RequestParam String value) {
        Long removed = redisTemplate.opsForList().remove(key, count, value);
        return Result.success(removed);
    }

    @Operation(summary = "List - 改：设置指定索引位置的值")
    @PutMapping("/update")
    public Result<String> listUpdate(@RequestParam String key, @RequestParam long index, @RequestParam String value) {
        redisTemplate.opsForList().set(key, index, value);
        return Result.success("更新成功");
    }

    @Operation(summary = "List - 查：获取指定范围的元素")
    @GetMapping("/query")
    public Result<List<Object>> listQuery(@RequestParam String key, @RequestParam long start, @RequestParam long end) {
        List<Object> values = redisTemplate.opsForList().range(key, start, end);
        return Result.success(values);
    }
} 