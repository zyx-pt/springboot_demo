package com.zyx.demo.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <pre>
 * 描述：读取配置信息
 * </pre>
 *
 * @author zhengyx
 * @email zhengyx@gillion.com.cn
 * @date 2020/9/23
 * @time 9:15
 * @description: 使用@ConfigurationProperties配合@Component读取配置信息与Bean绑定
 */
@Component
@ConfigurationProperties(prefix = "library")
@Setter
@Getter
@ToString
public class LibraryProperties {
    private String location;
    private List<Book> books;

    @Setter
    @Getter
    @ToString
    static class Book{
        String name;
        String description;
    }

}
