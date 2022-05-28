package com.icode.config;

import com.icode.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
public class MainConfig2 {
    @Scope(value = "prototype")
    @Bean(value = "person")
    @Lazy
    public Person person() {
        System.out.println("给容器中添加Person实例");
        return new Person("张三", 19);
    }
}
