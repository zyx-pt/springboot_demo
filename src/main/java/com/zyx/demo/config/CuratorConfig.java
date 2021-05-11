package com.zyx.demo.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @ClassName com.zyx.demo.config.CuratorConfig
 * @Author zhengyongxian
 * @Date 2021/1/6 10:06
 */
@Configuration
public class CuratorConfig {

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", exponentialBackoffRetry);
        return curatorFramework;
    }
}
