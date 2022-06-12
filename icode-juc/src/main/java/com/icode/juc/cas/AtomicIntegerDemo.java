package com.icode.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 底层compareAndSwapInt方法
 * 工作内存的值和主内存的值比较，如果相同，就让工作内存的值+自增值，否则，跳出循环
 *
 * @author caiyq
 * @date 2022/6/11 18:34
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + " " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + " " + atomicInteger.get());
    }
}
