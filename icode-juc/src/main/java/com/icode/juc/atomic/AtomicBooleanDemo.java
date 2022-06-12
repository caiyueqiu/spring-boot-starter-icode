package com.icode.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean可以作为中断标识停止线程的方式
 *
 * @author caiyq
 * @date 2022/6/10 23:10
 */
public class AtomicBooleanDemo {
    public static void main(String[] args) throws Exception {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 进来了");
            while (!atomicBoolean.get()) {
                System.out.println("一直在运行");
            }
            System.out.println("中断");
        }, "A").start();
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "B").start();
    }
}
