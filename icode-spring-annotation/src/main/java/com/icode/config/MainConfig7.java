package com.icode.config;

import com.icode.entity.Car;
import com.icode.entity.Cat;
import com.icode.entity.Dog;
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
public class MainConfig7 {
//    @Bean
//    public Cat cat() {
//        return new Cat();
//    }

    @Bean
    public Dog dog() {
        return new Dog();
    }
}
