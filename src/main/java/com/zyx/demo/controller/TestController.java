package com.zyx.demo.controller;

import com.zyx.demo.properties.LibraryProperties;
import com.zyx.demo.properties.MailProperties;
import com.zyx.demo.properties.MyProfileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 描述：用于相关测试
 * </pre>
 *
 * @author zhengyongxian
 */

@RestController
@RequestMapping("/test")
public class TestController {

    /** 1.使用 @Value("${property}") 读取比较简单的配置信息（不推荐使用） */
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${library.location}")
    private String libraryLocation;

    @Autowired
    private LibraryProperties libraryProperties;
    @Autowired
    private MyProfileProperties myProfileProperties;
    @Autowired
    private MailProperties mailProperties;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    /**
     * @Description: 测试读取配置文件
     *
     * @Author: zhengyongxian
     * @Date: 2020/9/23 17:01
     * @param
     * @return: java.lang.String
     */
    @RequestMapping("/readProperties")
    public String readProperties() {

        System.out.println("1. 使用@Value(\"${property}\") 读取------------");
        System.out.println(applicationName);
        System.out.println(libraryLocation);

        System.out.println("2. 使用@ConfigurationProperties+@Component读取-----------");
        System.out.println(libraryProperties.getLocation());
        System.out.println(libraryProperties.getBooks());

        System.out.println("3. 使用@ConfigurationProperties+@EnableConfigurationProperties读取-------------");
        System.out.println(myProfileProperties.getName());
        System.out.println(myProfileProperties.getEmail());

        System.out.println("4. 使用@PropertySource+@Component读取-------------");
        System.out.println(mailProperties.getHost());
        System.out.println(mailProperties.getUsername());
        return "Success!";
    }



}
