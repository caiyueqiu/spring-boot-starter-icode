package com.icode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
public class MainConfig5 {
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
