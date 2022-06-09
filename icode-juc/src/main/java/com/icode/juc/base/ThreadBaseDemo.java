package com.icode.juc.base;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 7:43
 */
public class ThreadBaseDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
        }, "t1");
        t1.start();
    }
}
