package com.icode.spring.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/12 10:40
 */
public class MyMethodInterceptor implements MethodInterceptor {

    /**
     * cglib
     *
     * @param o           cglib生成的代理对象
     * @param method      被代理对象的方法
     * @param objects     方法入参
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before advice...");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after advice...");
        return object;
    }
}
