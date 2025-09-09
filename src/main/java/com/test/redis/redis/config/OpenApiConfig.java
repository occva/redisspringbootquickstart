package com.test.redis.redis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j API文档配置
 */
@Configuration
public class OpenApiConfig {
    
    /**
     * 配置Knife4j的OpenAPI信息
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Redis 操作接口文档")
                        .description("提供 Redis 各种数据类型的操作接口")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Redis API")
                                .email("test@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
} 