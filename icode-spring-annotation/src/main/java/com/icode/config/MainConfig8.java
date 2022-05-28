package com.icode.config;

import com.icode.entity.Dog;
import com.icode.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
public class MainConfig8 {

    @Bean(initMethod = "init")
    public User user() {
        User user = new User();
        user.setName("大山");
        return user;
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
