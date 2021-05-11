package com.zyx.demo.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Redis的相关配置
 * @ClassName com.zyx.demo.config.RedisConfig
 * @Author zhengyongxian
 * @Date 2021/1/6 9:36
 */
@Configuration
public class RedisConfig {

    @Bean
    public Redisson redisson(){
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }

}
