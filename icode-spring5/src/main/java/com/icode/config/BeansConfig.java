package com.icode.config;

import com.icode.dao.UserDao;
import com.icode.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/15 23:03
 */
@Configuration
@EnableAspectJAutoProxy
public class BeansConfig {

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public UserServiceImpl userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        return userService;
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
