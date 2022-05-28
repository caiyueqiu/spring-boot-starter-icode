package com.icode.entity;

import lombok.Data;
import lombok.ToString;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/10 22:48
 */
@ToString
public class User {
    private Integer id;
    private String name;
    private String beanName;

    public User() {
        System.out.println("User实例化");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("设置name：" + name);
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void init() {
        System.out.println("User中自定义的初始化方法");
    }
}
