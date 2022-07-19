package com.icode;

import com.icode.config.BeansConfig;
import com.icode.entity.User;
import com.icode.service.UserService;
import com.icode.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootTest
class IcodeSpring5ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testXml() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        List<User> userList = userService.getUserList();
        System.out.println(userList);
    }

    @Test
    public void testAnnotation() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        List<User> userList = userService.getUserList();
        System.out.println(userList);
    }
}
