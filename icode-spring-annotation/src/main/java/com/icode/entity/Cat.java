package com.icode.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/27 12:36
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("构造器");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }
}
