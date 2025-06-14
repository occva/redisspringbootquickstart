# Redis 操作接口项目

## 项目介绍
这是一个基于 Spring Boot 的 Redis 操作接口项目，提供了 Redis 各种数据类型的操作接口，包括 String、Hash、List、Set、ZSet 等类型的增删改查操作。

## 技术栈
- Spring Boot 3.5.0
- Spring Data Redis
- Swagger/OpenAPI 3.0
- Lombok

## 项目结构
```
src/main/java/com/test/redis/redis/
├── controller
│   ├── common    // 通用操作
│   ├── string    // String 类型操作
│   ├── hash      // Hash 类型操作
│   ├── list      // List 类型操作
│   ├── set       // Set 类型操作
│   └── zset      // ZSet 类型操作
├── service       // 业务逻辑层
├── config        // 配置类
└── common        // 公共类
```

## 核心功能

### 1. String 类型操作
```java
// 设置字符串
redisService.setString(key, value);

// 获取字符串
String value = redisService.getString(key);

// 设置带过期时间的字符串
TimeUnit timeUnit = TimeUnit.valueOf(unit.toUpperCase());
redisService.setStringWithExpiration(key, value, timeout, timeUnit);
```

### 2. Hash 类型操作
```java
// 设置哈希表字段
redisService.putHash(key, hashKey, value);

// 获取哈希表字段
Object value = redisService.getHash(key, hashKey);
```

### 3. List 类型操作
```java
// 从左侧插入列表元素
Long size = redisService.leftPushList(key, value);

// 从右侧弹出列表元素
Object value = redisService.rightPopList(key);
```

### 4. Set 类型操作
```java
// 添加集合元素
Long size = redisService.addSet(key, value);

// 判断集合元素
Boolean isMember = redisService.isMemberSet(key, value);
```

### 5. ZSet 类型操作
```java
// 添加有序集合元素
Boolean added = redisService.addZSet(key, value, score);
```

### 6. 通用操作
```java
// 删除键
Boolean deleted = redisService.delete(key);
```

## API 接口说明

### String 类型接口
- 设置字符串：`POST /redis/string`
  - 参数：key, value
- 获取字符串：`GET /redis/string`
  - 参数：key
- 设置带过期时间的字符串：`POST /redis/string/expire`
  - 参数：key, value, timeout, unit

### Hash 类型接口
- 设置哈希表字段：`POST /redis/hash`
  - 参数：key, hashKey, value
- 获取哈希表字段：`GET /redis/hash`
  - 参数：key, hashKey

### List 类型接口
- 从左侧插入列表元素：`POST /redis/list/left-push`
  - 参数：key, value
- 从右侧弹出列表元素：`GET /redis/list/right-pop`
  - 参数：key

### Set 类型接口
- 添加集合元素：`POST /redis/set/add`
  - 参数：key, value
- 判断集合元素：`GET /redis/set/ismember`
  - 参数：key, value

### ZSet 类型接口
- 添加有序集合元素：`POST /redis/zset/add`
  - 参数：key, value, score

### 通用操作接口
- 删除键：`DELETE /redis/common/delete`
  - 参数：key

## 返回格式
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "key": "test",
        "value": "hello"
    }
}
```

## 参数说明
- key: 键名
- value: 值
- hashKey: 哈希表字段名
- timeout: 过期时间
- unit: 时间单位（SECONDS, MINUTES, HOURS, DAYS）
- score: 分数（用于 ZSet）

## 启动说明
1. 确保 Redis 服务已启动
2. 修改 `application.yaml` 中的 Redis 配置
3. 运行 `RedisApplication` 启动类
4. 访问 Swagger 文档：`http://localhost:8088/swagger-ui.html`

## 注意事项
1. 所有接口返回的数据类型都是 Object
2. 时间单位支持：SECONDS, MINUTES, HOURS, DAYS
3. 接口按数据类型分组，便于管理和使用 