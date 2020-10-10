package com.zyx.demo.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 描述：读取配置信息
 * </pre>
 *
 * @author zhengyx
 * @email zhengyx@gillion.com.cn
 * @date 2020/9/23
 * @time 9:30
 * @description: 使用@ConfigurationProperties读取配置信息
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("my-profile")
@Validated
//@Component
// 如果没有使用@Component 需要在启动类上加该注解注册@EnableConfigurationProperties(MyProfileProperties.class)
public class MyProfileProperties {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    //配置文件中没有读取到的话就用默认值
    private Boolean handsome = Boolean.TRUE;
}
