package com.zyx.demo;

import com.zyx.demo.properties.MyProfileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Description: SpringBoot 启动类
 *
 * @Author: zhengyongxian
 * @Date: 2020/9/23 9:14
 */
@SpringBootApplication
@EnableConfigurationProperties(MyProfileProperties.class)
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
