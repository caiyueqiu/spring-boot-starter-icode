package com.icode.config;

import com.icode.entity.Person;
import org.springframework.context.annotation.*;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:49
 */
@Configuration
public class MainConfig3 {
    @Bean(value = "windows")
    public Person person01() {
        System.out.println("给容器中添加Person实例");
        return new Person("张三", 19);
    }

    @Conditional(value = {
            LinuxCondition.class
    })
    @Bean(value = "linux")
    public Person person02() {
        System.out.println("给容器中添加Person实例");
        return new Person("李四", 19);
    }
}
