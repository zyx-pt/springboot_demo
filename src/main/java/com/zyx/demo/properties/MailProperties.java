package com.zyx.demo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 描述：读取指定properties文件的配置信息
 * </pre>
 *
 * @author zhengyx
 * @email zhengyx@gillion.com.cn
 * @date 2020/9/23
 * @time 16:38
 * @description: TODO
 */
@Component
@PropertySource("classpath:properties/mail.properties")
@Getter
@Setter
public class MailProperties {

    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.defaultEncoding}")
    private String defaultEncoding;
    @Value("${mail.smtp.username}")
    private String username;
    @Value("${mail.smtp.password}")
    private String password;
    @Value("${mail.smtp.port}")
    private String port;
    @Value("${mail.send.name}")
    private String sendName;
    @Value("${mail.smtp.starttls.enable}")
    private Boolean enableStarttls;

}
