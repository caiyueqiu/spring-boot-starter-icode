package com.icode.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用AtomicReference来实现自旋锁
 *
 * @author caiyq
 * @date 2022/6/10 23:21
 */
public class AtomicReferenceDemo {
    private static AtomicReference<Thread> atomicReference = new AtomicReference<>();
    private static Thread thread;

    public static void main(String[] args) throws Exception {

    }

    private static void lock() {
        thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " 进来了");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }
}
