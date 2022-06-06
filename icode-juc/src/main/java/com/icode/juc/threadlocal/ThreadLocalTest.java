package com.icode.juc.threadlocal;

import java.util.HashMap;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/4 21:39
 */
public class ThreadLocalTest {

    volatile boolean flag;

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("zzyybs@126.com");
        threadLocal.get();
        new HashMap<>().put(null, 123);
    }
}
