package com.icode.config;

import com.icode.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
public class MainConfig6 {
    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
