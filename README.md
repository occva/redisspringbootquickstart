# Redis 项目文件树和功能说明

## 项目概述
这是一个基于 Spring Boot 3.5.0 和 Java 21 的 Redis 操作演示项目，提供了完整的 Redis 五种数据类型（String、Hash、List、Set、ZSet）的 RESTful API 接口，并集成了 Knife4j API 文档工具。




![image-20250909155815298](https://s2.loli.net/2025/09/09/Qvml9VdpyEZIDw5.png)

## 文件树结构

```
src
└── main
    ├── java
    │   └── com.test.redis
    │       └── redis
    │           ├── RedisApplication.java               # Spring Boot 启动类
    │           ├── common
    │           │   └── Result.java                      # 统一响应结果封装类
    │           ├── config
    │           │   ├── OpenApiConfig.java               # Knife4j API 文档配置
    │           │   └── RedisConfig.java                 # Redis 配置类
    │           ├── controller
    │           │   ├── common
    │           │   │   └── CommonController.java        # 通用操作控制器
    │           │   ├── hash
    │           │   │   └── HashController.java          # Hash 类型操作控制器
    │           │   ├── list
    │           │   │   └── ListController.java          # List 类型操作控制器
    │           │   ├── set
    │           │   │   └── SetController.java           # Set 类型操作控制器
    │           │   ├── string
    │           │   │   └── StringController.java        # String 类型操作控制器
    │           │   └── zset
    │           │       └── ZSetController.java          # ZSet 类型操作控制器
    │           └── service
    │               └── RedisService.java                # Redis 业务服务类
    └── resources
        └── application.yaml                              # 应用配置文件

```

## 核心功能模块

### 1. 项目配置
- **pom.xml**: Maven 依赖管理，包含 Spring Boot、Redis、Knife4j、Lombok 等核心依赖
- **application.yaml**: 应用配置，包含服务器端口(8088)、Redis 连接配置、Knife4j 配置

### 2. 核心类功能

#### RedisApplication.java
- Spring Boot 应用启动类
- 使用 `@SpringBootApplication` 注解

#### Result.java
- 统一响应结果封装类
- 提供 `success()` 和 `error()` 静态方法
- 包含 code、message、data 字段

#### RedisConfig.java
- Redis 配置类
- 配置 RedisTemplate 的序列化方式
- Key 使用 StringRedisSerializer
- Value 使用 GenericJackson2JsonRedisSerializer

#### OpenApiConfig.java
- Knife4j API 文档配置

### 3. 业务服务层

#### RedisService.java
简化的 Redis 服务类，直接提供 RedisTemplate 实例：

**主要功能：**
- `getRedisTemplate()`: 获取 RedisTemplate 实例，供控制器直接使用
- 控制器可以直接调用 Spring Data Redis 提供的现成 API
- 无需封装底层 Redis 操作，代码更简洁

### 4. 控制器层

#### StringController.java
- 路径: `/redis/string`
- 功能: String 类型操作接口（直接使用 RedisTemplate）
- 接口:
  - `POST /add`: String - 增：设置字符串
  - `POST /add/expire`: String - 增：设置带过期时间的字符串
  - `DELETE /delete`: String - 删：删除字符串
  - `PUT /update`: String - 改：更新字符串
  - `GET /query`: String - 查：获取字符串

#### HashController.java
- 路径: `/redis/hash`
- 功能: Hash 类型操作接口（直接使用 RedisTemplate）
- 接口:
  - `POST /add`: Hash - 增：设置哈希表字段
  - `DELETE /delete`: Hash - 删：删除哈希表字段
  - `PUT /update`: Hash - 改：更新哈希表字段
  - `GET /query`: Hash - 查：获取哈希表字段
  - `GET /query/all`: Hash - 查：获取哈希表所有字段

#### ListController.java
- 路径: `/redis/list`
- 功能: List 类型操作接口（直接使用 RedisTemplate）
- 接口:
  - `POST /add/left`: List - 增：从左侧插入列表元素
  - `POST /add/right`: List - 增：从右侧插入列表元素
  - `DELETE /delete/left`: List - 删：从左侧弹出列表元素
  - `DELETE /delete/right`: List - 删：从右侧弹出列表元素
  - `DELETE /delete`: List - 删：移除指定元素
  - `PUT /update`: List - 改：设置指定索引位置的值
  - `GET /query`: List - 查：获取指定范围的元素

#### SetController.java
- 路径: `/redis/set`
- 功能: Set 类型操作接口（直接使用 RedisTemplate）
- 接口:
  - `POST /add`: Set - 增：添加集合元素
  - `DELETE /delete`: Set - 删：移除集合元素
  - `PUT /update`: Set - 改：更新集合元素
  - `GET /query`: Set - 查：获取所有集合成员
  - `GET /query/ismember`: Set - 查：判断集合元素是否存在

#### ZSetController.java
- 路径: `/redis/zset`
- 功能: ZSet 类型操作接口（直接使用 RedisTemplate）
- 接口:
  - `POST /add`: ZSet - 增：添加有序集合元素
  - `DELETE /delete`: ZSet - 删：移除有序集合元素
  - `PUT /update`: ZSet - 改：更新有序集合元素分数
  - `GET /query`: ZSet - 查：获取指定范围的元素
  - `GET /query/withscores`: ZSet - 查：获取指定范围的元素（带分数）

#### CommonController.java
- 路径: `/redis/common`
- 功能: 通用操作接口（直接使用 RedisTemplate）
- 接口:
  - `DELETE /delete`: 通用 - 删：删除键
  - `GET /exists`: 通用 - 查：检查键是否存在
  - `PUT /expire`: 通用 - 改：设置键的过期时间
  - `GET /ttl`: 通用 - 查：获取键的剩余过期时间

## 技术栈

- **Java 21**: 编程语言
- **Spring Boot 3.5.0**: 应用框架
- **Spring Data Redis**: Redis 数据访问
- **Lettuce**: Redis 客户端
- **Apache Commons Pool2**: 连接池
- **Knife4j**: API 文档工具
- **Lombok**: 代码生成工具
- **Maven**: 项目构建工具

## API 文档访问

启动应用后，可通过以下地址访问 API 文档：
- Knife4j 文档: http://localhost:8088/doc.html

## 项目特点

1. **完整的 Redis 数据类型支持**: 涵盖 String、Hash、List、Set、ZSet 五种数据类型
2. **直接使用 Spring Data Redis API**: 控制器直接调用 RedisTemplate 的 ops 方法，无需额外封装
3. **简化的响应格式**: 直接返回 Redis 操作结果，减少不必要的包装
4. **RESTful API 设计**: 遵循 REST 规范，使用标准 HTTP 方法（GET、POST、PUT、DELETE）
5. **完善的 API 文档**: 集成 Knife4j，提供可视化的 API 文档
6. **配置化设计**: 通过配置文件管理 Redis 连接和应用参数
7. **序列化优化**: 合理配置 Redis 序列化方式，支持复杂对象存储
8. **代码简洁性**: 移除中间层封装，直接使用 Spring 提供的 Redis 操作 API
9. **功能完整性**: 提供 Redis 常用操作，包括过期时间设置、键存在性检查等通用功能
