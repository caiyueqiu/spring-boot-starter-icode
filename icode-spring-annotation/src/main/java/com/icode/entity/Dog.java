package com.icode.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 12:42
 */
@Component
public class Dog {
    public Dog() {
        System.out.println("构造器");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }
}
