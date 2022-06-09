package com.icode.juc.base;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/9 7:48
 */
public class ThreadYieldDemo {
    public static void main(String[] args) throws Exception {
        new ThreadYield().start();
        new ThreadYield().start();
    }
}

class ThreadYield extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
