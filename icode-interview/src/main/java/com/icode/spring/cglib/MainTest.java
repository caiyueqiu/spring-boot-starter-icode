package com.icode.spring.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 10:41
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\workspace\\projects_java\\spring-boot-starter-icode");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(HelloService.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyMethodInterceptor());
        // 创建代理对象
        HelloService proxy = (HelloService) enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();
    }
}
