package com.icode.test;

import com.icode.config.MainConfig;
import com.icode.config.MainConfig8;
import com.icode.entity.Person;
import com.icode.entity.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 21:47
 */
@SpringBootTest
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig8.class);
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
